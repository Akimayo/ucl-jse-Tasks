package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class CategoriesMenu extends Menu {

    public CategoriesMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "categories_menu", title);
        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    protected void build() {
        IMenu backMenu = ui.getMenuFactory().createBackMenu(parentMenu);
        IMenu listMenu = ui.getMenuFactory().createListMenu(ICategory.class, this, ui, "Seznam kategorií");
        IMenu newCategoryMenu = ui.getMenuFactory().createCategoryFormMenu(this, ui, null);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), listMenu));
        addOption(new MenuOption(nextOptionNumber(), newCategoryMenu));
    }

}
