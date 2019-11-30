package cz.mciesla.ucl.ui.cli;

import cz.mciesla.ucl.logic.exceptions.AlreadyLoggedInException;
import cz.mciesla.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.mciesla.ucl.logic.exceptions.InvalidCredentialsException;
import cz.mciesla.ucl.logic.exceptions.NotLoggedInException;
import cz.mciesla.ucl.ui.cli.forms.FormManager;
import cz.mciesla.ucl.ui.cli.menu.MenuFactory;
import cz.mciesla.ucl.ui.cli.menu.system.DestroyEntityMenu;
import cz.mciesla.ucl.ui.cli.views.*;
import cz.mciesla.ucl.ui.definition.forms.FormFieldType;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormField;
import cz.mciesla.ucl.ui.definition.forms.IFormManager;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;
import cz.mciesla.ucl.ui.definition.views.*;

import java.io.Console;
import java.util.*;
import java.util.stream.IntStream;

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
        // WTF: Y U NO WORK?!
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
        // TODO: Add all other app logics
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
            drawMessage("Registrace proběhla úspěšně");
        } catch (EmailAddressAlreadyUsedException e) {
            drawError(e.getMessage());
        }
    }

    private void actionTask(IMenu fromMenu, Map<String, String> formData) {
        try {
            ITask seTask;
            if (formData.get("task").equals("")) {
                seTask = new Task(formData.get("title"), formData.get("note"), null);
                this.logic.getUserLoggedIn().addTask(seTask);
                drawMessage("Úkol byl úspěšně vytvořen");
            } else {
                int sourceId = Integer.parseInt(formData.get("task"));
                ITask sourceTask = this.logic.getUserLoggedIn().getTask(sourceId);
                String title = formData.get("title").equals("") ? sourceTask.getTitle() : formData.get("title");
                String note = formData.get("note").equals("") ? sourceTask.getNote() : formData.get("note");
                this.logic.getUserLoggedIn().saveTask(sourceId, new Task(title, note, sourceTask.getCategory()));
                drawMessage("Úkol byl úspěšně upraven");
            }
        } catch (NullPointerException e) {
            drawError("Tento úkol již neexistue nebo byl uživatel odhlášen");
        }
    }

    private void actionTag(IMenu fromMenu, Map<String, String> formData) {
        try {
            ITag seTag;
            if (formData.get("tag").equals("")) {
                seTag = new Tag(formData.get("title"), Color.values()[Integer.parseInt(formData.get("color"))]);
                this.logic.getUserLoggedIn().addTag(seTag);
                drawMessage("Značka byla úspěšně vytvořena");
            } else {
                int sourceId = Integer.parseInt(formData.get("tag"));
                ITag sourceTag = this.logic.getUserLoggedIn().getTag(sourceId);
                String title = formData.get("title").equals("") ? sourceTag.getTitle() : formData.get("title");
                Color color = formData.get("color").equals("") ? sourceTag.getColor()
                        : Color.values()[Integer.parseInt(formData.get("color"))];
                this.logic.getUserLoggedIn().saveTag(sourceId, new Tag(title, color));
                drawMessage("Značka byla úspěšně uložena");
            }
        } catch (NullPointerException e) {
            drawError("Tato značka již neexistuje nebo byl uživatel odhlášen.");
        }
    }

    private void actionCategory(IMenu fromMenu, Map<String, String> formData) {
        try {
            ICategory seCategory;
            if (formData.get("category").equals("")) {
                seCategory = new Category(formData.get("title"),
                        Color.values()[Integer.parseInt(formData.get("color"))]);
                this.logic.getUserLoggedIn().addCategory(seCategory);
                drawMessage("Kategorie byla úspěšně vytvořena");
            } else {
                int sourceId = Integer.parseInt(formData.get("category"));
                ICategory sourceCategory = this.logic.getUserLoggedIn().getCategory(sourceId);
                String title = formData.get("title").equals("") ? sourceCategory.getTitle() : formData.get("title");
                Color color = formData.get("color").equals("") ? sourceCategory.getColor()
                        : Color.values()[Integer.parseInt(formData.get("color"))];
                this.logic.getUserLoggedIn().saveCategory(sourceId, new Category(title, color));
                drawMessage("Kategorie byla úsěšně uložena");
            }
        } catch (NullPointerException e) {
            drawError("Tato kategorie již neexistuje nebo byl uživatel odhlášen");
        }
    }

    // TODO: Add actions
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
        // WTF: What's the purpose of currentMenu?
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

    // TODO: Add handlers

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
                if(menu.getEntity() instanceof ITask) this.logic.getUserLoggedIn().saveTask(((ITask)menu).getId(), null);
                if(menu.getEntity() instanceof ICategory) this.logic.getUserLoggedIn().saveCategory(((ICategory)menu).getId(), null);
                if(menu.getEntity() instanceof ITag) this.logic.getUserLoggedIn().saveTag(((ITag)menu).getId(), null);
            } catch (NullPointerException e) {
                drawError(e.getMessage());
            } finally {
                nextMenu = currentMenu.getParentMenu();
            }
            break;
        default:
            throw new RuntimeException(nextMenu.getType() + " is not valid type of system menu ");
        }
        return nextMenu;
    }

    // TODO: Add handlers

    // endregion

    private Map<String, String> handleForm(IMenu currentMenu) {
        /* Probably worthless - breaks scanner */
        // return this.createFormManagerForMenu(currentMenu).processForm();
        Map<String, String> ret = new HashMap<String, String>();
        for (IFormField field : currentMenu.getFormFields()) {
            if (field.getType() == FormFieldType.META) {
                ret.put(field.getIdentifier(), field.getTitle());
                continue;
            }
            this.drawPrompt(field.getLabel());
            System.out.print(field.getTitle() + ": ");
            String in = null;
            boolean hasInput;
            do {
                hasInput = this.sc.hasNext();
                if (hasInput)
                    in = this.sc.next();
            } while (field.getIsRequired() && !hasInput);
            ret.put(field.getIdentifier(), in);
        }
        return ret;
    }

    // region Utilities
    /* * Checks if validOptions contains testedOption */
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
        // WTF: What?!
        // Potential FIXME: Move string literal to a constant
        // Potential TODO: Make the text a tad better
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
