package cz.mciesla.ucl.ui.cli.menu.user;

import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.Category;
import cz.mciesla.ucl.logic.app.entities.Tag;
import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;
import cz.mciesla.ucl.ui.cli.menu.Menu;
import cz.mciesla.ucl.ui.cli.menu.MenuOption;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.definition.menu.IMenu;

public class ListMenu<T> extends Menu {
    private static TasksOrder order = TasksOrder.BY_TITLE;
    private static FilterBy filter = FilterBy.NONE;
    private static String[] filterTitles;

    private final Class<?> __type;

    public ListMenu(Class<?> type, IMenu parentMenu, IUserInterface ui, String title) {
        super(parentMenu, "list_menu", title);

        this.logic = ui.getLogic();
        this.ui = ui;

        this.__type = type;
    }

    @Override
    public void initialize() {
        this.clearOptions();
        build();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void build() {
        IMenu backMenu = ui.getMenuFactory().createBackMenu(this);
        addOption(new MenuOption(nextOptionNumber(), backMenu));
        T[] entities = null;
        if(this.__type.equals(ITask.class)){
            Stream<ITask> taskStream;
            switch (ListMenu.filter) {
                case CATEGORY:
                    taskStream = Stream.of(this.logic.getTasksFilteredByCategory(ListMenu.filterTitles[0]));
                    break;
                case TAGS:
                    taskStream = Stream.of(this.logic.getTasksFilteredByTags(ListMenu.filterTitles));
                    break;
                case CATEGORY_TAGS:
                    String[] tags = new String[ListMenu.filterTitles.length - 1];
                    for(int i = 1; i < ListMenu.filterTitles.length; i++) tags[i-1] = ListMenu.filterTitles[i];
                    taskStream = Stream.of(this.logic.getTasksFilteredByCategoryAndTags(ListMenu.filterTitles[0], tags));
                    break;
                case COMPLETED:
                    taskStream = Stream.of(this.logic.getAllTasks()).filter(i -> i.isDone());
                    break;
                    case OPEN:
                    taskStream = Stream.of(this.logic.getAllTasks()).filter(i -> !i.isDone());
                    break;
                case NONE:
                default:
                    taskStream = Stream.of(this.logic.getAllTasks(ListMenu.order));
                    break;
            }
            entities = (T[])taskStream.sorted().toArray(ITask[]::new);
            setDescription(String.format("Zobrazuji %d úkolů", entities.length));
            if(entities.length <= 0) {
                IMenu newTaskMenu = ui.getMenuFactory().createTaskFormMenu(parentMenu, ui, null);
                addOption(new MenuOption(nextOptionNumber(), newTaskMenu));
            }
        }
        if(this.__type.equals(ICategory.class)) {
            entities = (T[])Stream.of(this.logic.getAllCategories()).sorted().toArray(ICategory[]::new);
            setDescription(String.format("Zobrazuji %d kategorií", entities.length));
            if(entities.length <= 0) {
                IMenu newCategoryMenu = ui.getMenuFactory().createCategoryFormMenu(parentMenu, ui, null);
                addOption(new MenuOption(nextOptionNumber(), newCategoryMenu));
            }
        }
        if(this.__type.equals(ITag.class)) {
            entities = (T[])Stream.of(this.logic.getAllTags()).sorted().toArray(ITag[]::new);
            setDescription(String.format("Zobrazuji %d značek", entities.length));
            if(entities.length <= 0) {
                IMenu newTagMenu = ui.getMenuFactory().createTagFormMenu(parentMenu, ui, null);
                addOption(new MenuOption(nextOptionNumber(), newTagMenu));
            }
        }

        IMenu detailMenu;
        for(T e : entities) {
            detailMenu = null;
            if (e instanceof Task) detailMenu = ui.getMenuFactory().createTaskDetailMenu(this, ui, (Task)e);
            if (e instanceof Category) detailMenu = ui.getMenuFactory().createCategoryDetailMenu(this, ui, (Category)e);
            if (e instanceof Tag) detailMenu = ui.getMenuFactory().createTagDetailMenu(this, ui, (Tag)e);
            if (detailMenu != null) addOption(new MenuOption(nextOptionNumber(), detailMenu));
        }
    }

    public static void setTasksOrder(TasksOrder order) {
        ListMenu.order = order;
    }

	public static boolean hasFilters() {
		return ListMenu.filter != FilterBy.NONE;
    }
    
    public static void setFilter(FilterBy filter, String[] titles) {
        ListMenu.filter = filter;
        ListMenu.filterTitles = titles;
    }

    public static void resetFilter() {
        ListMenu.filter = FilterBy.NONE;
        ListMenu.filterTitles = null;
    }

}
