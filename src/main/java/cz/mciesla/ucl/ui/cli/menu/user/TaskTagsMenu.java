package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

/**
 * TaskTagsMenu
 */
public class TaskTagsMenu extends Menu {

    private ITask task;

    public TaskTagsMenu(IMenu parentMenu, IUserInterface ui, ITask task) {
        super(parentMenu, "task_tags", "Vybrat značky");
        this.task = task;
        this.logic = ui.getLogic();
        this.ui = ui;
        this.parentMenu = parentMenu;
    }

    @Override
    public void initialize() {
        this.clearOptions();
        this.build();
    }

    @Override
    protected void build() {
        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        this.addOption(new MenuOption(this.nextOptionNumber(), backMenu));
        IMenu tagMenu;
        for(ITag tag : this.logic.getAllTags()) {
            tagMenu = this.ui.getMenuFactory().createTagAssignMenu(this.parentMenu, this.ui, tag, this.task);
            this.addOption(new MenuOption(this.nextOptionNumber(), tagMenu));
        }
    }

    
}