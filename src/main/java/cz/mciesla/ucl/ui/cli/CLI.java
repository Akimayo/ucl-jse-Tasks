package cz.mciesla.ucl.ui.cli;

import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;
import cz.mciesla.ucl.ui.cli.forms.FormManager;
import cz.mciesla.ucl.ui.cli.menu.MenuFactory;
import cz.mciesla.ucl.ui.cli.menu.system.AssignTagMenu;
import cz.mciesla.ucl.ui.cli.menu.system.DestroyEntityMenu;
import cz.mciesla.ucl.ui.cli.menu.system.ToggleDoneMenu;
import cz.mciesla.ucl.ui.cli.views.*;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormField;
import cz.mciesla.ucl.ui.definition.forms.IFormManager;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;
import cz.mciesla.ucl.ui.definition.views.*;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CLI implements ICLI {
    private IMenuFactory menuFactory;
    private IAppLogic logic;

    private ICategoryView categoryView;
    private ITagView tagView;
    private ITaskView taskView;
    private IMenuView menuView;
    private IFormView formView;

    private final Scanner sc;

    public CLI() {
        menuFactory = new MenuFactory();

        categoryView = new CategoryView();
        tagView = new TagView();
        taskView = new TaskView();
        menuView = new MenuView();
        formView = new FormView();

        this.sc = new Scanner(System.in);
    }

    @Override
    public void run(IAppLogic logic) {
        this.logic = logic;

        drawMessage(getWelcomeText());

        IMenu currentMenu;
        IMenu nextMenu = getMainMenu();

        do {
            currentMenu = nextMenu;
            currentMenu.initialize();
            nextMenu = handleMenu(currentMenu);
        } while (nextMenu != null);
    }

    // region Forms
    @Override
    public IFormManager createFormManagerForMenu(IForm menu) {
        return new FormManager(menu, this);
    }
    // endregion

    // region Prompts
    @Override
    public int promptNumber() {
        while (!this.sc.hasNextInt())
            this.sc.next();
        return this.sc.nextInt();
    }

    @Override
    public String promptString() {
        return this.sc.nextLine();
    }

    @Override
    public String promptSecureString() {
        Console console = System.console();
        if (console != null) {
            return String.valueOf(console.readPassword(""));
        } else { // if secure input is not supported, fallback to the classic one
            return promptString();
        }
    }

    @Override
    public int promptOption(IMenu menu) {
        return promptNumber();
    }
    // endregion

    // region Logic
    public void invokeAppLogic(IMenu fromMenu, Map<String, String> formData) {
        switch (fromMenu.getIdentifier()) {
        case "login":
            actionLogin(fromMenu, formData);
            break;
        case "register":
            actionRegister(fromMenu, formData);
            break;
        case "task":
            actionTask(fromMenu, formData);
            break;
        case "category":
            actionCategory(fromMenu, formData);
            break;
        case "tag":
            actionTag(fromMenu, formData);
            break;
        }
    }

    public void invokeAppLogic(IMenu fromMenu) {
        switch (fromMenu.getIdentifier()) {
        case "main_menu":
            actionDashboard(fromMenu);
            break;
        }
    }
    // endregion

    // region Actions
    private void actionDashboard(IMenu fromMenu) {
        if (logic.isUserLoggedIn()) {
            drawMessage("Jste přihlášen jako: " + logic.getUserLoggedIn().getUsername());
        } else {
            drawMessage("Nejste přihlášen");
        }
    }

    private void actionLogin(IMenu menu, Map<String, String> data) {
        try {
            logic.loginUser(data.get("email"), data.get("password"));
            drawMessage("Přihlášení proběhlo úspěšně");

        } catch (AlreadyLoggedInException | InvalidCredentialsException e) {
            drawError(e.getMessage());
        }
    }

    private void actionRegister(IMenu menu, Map<String, String> data) {
        try {
            logic.registerUser(data.get("email"), data.get("username"), data.get("password"));
            logic.loginUser(data.get("email"), data.get("password"));
            for(String title : new String[] {"UCL", "JSE", "WEB", "3DT", "PR1", "PES", "Nákupy", "Wishlist"})
                this.logic.createTag(title);
            for(String title : new String[] {"Osobní", "Škola", "Práce"})
                this.logic.createCategory(title);
            // TODO: Default tasks
            logic.logoutUser();
            drawMessage("Registrace proběhla úspěšně");
        } catch (EmailAddressAlreadyUsedException e) {
            drawError(e.getMessage());
        } catch(AlreadyLoggedInException | InvalidCredentialsException | NotLoggedInException e) {}
    }

    private void actionTask(IMenu fromMenu, Map<String, String> formData) {
        try {
            LocalDate date = null;
            try {
                date = LocalDate.parse(formData.get("deadline"), DateTimeFormatter.BASIC_ISO_DATE);
            } catch (DateTimeParseException e) {}
            if (formData.get("task").equals("")) {
                ICategory cat;
                if(!formData.containsKey("category") || formData.get("category") == "") cat = null;
                else cat = this.logic.getCategoryById(Integer.parseInt(formData.get("category")));
                this.logic.createTask(formData.get("title"), formData.get("note"), cat, date);
                drawMessage("Úkol byl úspěšně vytvořen");
            } else {
                int sourceId = Integer.parseInt(formData.get("task"));
                this.logic.updateTask(sourceId, formData.get("title"), formData.get("note"), this.logic.getCategoryById(Integer.parseInt(formData.get("category"))), date);
                drawMessage("Úkol byl úspěšně upraven");
            }
        } catch (NullPointerException e) {
            drawError("Tento úkol již neexistue nebo byl uživatel odhlášen");
        }
    }

    private void actionTag(IMenu fromMenu, Map<String, String> formData) {
        try {
            if (formData.get("tag").equals("")) {
                this.logic.createTag(formData.get("title"), Color.values()[Integer.parseInt(formData.get("color"))]);
                drawMessage("Značka byla úspěšně vytvořena");
            } else {
                int sourceId = Integer.parseInt(formData.get("tag"));
                this.logic.updateTag(sourceId, formData.get("title"), Color.values()[Integer.parseInt(formData.get("color"))]);
                drawMessage("Značka byla úspěšně uložena");
            }
        } catch (NullPointerException e) {
            drawError("Tato značka již neexistuje nebo byl uživatel odhlášen.");
        }
    }

    private void actionCategory(IMenu fromMenu, Map<String, String> formData) {
        try {
            if (formData.get("category").equals("")) {
                this.logic.createCategory(formData.get("title"),
                        Color.values()[Integer.parseInt(formData.get("color"))]);
                drawMessage("Kategorie byla úspěšně vytvořena");
            } else {
                int sourceId = Integer.parseInt(formData.get("category"));
                this.logic.updateCategory(sourceId, formData.get("title"), Color.values()[Integer.parseInt(formData.get("color"))]);
                drawMessage("Kategorie byla úsěšně uložena");
            }
        } catch (NullPointerException e) {
            drawError("Tato kategorie již neexistuje nebo byl uživatel odhlášen");
        }
    }
    // endregion

    // region Handlers
    public IMenu handleMenu(IMenu currentMenu) {
        drawMenu(currentMenu);

        invokeAppLogic(currentMenu);

        IMenuOption selectedOption = handleOptions(currentMenu);

        if (currentMenu.isForm()) {
            return handleMenuAsFormForOption(currentMenu, selectedOption);
        } else {
            return handleMenuForOption(currentMenu, selectedOption);
        }
    }

    private IMenuOption handleOptions(IMenu currentMenu) {
        for (IMenuOption option : currentMenu.getOptions()) {
            this.drawPrompt(option.getNumber() + ": " + option.getTitle());
        }
        System.out.print(" : ");
        int selected = this.promptOption(currentMenu);
        while (!this.isValidOption(selected, currentMenu.getValidOptionNumbers())) {
            this.drawError("Pro tuto možnost neexistuje žádná akce");
            selected = this.promptOption(currentMenu);
        }
        return currentMenu.getOptionForNumber(selected);
    }

    private IMenu handleMenuForOption(IMenu currentMenu, IMenuOption selectedOption) {
        IMenu nextMenu = selectedOption.getMenu();

        if (nextMenu.isSystemMenu()) {
            nextMenu = handleSystemMenuChange(currentMenu, nextMenu);
        } else {
            nextMenu = handleUserMenuChange(currentMenu, nextMenu);
        }

        return nextMenu;
    }

    private String __spacer = null;

    private IMenu handleUserMenuChange(IMenu currentMenu, IMenu nextMenu) {
        if (this.__spacer == null) {
            this.__spacer = "";
            for (int i = 0; i < 10; i++)
                this.__spacer += System.lineSeparator();
        }
        this.drawPrompt(this.__spacer);
        this.drawMenu(nextMenu);
        return nextMenu;
    }

    private IMenu handleMenuAsFormForOption(IMenu currentMenu, IMenuOption selectedOption) {
        IMenu nextMenu = selectedOption.getMenu();

        if (nextMenu.isSystemMenu()) {
            nextMenu = handleSystemMenuChange(currentMenu, nextMenu);
        } else {
            throw new RuntimeException("You cannot use a non-system menu inside the form menus");
        }

        return nextMenu;
    }

    private IMenu handleSystemMenuChange(IMenu currentMenu, IMenu nextMenu) {
        switch (nextMenu.getType()) {
        case SYSTEM_BACK:
            nextMenu = currentMenu.getParentMenu();
            break;
        case SYSTEM_FILL_FORM:
            Map<String, String> formData = handleForm(currentMenu);
            invokeAppLogic(currentMenu, formData);
            nextMenu = currentMenu.getParentMenu();
            break;
        case SYSTEM_QUIT:
            // we will close the application with status code 0 (OK) instead of rendering
            // the menu
            this.sc.close();
            System.exit(0);
            break;
        case SYSTEM_LOGOUT:
            try {
                this.logic.logoutUser();
            } catch (NotLoggedInException e) {
                drawError(e.getMessage());
            } finally {
                nextMenu = currentMenu.getParentMenu();
            }
            break;
        case SYSTEM_DESTROY:
            try {
                DestroyEntityMenu<?> menu = (DestroyEntityMenu<?>)nextMenu;
                if(menu.getEntity() instanceof ITask) this.logic.destroyTask(((ITask)menu.getEntity()).getId());
                if(menu.getEntity() instanceof ICategory) this.logic.destroyCategory(((ICategory)menu.getEntity()).getId());
                if(menu.getEntity() instanceof ITag) this.logic.destroyTag(((ITag)menu.getEntity()).getId());
            } catch (NullPointerException e) {
                drawError(e.getMessage());
            } finally {
                nextMenu = currentMenu.getParentMenu();
            }
            break;
        case SYSTEM_TOGGLE_DONE:
            ToggleDoneMenu menu = (ToggleDoneMenu)nextMenu;
            // For current instance
            if(menu.getEntity().isDone()) menu.getEntity().reopen();
            else menu.getEntity().complete();
            // For persistance
            this.logic.updateTask(menu.getEntity().getId());
            nextMenu = currentMenu;
            break;
        case SYSTEM_ASSIGN:
            AssignTagMenu assignMenu = (AssignTagMenu)nextMenu;
            // For current instance
            if(Stream.of(assignMenu.getTask().getTags()).anyMatch(i -> i.equals(assignMenu.getTag()))) assignMenu.getTask().removeTag(assignMenu.getTag());
            else assignMenu.getTask().addTag(assignMenu.getTag());
            // For persistance
            this.logic.updateTask(assignMenu.getTask().getId(), assignMenu.getTag());
            nextMenu = assignMenu.getParentMenu();
            break;
        default:
            throw new RuntimeException(nextMenu.getType() + " is not valid type of system menu ");
        }
        return nextMenu;
    }
    // endregion

    private Map<String, String> handleForm(IMenu currentMenu) {
        /* Probably worthless - breaks scanner */
        // return this.createFormManagerForMenu(currentMenu).processForm();
        Map<String, String> ret = new HashMap<String, String>();
        for (IFormField field : currentMenu.getFormFields()) {
            String in = null;
            switch(field.getType()) {
                case META:
                    ret.put(field.getIdentifier(), field.getTitle());
                    continue;
                case DATE:
                    in = getInput(field);
                    try {
                        in = LocalDate.parse(in).format(DateTimeFormatter.BASIC_ISO_DATE);
                    } catch (DateTimeParseException e) {
                        drawError("Vložená hodnota není datum");
                    }
                    break;
                case COLOR:
                    Color[] colors = Color.values();
                    this.drawPrompt("Barva: ");
                    StringBuilder clrout = new StringBuilder();
                    for(int i = 1; i <= colors.length; i++) {
                        clrout.append(i + ": " + colors[i-1].name() + "    ");
                    }
                    this.drawPrompt(clrout.toString());
                    int colorIndex = -1;
                    do {
                        this.drawPrompt(field.getLabel());
                        System.out.print(" : ");
                        colorIndex = this.promptNumber();
                    } while(colorIndex <= 0 || colorIndex > colors.length + 1);
                    ret.put(field.getIdentifier(), Integer.toString(colorIndex - 1));
                    continue;
                case CATEGORY_ASSOC:
                    ICategory[] categories = this.logic.getAllCategories();
                    if(categories.length <= 0) continue;
                    this.drawPrompt("Kategorie:");
                    StringBuilder cout = new StringBuilder("1: [bez kategorie]    ");
                    for(int i = 2; i < categories.length + 2; i++) {
                        cout.append(i + ": " + categories[i - 2].getTitle() + "    ");
                        if(i % 4 == 0) cout.append(System.lineSeparator());
                    }
                    this.drawPrompt(cout.toString());
                    int categoryIndex = -1;
                    do {
                        this.drawPrompt(field.getLabel());
                        System.out.print(" : ");
                        categoryIndex = this.promptNumber();
                    } while(categoryIndex <= 0 || categoryIndex > categories.length + 1);
                    if(categoryIndex == 1) ret.put(field.getIdentifier(), "");
                    else ret.put(field.getIdentifier(), Integer.toString(categories[categoryIndex - 2].getId()));
                    continue;
                default:
                    in = getInput(field);
                    break;
            }
            ret.put(field.getIdentifier(), in);
        }
        return ret;
    }

    private String getInput(IFormField field) {
        this.drawPrompt(field.getLabel());
        System.out.print(field.getTitle() + ": ");
        boolean hasInput;
        do {
            hasInput = this.sc.hasNext();
            if (hasInput)
                return this.sc.next();
        } while (field.getIsRequired() && !hasInput);
        return null;
    }

    // region Utilities
    /** Checks if validOptions contains testedOption */
    private boolean isValidOption(int testedOption, int[] validOptions) {
        return IntStream.of(validOptions).anyMatch(x -> x == testedOption);
    }
    // endregion

    // region Draw Methods
    @Override
    public void drawOutput(String output) {
        System.out.println(output);
    }

    @Override
    public void drawMenu(IMenu menuToBeRendered) {
        System.out.println(menuToBeRendered.render());
    }

    @Override
    public void drawMessage(String message) {
        System.out.println(getMenuView().drawMessage(message));
    }

    @Override
    public void drawError(String message) {
        System.out.println(getMenuView().drawError(message));
    }

    @Override
    public void drawPrompt(String message) {
        System.out.println(getMenuView().drawPrompt(message));
    }
    // endregion

    @Override
    public IAppLogic getLogic() {
        return this.logic;
    }

    @Override
    public IMenuFactory getMenuFactory() {
        return this.menuFactory;
    }

    @Override
    public IMenu getMainMenu() {
        return this.menuFactory.createMainMenu(this);
    }

    @Override
    public String getWelcomeText() {
        return "Vítejte v úkolníčku Tasks";
    }

    @Override
    public ICategoryView getCategoryView() {
        return this.categoryView;
    }

    @Override
    public ITagView getTagView() {
        return this.tagView;
    }

    @Override
    public ITaskView getTaskView() {
        return this.taskView;
    }

    @Override
    public IFormView getFormView() {
        return this.formView;
    }

    @Override
    public IMenuView getMenuView() {
        return this.menuView;
    }
}
