package cz.mciesla.ucl.ui.definition;

import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.definition.forms.IForm;
import cz.mciesla.ucl.ui.definition.forms.IFormManager;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuFactory;
import cz.mciesla.ucl.ui.definition.views.*;

import java.util.Map;

public interface IUserInterface {
    // Public Methods
    /** This method is "the application". The code in Main.java will call this method and passes the IAppLogic
     * implementation into it
     */
    void run(IAppLogic logic);

    /** Returns IAppLogic used by this user interface */
    IAppLogic getLogic();

    /** Returns IMenuFactory used by this user interface */
    IMenuFactory getMenuFactory();

    // "Private" Methods
    /**
     * Methods below are "private" methods, we are exposing them via interface in order to make them testable via Unit Tests
     * In real application, these methods would be private
     */

    /** This method is used by the UI to invoke IAppLogic as a reaction to form filling by the user */
    void invokeAppLogic(IMenu fromMenu, Map<String, String> formData);

    /** This method is used by the UI to invoke IAppLogic as a reaction to enter particular menu by the user */
    void invokeAppLogic(IMenu fromMenu);

    /** This method should return the main menu IMenu instance. Rest of the IMenu instances are loaded from this menu */
    IMenu getMainMenu();

    /** This method should return textual message shown on the application start */
    String getWelcomeText();

    //region Views
    /** This method should return the ICategoryView instance which will be used for Category formatting */
    ICategoryView getCategoryView();

    /** This method should return the ITagView instance which will be used for Tag formatting */
    ITagView getTagView();

    /** This method should return the ITaskView instance which will be used for Task formatting */
    ITaskView getTaskView();

    /** This method should return the IFormView instance which will be used for Form formatting */
    IFormView getFormView();

    /** This method should return the IMenuView instance which will be used for Menu formatting */
    IMenuView getMenuView();
    //endregion

    //region Forms
    /** This method should create the IFormManager instance for the passed IMenu */
    IFormManager createFormManagerForMenu(IForm menu);
    //endregion

    //region Prompts
    /** This method should load numerical (integer) input from the user */
    int promptNumber();

    /** This method should load textual input from the user */
    String promptString();

    /** This method should load textual input from the user in a secure way */
    String promptSecureString();

    /**
     * This method should load option (numerical menu option) input from the user.
     * The method will load prompt and available options from the passed IMenu instance
     */
    int promptOption(IMenu menu);
    //endregion

    //region Draw Methods
    /** This method should draw a textual on screen (like rendered mneu options, etc) */
    void drawOutput(String output);

    /** This method should draw a IMenu on screen */
    void drawMenu(IMenu menuToBeRendered);

    /** This method should display a message on screen */
    void drawMessage(String message);

    /** This method should display an error message on screen */
    void drawError(String message);

    /** This method should display a prompt for a value to the user */
    void drawPrompt(String message);
    //endregion
}
