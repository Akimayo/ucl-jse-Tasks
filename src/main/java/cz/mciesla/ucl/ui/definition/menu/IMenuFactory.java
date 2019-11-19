package cz.mciesla.ucl.ui.definition.menu;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.ui.definition.IUserInterface;

public interface IMenuFactory {
    IMenu createMainMenu(IUserInterface ui);
    IMenu createQuitMenu(IMenu parentMenu);
    IMenu createBackMenu(IMenu parentMenu);
    IMenu createFillFormMenu(IMenu parentMenu);

    IMenu createLoginFormMenu(IMenu parentMenu);
    IMenu createRegistrationFormMenu(IMenu parentMenu);

    IMenu createTasksMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createTaskFormMenu(IMenu parentMenu, IUserInterface ui, Task task);

    IMenu createCategoriesMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createCategoryFormMenu(IMenu parentMenu, IUserInterface ui, Category task);

    IMenu createTagsMenu(IMenu parentMenu, IUserInterface ui);
    IMenu createTagFormMenu(IMenu parentMenu, IUserInterface ui, Tag tag);

    IMenu createSettingsMenu(IMenu parentMenu, IUserInterface iu);
    IMenu createLogoutMenu(IMenu parentMenu, IUserInterface ui);

    <T> IMenu createListMenu(IMenu parentMenu, IUserInterface ui, String title, T[] entitites);
	IMenu createLogout(IMenu parentMenu);
}
