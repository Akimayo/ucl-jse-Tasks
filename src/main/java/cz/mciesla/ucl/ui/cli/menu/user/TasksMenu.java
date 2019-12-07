package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class TasksMenu extends Menu {

	public TasksMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "tasks_menu", title);
        this.logic = ui.getLogic();
		this.ui = ui;
    }
    
    @Override
    public void initialize() {
        this.clearOptions();
        this.build();
    }

	@Override
	protected void build() {
		IMenu backMenu = ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu taskListMenu = ui.getMenuFactory().createListMenu(ITask.class, this, ui, "Seznam úkolů");
        IMenu newTaskMenu = ui.getMenuFactory().createTaskFormMenu(this, this.ui, null);
        IMenu orderMenu = ui.getMenuFactory().createTasksOrderMenu(this, this.ui);
        IMenu filterMenu;
        if(ListMenu.hasFilters()) filterMenu = ui.getMenuFactory().createFilterFormMenu(this, this.ui, true);
        else filterMenu = ui.getMenuFactory().createFilterFormMenu(this, this.ui, false);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), taskListMenu));
        addOption(new MenuOption(nextOptionNumber(), newTaskMenu));
        if(!ListMenu.hasFilters())addOption(new MenuOption(nextOptionNumber(), orderMenu));
        addOption(new MenuOption(nextOptionNumber(), filterMenu));
	}

}
