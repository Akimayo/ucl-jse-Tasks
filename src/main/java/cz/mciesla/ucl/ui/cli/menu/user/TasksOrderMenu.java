package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.cli.menu.system.SetOrderMenu;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

/**
 * TasksOrderMenu
 */
public class TasksOrderMenu extends Menu {

    public TasksOrderMenu(IMenu parentMenu, IUserInterface ui) {
        super(parentMenu, "tasks_order", "Seřadit úkoly podle...");
        
        this.parentMenu = parentMenu;
        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    protected void build() {
        this.setDescription("Vyberte způsob řazení úkolů");

        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        SetOrderMenu orderMenu = (SetOrderMenu)this.ui.getMenuFactory().createSetTasksOrderMenu(this.parentMenu);

        this.addOption(new MenuOption(nextOptionNumber(), backMenu));
        this.addOption(new MenuOption(nextOptionNumber(), orderMenu));
        this.addOption(new MenuOption(nextOptionNumber(), orderMenu.by(TasksOrder.BY_CREATED_AT_ASC)));
        this.addOption(new MenuOption(nextOptionNumber(), orderMenu.by(TasksOrder.BY_CREATED_AT_DESC)));
        this.addOption(new MenuOption(nextOptionNumber(), orderMenu.by(TasksOrder.BY_UPDATED_AT_ASC)));
        this.addOption(new MenuOption(nextOptionNumber(), orderMenu.by(TasksOrder.BY_UPDATED_AT_DESC)));
    }

    
}