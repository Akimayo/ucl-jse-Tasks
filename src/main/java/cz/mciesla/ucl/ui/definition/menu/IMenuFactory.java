package cz.mciesla.ucl.ui.definition.menu;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.user.detail.TaskDetailMenu;
import cz.mciesla.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);

    IMenu createTasksMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createTaskFormMenu(IMenu parentMenu, IUserInterface ui, ITask task);

    IMenu createCategoriesMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createCategoryFormMenu(IMenu parentMenu, IUserInterface ui, ICategory category);

    IMenu createTagsMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createTagFormMenu(IMenu parentMenu, IUserInterface ui, ITag tag);

    IMenu createSettingsMenu(IMenu parentMenu, IUserInterface iu);
    IMenu createLogoutMenu(IMenu parentMenu, IUserInterface ui);

    <T> IMenu createListMenu(Class<?> type, IMenu parentMenu, IUserInterface ui, String title);
	IMenu createLogout(IMenu parentMenu);
	IMenu createTaskDetailMenu(IMenu listMenu, IUserInterface ui, ITask e);
	IMenu createCategoryDetailMenu(IMenu listMenu, IUserInterface ui, ICategory e);
	IMenu createTagDetailMenu(IMenu listMenu, IUserInterface ui, ITag e);
    <T> IMenu createConfirmDestroyMenu(IMenu parentMenu, IUserInterface ui, T entity);
	<T> IMenu createDestroyMenu(IMenu parentMenu, IUserInterface ui, T entity);
	IMenu createMarkDoneMenu(TaskDetailMenu taskDetailMenu, ITask task);
	IMenu createTagAssignMenu(IMenu parentMenu, IUserInterface ui, ITag tag, ITask task);
    IMenu createTaskTagsMenu(IMenu parentMenu, IUserInterface ui, ITask task);
    IMenu createUserEditMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createUserEditFormMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createUserDestroyConfirmMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createUserDestroyMenu(IMenu mainMenu, IUserInterface ui);
	IMenu createSetTasksOrderMenu(IMenu parentMenu);
	IMenu createTasksOrderMenu(IMenu parentMenu, IUserInterface ui);
	IMenu createFilterFormMenu(IMenu parentMenu, IUserInterface ui, boolean clearFilter);
	IMenu createTutorial(IMenu parentMenu);
}
