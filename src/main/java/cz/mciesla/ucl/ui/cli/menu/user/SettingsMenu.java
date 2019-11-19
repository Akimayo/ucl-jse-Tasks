package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class SettingsMenu extends Menu {

    public SettingsMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "settings_menu", title);
        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    protected void build() {
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu categoriesMenu = ui.getMenuFactory().createCategoriesMenu(this, ui);
        IMenu tagsMenu = ui.getMenuFactory().createTagsMenu(this, ui);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), categoriesMenu));
        addOption(new MenuOption(nextOptionNumber(), tagsMenu));
    }

}
