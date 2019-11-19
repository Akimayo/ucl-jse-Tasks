package cz.mciesla.ucl.ui.cli.menu.user;

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
	protected void build() {
		IMenu backMenu = ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu taskListMenu = ui.getMenuFactory().createListMenu(this, ui, "Seznam úkolů", this.logic.getAllTasks());
        IMenu newTaskMenu = ui.getMenuFactory().createTaskFormMenu(this, this.ui, null);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), taskListMenu));
        addOption(new MenuOption(nextOptionNumber(), newTaskMenu));
	}

}
