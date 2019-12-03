package cz.mciesla.ucl.ui.cli.menu.system;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

public class ToggleDoneMenu extends Menu {
    private ITask entity;

    public ToggleDoneMenu(IMenu parentMenu, ITask entity) {
        super(parentMenu, "toggle_done", entity.isDone() ? "Otevřít" : "Dokončit");
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
        return MenuType.SYSTEM_TOGGLE_DONE;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the LogoutMenu class. Check your UI logic implementation.");
    }

    public ITask getEntity() {
        return entity;
    }
}
