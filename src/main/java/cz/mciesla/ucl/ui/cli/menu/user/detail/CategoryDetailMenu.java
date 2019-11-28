package cz.mciesla.ucl.ui.cli.menu.user.detail;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.cli.views.CategoryView;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.views.ICategoryView;

public class CategoryDetailMenu extends Menu {

    private ICategoryView formatter;
    private ICategory category;

	public CategoryDetailMenu(IMenu listMenu, IUserInterface ui, ICategory e) {
        super(listMenu, "category_detail", e.getTitle());
        this.category = e;

        this.formatter = new CategoryView();
        this.parentMenu = listMenu;
        this.logic = ui.getLogic();
        this.ui = ui;
    }
    
    @Override
    public void initialize() {
        this.clearOptions();
        this.build();
    }

    @Override
    protected void build() {
        this.setDescription(this.formatter.formatCategory(this.category));

        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        // TODO: Options

        this.addOption(new MenuOption(this.nextOptionNumber(), backMenu));

    }

}
