package cz.mciesla.ucl.ui.cli.menu;

import cz.mciesla.ucl.ui.definition.menu.IMenu;

public abstract class FormMenu extends Menu {
    public FormMenu(IMenu parentMenu, String identifier, String title) {
        super(parentMenu, identifier, title);

        defineForm();
    }

    /** This method should be used for definition of form fields */
    protected abstract void defineForm();
}
