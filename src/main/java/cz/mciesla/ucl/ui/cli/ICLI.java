package cz.mciesla.ucl.ui.cli;

import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public interface ICLI extends IUserInterface {
    IMenu handleMenu(IMenu currentMenu);
}
