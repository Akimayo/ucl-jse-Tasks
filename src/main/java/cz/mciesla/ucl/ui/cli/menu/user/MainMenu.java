package cz.mciesla.ucl.ui.cli.menu.user;

import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class MainMenu extends Menu {
    public MainMenu(IUserInterface ui, String title) {
        super(null, "main_menu", title);

        this.ui = ui;
        this.logic = ui.getLogic();
    }

    @Override
    protected void build() {
        setDescription("Abyste mohli aplikaci používat, je nutné se nejprve přihlásit.\n\n"
                + "Pokud ještě nemáte svůj uživatelský účet, je možné se registrovat.");

        IMenu loginMenu = ui.getMenuFactory().createLoginFormMenu(this);
        IMenu registerMenu = ui.getMenuFactory().createRegistrationFormMenu(this);
        IMenu quitMenu = ui.getMenuFactory().createQuitMenu(this);

        addOption(new MenuOption(nextOptionNumber(), loginMenu));
        addOption(new MenuOption(nextOptionNumber(), registerMenu));
        addOption(new MenuOption(nextOptionNumber(), quitMenu));
    }
}
