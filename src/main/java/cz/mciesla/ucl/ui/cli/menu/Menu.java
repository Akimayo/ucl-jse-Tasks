package cz.mciesla.ucl.ui.cli.menu;

import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.cli.forms.Form;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormField;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Menu implements IMenu {
    private String identifier;
    private String title;
    private String description;
    private List<IMenuOption> options;
    private IForm form;
    private boolean isBuilt;
    private int optionNumber = 0;

    protected IUserInterface ui;
    protected IAppLogic logic;
    protected IMenu parentMenu;

    public Menu(IMenu parentMenu, String identifier, String title) {
        if (parentMenu != null) {
            this.ui = parentMenu.getParentInterface();
            this.logic = this.ui.getLogic();
        }
        this.parentMenu = parentMenu;
        this.identifier = identifier;
        this.title = title;

        this.options = new ArrayList<>();
        this.form = new Form(this);

        this.isBuilt = false;
    }

    /** This method should be used for building the menu - setting description, adding options, etc */
    protected abstract void build();

    @Override
    public void initialize() {
        if (!isBuilt) {
            build();
            isBuilt = true;
        }
    }

    protected void clearOptions() {
        this.options.clear();
        this.optionNumber = 0;
    }

    @Override
    public IMenuOption getOptionForNumber(int optionNumber) {
        return this.options.stream().filter(i -> i.getNumber() == optionNumber).collect(Collectors.toList()).get(0);
    }
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public IAppLogic getLogic() {
        return this.logic;
    }
    @Override
    public boolean isForm() {
        return this.form.getFormFields().length > 0;
    }
    @Override
    public void addOption(IMenuOption option) {
        this.options.add(option);
    }
    @Override
    public MenuType getType() {
        return MenuType.USER;
    }
    @Override
    public String renderFormField(IFormField formField) {
        return String.format("%s%s%s: ", formField.getLabel(), System.lineSeparator(), formField.getTitle());
    }
    @Override
    public int nextOptionNumber() {
        return ++this.optionNumber;
    }
    @Override
    public IUserInterface getParentInterface() {
        return this.ui;
    }
    @Override
    public IMenuOption[] getOptions() {
        return this.options.toArray(new IMenuOption[0]);
    }
    @Override
    public int[] getValidOptionNumbers() {
        return this.options.stream().map(i -> i.getNumber()).mapToInt(Integer::intValue).toArray();
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public IMenu getParentMenu() {
        return this.parentMenu;
    }
    @Override
    public String render() {
        String spacer = "";
        for(int i = 0; i < 34-this.title.length(); i++) spacer += " ";
        // TODO: Improve UI string
        return "===-===-===-===-===-===-===-===-===-===" + System.lineSeparator() +
               "=  " + this.title + spacer + " =" + System.lineSeparator() +
               "===-===-===-===-===-===-===-===-===-===" + System.lineSeparator() +
               (this.description == null ? " " : this.description);
    }
    @Override
    public void addFormField(IFormField field) {
        this.form.addFormField(field);
    }
    @Override
    public boolean isSystemMenu() {
        return this.getType().name().contains("SYSTEM");
    }
    @Override
    public IFormField[] getFormFields() {
        return this.form.getFormFields();
    }
    @Override
    public String getIdentifier() {
        return this.identifier;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
}
