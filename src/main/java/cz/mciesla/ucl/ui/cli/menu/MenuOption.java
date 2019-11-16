package cz.mciesla.ucl.ui.cli.menu;

import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;

public class MenuOption implements IMenuOption {

	private int number;
    private IMenu menu;

    public MenuOption(int nextOptionNumber, IMenu loginMenu) {
        this.number = nextOptionNumber;
        this.menu = loginMenu;
	}

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public String getTitle() {
        return this.menu.getTitle();
    }

    @Override
    public IMenu getMenu() {
        return this.menu;
    }

}
