package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

/**
 * TutorialMenu
 */
public class TutorialMenu extends Menu {

    public TutorialMenu(IMenu parentMenu) {
        super(parentMenu, "tutorial", "Jak používat");
    }

    @Override
    protected void build() {
        setDescription("> Tag           Přiřazený"+
                       "  Tag           Nepřiřazený"+
                       "*               Povinné pole"+
                       "-               Prázdná hodnota"+
                       "_               Mezera"); // TODO: Tutorial Text
        
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this.parentMenu);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    
}