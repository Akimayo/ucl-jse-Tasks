package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class UserEditMenu extends Menu {

    public UserEditMenu(IMenu parentMenu, IUserInterface ui) {
        super(parentMenu, "user_edit", "Správa uživatele " + ui.getLogic().getUserLoggedIn().getUsername());
        this.logic = ui.getLogic();
        this.ui = ui;
        this.parentMenu = parentMenu;
    }

    @Override
    protected void build() {
        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu editMenu = this.ui.getMenuFactory().createUserEditFormMenu(this.parentMenu, this.ui);
        IMenu destroyMenu = this.ui.getMenuFactory().createConfirmDestroyMenu(this.parentMenu, this.ui, this.logic.getUserLoggedIn());

        addOption(new MenuOption(nextOptionNumber(), backMenu));
        addOption(new MenuOption(nextOptionNumber(), editMenu));
        addOption(new MenuOption(nextOptionNumber(), destroyMenu));
    }

}
