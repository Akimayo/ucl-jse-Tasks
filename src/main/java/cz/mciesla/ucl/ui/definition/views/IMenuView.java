package cz.mciesla.ucl.ui.definition.views;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;

/** Views are used only for formatting purposes. They are stateless. */
public interface IMenuView {
    String formatMenuOption(IMenuOption option);
    String formatMenu(Menu menu);

    String drawDescription(Menu menu);
    String drawHeader(IMenu currentMenu);
    String drawSeparator();
    String drawNewLine();
    String drawBreadcrumbs(IMenu currentMenu);
    String drawOptions(IMenu currentMenu);
    String drawMessage(String message);
    String drawError(String message);
    String drawPrompt(String message);
}
