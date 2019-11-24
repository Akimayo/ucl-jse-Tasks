package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class ListMenu<T> extends Menu {

    public ListMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "list_menu", title);

        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    public void initialize() {
        this.clearOptions();
        build();
    }

    @Override
    protected void build() {
        T[] entities = null;
        if(this.getClass().getComponentType().equals(ITask.class)){
            entities = (T[])this.logic.getAllTasks();
            setDescription(String.format("Zobrazuji %d úkolů", entities.length));
        }
        if(this.getClass().getComponentType().equals(ICategory.class)) {
            entities = (T[])this.logic.getAllCategories();
            setDescription(String.format("Zobrazuji %d kategorií", entities.length));
        }
        if(this.getClass().getComponentType().equals(ITag.class)) {
            entities = (T[])this.logic.getAllTags();
            setDescription(String.format("Zobrazuji %d značek", entities.length));
        }

        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
        IMenu detailMenu;
        for(T e : entities) {
            detailMenu = null;
            if (e instanceof Task) detailMenu = ui.getMenuFactory().createTaskFormMenu(this, ui, (Task)e);
            if (e instanceof Category) detailMenu = ui.getMenuFactory().createCategoryFormMenu(this, ui, (Category)e);
            if (e instanceof Tag) detailMenu = ui.getMenuFactory().createTagFormMenu(this, ui, (Tag)e);
            if (detailMenu != null) addOption(new MenuOption(nextOptionNumber(), detailMenu));
        }
    }

}
