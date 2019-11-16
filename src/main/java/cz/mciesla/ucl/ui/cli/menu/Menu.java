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

    // TODO
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
        // WTF: TODO: Figure out types
        return null;
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
        // TODO: Improve UI string
        return "===-===-===-===-===-===-===-===-===-===" + System.lineSeparator() +
               "=  " + this.title + " =" + System.lineSeparator() +
               "===-===-===-===-===-===-===-===-===-===" + System.lineSeparator() +
               (this.description == null ? " " : this.description);
    }
    @Override
    public void addFormField(IFormField field) {
        this.form.addFormField(field);
    }
    @Override
    public boolean isSystemMenu() {
        // WTF:
        switch(this.identifier) {
            case "back_menu":
            case "fill_form_menu":
            case "quit_menu":
                return true;
            default:
                return false;
        }
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
