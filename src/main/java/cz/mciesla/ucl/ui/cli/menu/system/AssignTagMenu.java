package cz.mciesla.ucl.ui.cli.menu.system;

import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;
import cz.mciesla.ucl.ui.definition.menu.MenuType;

/**
 * AssighTagMenu
 */
public class AssignTagMenu extends Menu {
    private ITask task;
    private ITag tag;
    public AssignTagMenu(IMenu parentMenu, IUserInterface ui, ITag assign, ITask to) {
        super(parentMenu, "assign", (Stream.of(to.getTags()).anyMatch(i -> i.getId() == assign.getId()) ? "> " : "") + assign.getTitle());
        this.tag = assign;
        this.task = to;
    }

    @Override
    protected void build() {
        // intentionally no operation here
    }

    @Override
    public boolean isSystemMenu() {
        return true;
    }

    @Override
    public MenuType getType() {
        return MenuType.SYSTEM_ASSIGN;
    }

    public String render() {
        // this call should never happen (the UI logic should handle it)
        // so when this method is called, it means that something is wrong,
        // hence we will throw a runtime exception
        throw new RuntimeException(
                "Method render() should never be called on the LogoutMenu class. Check your UI logic implementation.");
    }

    public ITask getTask() {
        return this.task;
    }
    public ITag getTag() {
        return this.tag;
    }
}