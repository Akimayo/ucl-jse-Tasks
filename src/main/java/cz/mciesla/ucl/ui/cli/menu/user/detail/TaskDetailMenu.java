package cz.mciesla.ucl.ui.cli.menu.user.detail;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.cli.views.TaskView;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.views.ITaskView;

public class TaskDetailMenu extends Menu {

    private final ITaskView formatter;
    private final ITask task;

	public TaskDetailMenu(final IMenu listMenu, final IUserInterface ui, final ITask e) {
        super(listMenu, "task_detail", e.getTitle());
        this.task = e;

        this.formatter = new TaskView();
        this.parentMenu = listMenu;
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
        this.setDescription(this.formatter.formatTask(this.task));

        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu markDoneMenu = this.ui.getMenuFactory().createMarkDoneMenu(this, this.task);
        IMenu changeTagsMenu = this.ui.getMenuFactory().createTaskTagsMenu(this, this.ui, this.task);
        IMenu editMenu = this.ui.getMenuFactory().createTaskFormMenu(this.parentMenu, this.ui, this.task);
        IMenu destroyMenu = this.ui.getMenuFactory().createConfirmDestroyMenu(this.parentMenu, this.ui, this.task);

        this.addOption(new MenuOption(this.nextOptionNumber(), backMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), markDoneMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), changeTagsMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), editMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), destroyMenu));
    }

}
