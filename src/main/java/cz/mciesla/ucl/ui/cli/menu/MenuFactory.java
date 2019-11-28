package cz.mciesla.ucl.ui.cli.menu;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.ui.cli.forms.FormField;
import cz.mciesla.ucl.ui.cli.menu.system.BackMenu;
import cz.mciesla.ucl.ui.cli.menu.system.FillFormMenu;
import cz.mciesla.ucl.ui.cli.menu.system.QuitMenu;
import cz.mciesla.ucl.ui.cli.menu.user.MainMenu;
import cz.mciesla.ucl.ui.cli.menu.user.SettingsMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TagsMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TasksMenu;
import cz.mciesla.ucl.ui.cli.menu.user.CategoriesMenu;
import cz.mciesla.ucl.ui.cli.menu.user.ConfirmLogoutMenu;
import cz.mciesla.ucl.ui.cli.menu.user.ListMenu;
import cz.mciesla.ucl.ui.cli.menu.system.LogoutMenu;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.forms.FormFieldType;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;

public class MenuFactory implements IMenuFactory {
    @Override
    public IMenu createMainMenu(IUserInterface ui) {
        return new MainMenu(ui, "Hlavní nabídka");
    }

    @Override
    public IMenu createQuitMenu(IMenu parentMenu) {
        return new QuitMenu(parentMenu, "Ukončit aplikaci");
    }

    @Override
    public IMenu createBackMenu(IMenu parentMenu) {
        return new BackMenu(parentMenu, "Zpět");
    }

    @Override
    public IMenu createFillFormMenu(IMenu parentMenu) {
        return new FillFormMenu(parentMenu, "Vyplnit formulář");
    }

    @Override
    public IMenu createLoginFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "login", "Přihlásit se") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("email", "E-Mail", FormFieldType.TEXTUAL));
                addFormField(new FormField("password", "Heslo", FormFieldType.SECURE));
            }

            @Override
            protected void build() {
                setDescription("Pro přihlášení je třeba zadat uživatelské jméno a heslo.");

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createRegistrationFormMenu(IMenu parentMenu) {
        return new FormMenu(parentMenu, "register", "Registrovat se") {
            @Override
            protected void defineForm() {
                addFormField(new FormField("email", "E-Mail", FormFieldType.TEXTUAL));
                addFormField(new FormField("username", "Uživatelské jméno", FormFieldType.TEXTUAL));
                addFormField(new FormField("password", "Heslo", FormFieldType.SECURE));
            }

            @Override
            protected void build() {
                setDescription("Pro registraci je třeba zadat e-mail, uživatelské jméno a heslo.");

                IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(this);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        };
    }

    @Override
    public IMenu createTasksMenu(IMenu parentMenu, IUserInterface ui) {
        return new TasksMenu(parentMenu, ui, "Úkoly");
    }

    @Override
    public IMenu createTaskFormMenu(IMenu parentMenu, IUserInterface ui, Task task) {
        return new FormMenu(parentMenu, "task", task == null ? "Vytvořit úkol" : "> " + task.getTitle()) {
            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(new FormField("task", task == null ? "" : Integer.toString(task.getId()), FormFieldType.META));
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("note", "Poznámky", FormFieldType.TEXTUAL));
                // TODO: Add more fields
            }
        };
    }

    @Override
    public IMenu createCategoriesMenu(IMenu parentMenu, IUserInterface ui) {
        return new CategoriesMenu(parentMenu, ui, "Kategorie");
    }

    @Override
    public IMenu createCategoryFormMenu(IMenu parentMenu, IUserInterface ui, Category category) {
        return new FormMenu(parentMenu, "category", category == null ? "Vytvořit kategorii" : "Upravit kategorii") {

            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("color", "Barva", FormFieldType.TEXTUAL)); // FIXME: Create new field type for colors
            }
        };
    }

    @Override
    public IMenu createTagsMenu(IMenu parentMenu, IUserInterface ui) {
        return new TagsMenu(parentMenu, ui, "Značky");
    }

    @Override
    public IMenu createTagFormMenu(IMenu parentMenu, IUserInterface ui, Tag tag) {
        return new FormMenu(parentMenu, "tag", tag == null ? "Vytvořit značku" : "Upravit značku") {

            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("color", "Barva", FormFieldType.TEXTUAL)); // FIXME: Create new field type for colors
            }
        };
    }

    @Override
    public IMenu createSettingsMenu(IMenu parentMenu, IUserInterface ui) {
        return new SettingsMenu(parentMenu, ui, "Nastavení");
    }

    @Override
    public IMenu createLogoutMenu(IMenu parentMenu, IUserInterface ui) {
        return new ConfirmLogoutMenu(parentMenu, ui, "Odhlásit se");
    }

    @Override
    public <T> IMenu createListMenu(Class<?> type, IMenu parentMenu, IUserInterface ui, String title) {
        return new ListMenu<T>(type, parentMenu, ui, title);
    }

    @Override
    public IMenu createLogout(IMenu parentMenu) {
        return new LogoutMenu(parentMenu, "Odhlásit se");
    }

}
