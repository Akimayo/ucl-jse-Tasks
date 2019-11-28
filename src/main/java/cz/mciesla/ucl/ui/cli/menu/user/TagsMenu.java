package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class TagsMenu extends Menu {

    public TagsMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "tags_menu", title);
        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    protected void build() {
        // TODO Auto-generated method stub

    }

}
