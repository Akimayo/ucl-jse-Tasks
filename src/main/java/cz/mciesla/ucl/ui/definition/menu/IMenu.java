package cz.mciesla.ucl.ui.definition.menu;

import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.forms.IForm;

/**
 * This interface represents a "menu" a.k.a screen in CLI app.
 *
 * IMenu(s) form a tree hierarchy. At the top is the "Main Menu".
 * Every IMenu knows its parent IMenu (if there is any) and its child IMenu(s)
 * When the IMenu does not have child IMenu(s) it is a leaf of the tree hierarchy
 *
 * IMenu can be a IForm if it contains IFormField(s).
 * When IMenu's getFormFields() returns empty array, the IMenu is not considered as form
 *
 * The form part of IMenu is always shown first (by this IUserInterface), followed by the IMenuOption listing.
 * From the options listing user either chooses next IMenu (via IMenuOption) or exists the app.
 */
public interface IMenu extends IForm {
    /** Returns system identifier of the menu (eg. "login"). Identifiers should be lowercase and underscore_case */
    String getIdentifier();

    /** Returns menu title as string (to be included in rendered breadcrumbs) */
    String getTitle();

    /** Returns menu description as string (to be included in rendered menu) */
    String getDescription();

    /** Sets menu description */
    void setDescription(String description);

    /** Returns parent IMenu or null in case of main menu */
    IMenu getParentMenu();

    /** Returns array of IMenuOption(s) which will be used as options or null if menu has no options (eg. is a form) */
    IMenuOption[] getOptions();

    /** Returns integer array with valid numbers for options */
    int[] getValidOptionNumbers();

    /** Returns IMenuOption for menu option number or null if no option for provided number exists */
    IMenuOption getOptionForNumber(int optionNumber);

    /** Adds IMenuOption to the IMenu */
    void addOption(IMenuOption option);

    /** Returns number of next IMenuOption that could be added to the IMenu */
    int nextOptionNumber();

    /**
     * This method is called before the menu is shown, here menu can be built - description and options can be set.
     * This method should build the menu if needed
     */
    void initialize();

    /** Returns rendered menu as string (to be shown in console) */
    String render();

    /** Every IMenu should use same application logic as the IUserInterface which is using the IMenu */
    IAppLogic getLogic();

    /**
     * Every IMenu should be able use the promptXXX() methods and views from the parent IUserInterface.
     * Hence it has to have a link to the parent IUserInterface which owns the IMenu
     */
    IUserInterface getParentInterface();

    /** Returns true if this menu is a system menu (back, fill, quit) or false otherwise */
    boolean isSystemMenu();

    /**
     * Returns MenuType of the menu. Default value (used by user menus) is USER. Other values are reserved
     * for system purposes - like back, fill_form or quit
     */
    MenuType getType();
}
