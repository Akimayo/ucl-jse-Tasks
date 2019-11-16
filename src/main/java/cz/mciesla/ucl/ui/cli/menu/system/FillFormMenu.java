package cz.mciesla.ucl.ui.cli.menu.system;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

public class FillFormMenu extends Menu {
    public FillFormMenu(IMenu parentMenu, String title) {
        super(parentMenu, "fill_form", title);
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
        return MenuType.SYSTEM_FILL_FORM;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the FillFormMenu class. Check your UI logic implementation.");
    }
}
