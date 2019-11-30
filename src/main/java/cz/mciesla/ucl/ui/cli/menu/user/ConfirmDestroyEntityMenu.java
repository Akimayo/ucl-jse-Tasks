package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class ConfirmDestroyEntityMenu<T> extends Menu {

    T entity;

    public ConfirmDestroyEntityMenu(IMenu parentMenu, IUserInterface ui, T entity) {
        super(parentMenu, "destroy_entity", "Odstranit");
        this.entity = entity;

        this.parentMenu = parentMenu;
        this.logic = ui.getLogic();
        this.ui = ui;
	}

	@Override
    protected void build() {
        StringBuilder desc = new StringBuilder("Opravdu si přejete odstranit ");
        if(this.entity instanceof ITask) desc.append("úkol "+ ((ITask)this.entity).getTitle());
        if(this.entity instanceof ICategory) desc.append("kategorii "+ ((ICategory)this.entity).getTitle());
        if(this.entity instanceof ITag) desc.append("značku "+ ((ITag)this.entity).getTitle());

        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu destroyMenu = this.ui.getMenuFactory().createDestroyMenu(this.parentMenu, this.ui, entity);

        this.addOption(new MenuOption(this.nextOptionNumber(), backMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), destroyMenu));
    }

}
