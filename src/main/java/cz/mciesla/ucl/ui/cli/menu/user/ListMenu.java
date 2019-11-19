package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class ListMenu<T> extends Menu {

    T[] entities;

    public ListMenu(IMenu parentMenu, IUserInterface ui, String title, T[] entities) {
        super(parentMenu, "list_menu", title);

        this.logic = ui.getLogic();
        this.ui = ui;
        this.entities = entities;
    }

    @Override
    protected void build() {
        setDescription(String.format("Zobrazuji %d úkolů", this.entities.length)); // TODO: Better text

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
        IMenu detailMenu;
        for(T e : this.entities) {
            detailMenu = null;
            if (e instanceof Task) detailMenu = ui.getMenuFactory().createTaskFormMenu(this, ui, (Task)e);
            if (e instanceof Category) detailMenu = ui.getMenuFactory().createCategoryFormMenu(this, ui, (Category)e);
            if (e instanceof Tag) detailMenu = ui.getMenuFactory().createTagFormMenu(this, ui, (Tag)e);
            if (detailMenu != null) addOption(new MenuOption(nextOptionNumber(), detailMenu));
        }
    }

}
