package cz.mciesla.ucl.ui.definition.menu;

public interface IMenuOption {
    /** Returns option number */
    int getNumber();

    /** Returns option name */
    String getTitle();

    /** Returns IMenu which should be used when option is selected by the user */
    IMenu getMenu();
}
