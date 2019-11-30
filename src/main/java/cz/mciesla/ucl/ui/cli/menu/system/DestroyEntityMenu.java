package cz.mciesla.ucl.ui.cli.menu.system;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

public class DestroyEntityMenu<T> extends Menu {

    T entity;

    public DestroyEntityMenu(IMenu parentMenu, IUserInterface ui, T entity) {
        super(parentMenu, "destroy", "Odstranit");
        this.entity = entity;
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
        return MenuType.SYSTEM_DESTROY;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the LogoutMenu class. Check your UI logic implementation.");
    }

    public T getEntity() {
        return entity;
    }

}
