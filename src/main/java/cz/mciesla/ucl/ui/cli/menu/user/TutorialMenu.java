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
        StringBuilder tutorial = new StringBuilder();
        String[] lines = new String[] {
                        "  Formulářová pole  ",
                        "===============================",
                        "*               Povinné pole",
                        " Formulářové vstupy ",
                        "-------------------------------",
                        "-               Prázdná hodnota",
                        "_               Mezera",
                        "  Správa úkolů  ",
                        "===============================",
                        " Značky ",
                        "-------------------------------",
                        "> Tag           Přiřazená",
                        "  Tag           Nepřiřazená"
                    };
        for(String line : lines) 
            tutorial.append(line).append(System.lineSeparator());
        setDescription(tutorial.toString());
        
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this.parentMenu);

        addOption(new MenuOption(nextOptionNumber(), backMenu));
    }

    
}