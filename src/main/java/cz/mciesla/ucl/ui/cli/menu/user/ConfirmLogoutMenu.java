package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class ConfirmLogoutMenu extends Menu {

    public ConfirmLogoutMenu(IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "logout_menu", title);

        this.logic = ui.getLogic();
        this.ui = ui;
    }

    @Override
    protected void build() {
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        IMenu logoutMenu = ui.getMenuFactory().createLogout(this);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), logoutMenu));
    }

}
