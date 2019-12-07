package cz.mciesla.ucl.ui.cli.menu;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.ui.cli.forms.FormField;
import cz.mciesla.ucl.ui.cli.menu.system.AssignTagMenu;
import cz.mciesla.ucl.ui.cli.menu.system.BackMenu;
import cz.mciesla.ucl.ui.cli.menu.system.DestroyEntityMenu;
import cz.mciesla.ucl.ui.cli.menu.system.FillFormMenu;
import cz.mciesla.ucl.ui.cli.menu.system.LogoutMenu;
import cz.mciesla.ucl.ui.cli.menu.system.QuitMenu;
import cz.mciesla.ucl.ui.cli.menu.system.SetOrderMenu;
import cz.mciesla.ucl.ui.cli.menu.system.ToggleDoneMenu;
import cz.mciesla.ucl.ui.cli.menu.user.CategoriesMenu;
import cz.mciesla.ucl.ui.cli.menu.user.ConfirmLogoutMenu;
import cz.mciesla.ucl.ui.cli.menu.user.ConfirmDestroyEntityMenu;
import cz.mciesla.ucl.ui.cli.menu.user.ListMenu;
import cz.mciesla.ucl.ui.cli.menu.user.MainMenu;
import cz.mciesla.ucl.ui.cli.menu.user.SettingsMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TagsMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TaskTagsMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TasksMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TasksOrderMenu;
import cz.mciesla.ucl.ui.cli.menu.user.TutorialMenu;
import cz.mciesla.ucl.ui.cli.menu.user.UserEditMenu;
import cz.mciesla.ucl.ui.cli.menu.user.detail.CategoryDetailMenu;
import cz.mciesla.ucl.ui.cli.menu.user.detail.TagDetailMenu;
import cz.mciesla.ucl.ui.cli.menu.user.detail.TaskDetailMenu;
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
        return new BackMenu(parentMenu, "< Zpět");
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
    public IMenu createTaskFormMenu(IMenu parentMenu, IUserInterface ui, ITask task) {
        return new FormMenu(parentMenu, "task", task == null ? "Vytvořit úkol" : "Upravit tento úkol") {
            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(
                        new FormField("task", task == null ? "" : Integer.toString(task.getId()), FormFieldType.META));
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("note", "Poznámky", "Prosím zadejte poznámku", FormFieldType.TEXTUAL, false));
                addFormField(new FormField("deadline", "Termín splnění", "Prosím zadejte termín splnění [YYYY-MM-DD]",
                        FormFieldType.DATE, false));
                addFormField(
                        new FormField("category", "Kategorie", "Zvolte kategorii", FormFieldType.CATEGORY_ASSOC, true));
            }
        };
    }

    @Override
    public IMenu createCategoriesMenu(IMenu parentMenu, IUserInterface ui) {
        return new CategoriesMenu(parentMenu, ui, "Kategorie");
    }

    @Override
    public IMenu createCategoryFormMenu(IMenu parentMenu, IUserInterface ui, ICategory category) {
        return new FormMenu(parentMenu, "category",
                category == null ? "Vytvořit kategorii" : "Upravit tuto kategorii") {

            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(new FormField("category", category == null ? "" : Integer.toString(category.getId()),
                        FormFieldType.META));
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("color", "Barva", FormFieldType.COLOR));
            }
        };
    }

    @Override
    public IMenu createTagsMenu(IMenu parentMenu, IUserInterface ui) {
        return new TagsMenu(parentMenu, ui, "Značky");
    }

    @Override
    public IMenu createTagFormMenu(IMenu parentMenu, IUserInterface ui, ITag tag) {
        return new FormMenu(parentMenu, "tag", tag == null ? "Vytvořit značku" : "Upravit tuto značku") {

            @Override
            protected void build() {
                IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
                IMenu fillMenu = ui.getMenuFactory().createFillFormMenu(parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(
                        new FormField("tag", tag == null ? "" : Integer.toString(tag.getId()), FormFieldType.META));
                addFormField(new FormField("title", "Název", FormFieldType.TEXTUAL));
                addFormField(new FormField("color", "Barva", "Prosím vyberte barvu: ", FormFieldType.COLOR, true));
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

    @Override
    public IMenu createTaskDetailMenu(IMenu listMenu, IUserInterface ui, ITask e) {
        return new TaskDetailMenu(listMenu, ui, e);
    }

    @Override
    public IMenu createCategoryDetailMenu(IMenu listMenu, IUserInterface ui, ICategory e) {
        return new CategoryDetailMenu(listMenu, ui, e);
    }

    @Override
    public IMenu createTagDetailMenu(IMenu listMenu, IUserInterface ui, ITag e) {
        return new TagDetailMenu(listMenu, ui, e);
    }

    @Override
    public <T> IMenu createConfirmDestroyMenu(IMenu parentMenu, IUserInterface ui, T entity) {
        return new ConfirmDestroyEntityMenu<T>(parentMenu, ui, entity);
    }

    @Override
    public <T> IMenu createDestroyMenu(IMenu parentMenu, IUserInterface ui, T entity) {
        if (entity instanceof ITask)
            return new DestroyEntityMenu<ITask>(parentMenu, ui, (ITask) entity);
        if (entity instanceof ICategory)
            return new DestroyEntityMenu<ICategory>(parentMenu, ui, (ICategory) entity);
        if (entity instanceof ITag)
            return new DestroyEntityMenu<ITag>(parentMenu, ui, (ITag) entity);
        if (entity instanceof IUser)
            return new DestroyEntityMenu<IUser>(parentMenu, ui, (IUser) entity);
        return null;
    }

    @Override
    public IMenu createMarkDoneMenu(TaskDetailMenu taskDetailMenu, ITask task) {
        return new ToggleDoneMenu(taskDetailMenu, task);
    }

    @Override
    public IMenu createTagAssignMenu(IMenu parentMenu, IUserInterface ui, ITag tag, ITask task) {
        return new AssignTagMenu(parentMenu, ui, tag, task);
    }

    @Override
    public IMenu createTaskTagsMenu(IMenu parentMenu, IUserInterface ui, ITask task) {
        return new TaskTagsMenu(parentMenu, ui, task);
    }

    @Override
    public IMenu createUserEditMenu(IMenu parentMenu, IUserInterface ui) {
        return new UserEditMenu(parentMenu, ui);
    }

    @Override
    public IMenu createUserEditFormMenu(IMenu parentMenu, IUserInterface ui) {
        return new FormMenu(parentMenu, "user_edit", "Upravit účet") {

            @Override
            protected void build() {
                IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
                IMenu fillMenu = this.ui.getMenuFactory().createFillFormMenu(this.parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }

            @Override
            protected void defineForm() {
                addFormField(new FormField("username", "Uživatelské jméno", FormFieldType.TEXTUAL));
                addFormField(new FormField("email", "E-mail", FormFieldType.TEXTUAL));
                addFormField(new FormField("old_password", "Staré heslo", FormFieldType.SECURE));
                addFormField(new FormField("new_password", "Nové heslo", FormFieldType.SECURE));
                addFormField(new FormField("new_confirm", "Potvrdit nové heslo", FormFieldType.SECURE));
            }
        };
    }

    @Override
    public IMenu createUserDestroyConfirmMenu(IMenu parentMenu, IUserInterface ui) {
        return new ConfirmDestroyEntityMenu<IUser>(parentMenu, ui, ui.getLogic().getUserLoggedIn());
    }

    @Override
    public IMenu createUserDestroyMenu(IMenu mainMenu, IUserInterface ui) {
        return new DestroyEntityMenu<IUser>(mainMenu, ui, ui.getLogic().getUserLoggedIn());
    }

    @Override
    public IMenu createSetTasksOrderMenu(IMenu parentMenu) {
        return new SetOrderMenu(parentMenu);
    }

    @Override
    public IMenu createTasksOrderMenu(IMenu parentMenu, IUserInterface ui) {
        return new TasksOrderMenu(parentMenu, ui);
    }

    @Override
    public IMenu createFilterFormMenu(IMenu parentMenu, IUserInterface ui, boolean clearFilter) {
        return new FormMenu(parentMenu, "filter", clearFilter ? "Zrušit filtry" : "Filtrovat úkoly podle...") {
        
            @Override
            protected void build() {
                IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
                IMenu fillMenu = this.ui.getMenuFactory().createFillFormMenu(this.parentMenu);

                addOption(new MenuOption(nextOptionNumber(), backMenu));
                addOption(new MenuOption(nextOptionNumber(), fillMenu));
            }
        
            @Override
            protected void defineForm() {
                if(!clearFilter) {
                    addFormField(new FormField("type", "Filtrovat podle", "Filtrovat podle", FormFieldType.FILTER, true));
                    addFormField(new FormField("category", "Kategorie", "Název kategorie", FormFieldType.TEXTUAL, false));
                    addFormField(new FormField("tags", "Značky", "Názvy značek (oddělené čárkou)", FormFieldType.TEXTUAL, false));
                } else addFormField(new FormField("clear", "", FormFieldType.META));
            }
        };
    }

    @Override
    public IMenu createTutorial(IMenu parentMenu) {
        return new TutorialMenu(parentMenu);
    }

}
