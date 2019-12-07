package cz.mciesla.ucl.ui.cli.menu.system;

import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

/**
 * SetOrderMenu
 */
public class SetOrderMenu extends Menu {
    private TasksOrder order;

    public SetOrderMenu(IMenu parentMenu) {
        super(parentMenu, "set_order", TasksOrder.BY_TITLE.name().replace("_", " "));

        this.order = TasksOrder.BY_TITLE;
    }

    private SetOrderMenu(IMenu parentMenu, TasksOrder order) {
        super(parentMenu, "set_order", order.name().replace("_", " "));

        this.order = order;
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
        return MenuType.SYSTEM_SET_ORDER;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the LogoutMenu class. Check your UI logic implementation.");
    }

    public SetOrderMenu by(TasksOrder order) {
        return new SetOrderMenu(this.parentMenu, order);
    }

    public TasksOrder getOrder() {
        return this.order;
    }
    
}