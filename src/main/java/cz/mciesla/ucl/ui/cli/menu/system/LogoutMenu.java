package cz.mciesla.ucl.ui.cli.menu.system;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

/** This menu represents the "quit application" action */
public class LogoutMenu extends Menu {
    public LogoutMenu(IMenu parentMenu, String title) {
        super(parentMenu, "logout", title);
    }

    @Override
    protected void build() {
        // intentionally no operation here
    }

    @Override
    public boolean isSystemMenu() {
        return true;
    }

    @Override
    public MenuType getType() {
        return MenuType.SYSTEM_LOGOUT;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the LogoutMenu class. Check your UI logic implementation.");
    }
}
