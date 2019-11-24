package cz.mciesla.ucl.ui.gui.views;

import javax.swing.JButton;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;

public class MenuView {

    public JButton formatMenuOption(IMenuOption option) {
        JButton btn = new JButton(option.getTitle());

        return btn;
    }

    public String formatMenu(Menu menu) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawDescription(Menu menu) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawHeader(IMenu currentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawSeparator() {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawNewLine() {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawBreadcrumbs(IMenu currentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawOptions(IMenu currentMenu) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawMessage(String message) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawError(String message) {
        // TODO Auto-generated method stub
        return null;
    }

    public String drawPrompt(String message) {
        // TODO Auto-generated method stub
        return null;
    }

}
