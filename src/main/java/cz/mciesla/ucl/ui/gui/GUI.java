package cz.mciesla.ucl.ui.gui;

import java.util.Map;

import javax.swing.JFrame;

import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormManager;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;
import cz.mciesla.ucl.ui.definition.views.ICategoryView;
import cz.mciesla.ucl.ui.definition.views.IFormView;
import cz.mciesla.ucl.ui.definition.views.IMenuView;
import cz.mciesla.ucl.ui.definition.views.ITagView;
import cz.mciesla.ucl.ui.definition.views.ITaskView;
import cz.mciesla.ucl.ui.gui.menu.MenuFactory;
import cz.mciesla.ucl.ui.gui.views.CategoryView;
import cz.mciesla.ucl.ui.gui.views.FormView;
import cz.mciesla.ucl.ui.gui.views.MenuView;
import cz.mciesla.ucl.ui.gui.views.TagView;
import cz.mciesla.ucl.ui.gui.views.TaskView;

/**
 * GUI
 */
public class GUI implements IGUI {
    private JFrame window;

    private IMenuFactory menuFactory;
    private IAppLogic logic;

    private CategoryView categoryView;
    private TagView tagView;
    private TaskView taskView;
    private MenuView menuView;
    private FormView formView;

    public GUI() {
        this.menuFactory = new MenuFactory();

        this.categoryView = new CategoryView();
        this.tagView = new TagView();
        this.taskView = new TaskView();
        this.menuView = new MenuView();
        this.formView = new FormView();
    }

    @Override
    public void run(IAppLogic logic) {
        this.logic = logic;
        this.window = new JFrame("Tasks");
        this.window.setSize(1280, 720);
        this.window.setVisible(true);
        
        this.drawMessage(this.getWelcomeText());

        IMenu currentMenu;
        IMenu nextMenu = this.getMainMenu();
        do {
            currentMenu = nextMenu;
            currentMenu.initialize();
            nextMenu = this.handleMenu(currentMenu);
        } while (nextMenu != null);

        this.window.setVisible(false);
        System.exit(0);
    }

    @Override
    public IAppLogic getLogic() {
        return this.logic;
    }

    @Override
    public IMenuFactory getMenuFactory() {
        return this.menuFactory;
    }

    @Override
    public void invokeAppLogic(IMenu fromMenu, Map<String, String> formData) {
        // TODO Auto-generated method stub

    }

    @Override
    public void invokeAppLogic(IMenu fromMenu) {
        // TODO Auto-generated method stub

    }

    @Override
    public IMenu getMainMenu() {
        return this.menuFactory.createMainMenu(this);
    }

    @Override
    public String getWelcomeText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICategoryView getCategoryView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITagView getTagView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITaskView getTaskView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IFormView getFormView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMenuView getMenuView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IFormManager createFormManagerForMenu(IForm menu) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int promptNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String promptString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String promptSecureString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int promptOption(IMenu menu) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void drawOutput(String output) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawMenu(IMenu menuToBeRendered) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawMessage(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawError(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawPrompt(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public IMenu handleMenu(IMenu currentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    
}