package cz.mciesla.ucl.ui.cli.menu.user.detail;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.cli.views.TagView;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.views.ITagView;

public class TagDetailMenu extends Menu {

    private ITagView formatter;
    private ITag tag;

    public TagDetailMenu(IMenu listMenu, IUserInterface ui, ITag e) {
        super(listMenu, "tag_detail", e.getTitle());
        this.formatter = new TagView();
        this.tag = e;
        
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
        this.setDescription(this.formatter.formatTag(this.tag));
        
        IMenu backMenu = this.ui.getMenuFactory().createBackMenu(this.parentMenu);
        IMenu editMenu = this.ui.getMenuFactory().createTagFormMenu(this.parentMenu, this.ui, this.tag);
        IMenu destroyMenu = this.ui.getMenuFactory().createDestroyMenu(this.parentMenu, this.ui, this.tag);

        this.addOption(new MenuOption(this.nextOptionNumber(), backMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), editMenu));
        this.addOption(new MenuOption(this.nextOptionNumber(), destroyMenu));
    }

}
