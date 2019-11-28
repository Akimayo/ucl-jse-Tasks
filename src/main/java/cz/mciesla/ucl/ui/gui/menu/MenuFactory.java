package cz.mciesla.ucl.ui.gui.menu;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;

public class MenuFactory implements IMenuFactory {

    @Override
    public IMenu createMainMenu(IUserInterface ui) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createQuitMenu(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createBackMenu(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createFillFormMenu(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createLoginFormMenu(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createRegistrationFormMenu(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createTasksMenu(IMenu parentMenu, IUserInterface ui) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createTaskFormMenu(IMenu parentMenu, IUserInterface ui, Task task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createCategoriesMenu(IMenu parentMenu, IUserInterface ui) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createCategoryFormMenu(IMenu parentMenu, IUserInterface ui, Category task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createTagsMenu(IMenu parentMenu, IUserInterface ui) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createTagFormMenu(IMenu parentMenu, IUserInterface ui, Tag tag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createSettingsMenu(IMenu parentMenu, IUserInterface iu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createLogoutMenu(IMenu parentMenu, IUserInterface ui) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> IMenu createListMenu(Class<?> task, IMenu parentMenu, IUserInterface ui, String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenu createLogout(IMenu parentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

}
