package cz.mciesla.ucl.ui.cli.views;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.IMenuOption;
import cz.mciesla.ucl.ui.definition.views.IMenuView;

public class MenuView implements IMenuView {

    @Override
    public String formatMenuOption(IMenuOption option) {
        // TODO: Number padding
        return option.getNumber() + ": " + option.getTitle();
    }

    @Override
    public String formatMenu(Menu menu) {
        // WTF: TODO: Options/Forms?
        return null;
    }

    @Override
    public String drawDescription(Menu menu) {
        // TODO: Prettify (Align to center?)
        return menu.getDescription();
    }

    @Override
    public String drawHeader(IMenu currentMenu) {
        // TODO: Prettify (Border?)
        return currentMenu.getTitle();
    }

    @Override
    public String drawSeparator() {
        // WTF: What does a separator separate?
        String ret = "";
        for(int i = 0; i < 10; i++)
            ret += this.drawNewLine();
        return ret;
    }

    @Override
    public String drawNewLine() {
        return System.lineSeparator();
    }

    @Override
    public String drawBreadcrumbs(IMenu currentMenu) {
        String ret = "";
        IMenu menu = currentMenu;
        while(menu != null) {
            ret = " > " + menu.getTitle() + ret;
        }
        return ret.substring(3);
    }

    @Override
    public String drawOptions(IMenu currentMenu) {
        String ret = "";
        for(IMenuOption option : currentMenu.getOptions()) {
            ret += this.drawNewLine() + this.formatMenuOption(option);
        }
        return ret.substring(this.drawNewLine().length());
    }

    @Override
    public String drawMessage(String message) {
        return "[ " + message + " ]";
    }

    @Override
    public String drawError(String message) {
        return "! " + message + " !";
    }

    @Override
    public String drawPrompt(String message) {
        return message;
    }

}
