package cz.mciesla.ucl.ui.definition.menu;

/**
 * This enumeration describes IMenu type
 *
 * USER indicates that the IMenu is not system menu, but user menu (this is default situation)
 * SYSTEM_XXX indicates that the IMenu is one of the system menus (like back, fill_form and quit)
 */
public enum MenuType {
    USER, SYSTEM_BACK, SYSTEM_FILL_FORM, SYSTEM_QUIT, SYSTEM_LOGOUT, SYSTEM_DESTROY, SYSTEM_TOGGLE_DONE, SYSTEM_ASSIGN
}
