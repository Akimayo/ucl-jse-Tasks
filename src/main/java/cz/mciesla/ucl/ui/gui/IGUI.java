package cz.mciesla.ucl.ui.gui;

import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

/**
 * IGUI
 */
public interface IGUI extends IUserInterface {
    IMenu handleMenu(IMenu currentMenu);
}