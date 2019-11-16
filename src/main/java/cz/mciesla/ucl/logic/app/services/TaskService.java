package cz.mciesla.ucl.logic.app.services;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.services.definition.ITaskService;
import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;

/**
 * TaskService
 */
public class TaskService implements ITaskService {
    private List<ITask> tasks;

    public TaskService(UserService userService) {
	}

	@Override
    public ITask[] getAllTasks() {
        return this.tasks.toArray(new ITask[0]);
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        Comparator<ITask> comp;
        switch(order) {
            case BY_CREATED_AT_ASC:
                comp = new CreatedAtAscComparator();
            break;
            case BY_CREATED_AT_DESC:
                comp = new CreatedAtDescComparator();
            break;
            case BY_UPDATED_AT_ASC:
            comp = new UpdatedAtAscComparator();
            break;
            case BY_UPDATED_AT_DESC:
            comp = new UpdatedAtDescComparator();
            break;
            case BY_TITLE:
            default:
                comp = new TitleComparator();
            break;
        }
        return this.tasks.stream().sorted(comp).collect(Collectors.toList()).toArray(new ITask[0]);
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        Pattern p = Pattern.compile(keyword);
        return this.tasks.stream().filter(i -> p.matcher(i.getTitle()).matches() || p.matcher(i.getNote()).matches()).collect(Collectors.toList()).toArray(new ITask[0]);
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        return this.tasks.stream().filter(i -> i.getCategory().equals(category)).collect(Collectors.toList()).toArray(new ITask[0]);
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        return tag.getTasks();
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        // TODO: List concatenation and element distinctiveness
        return null;
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        // TODO: Mad filtering
        return null;
    }

    @Override
    public ITask getTaskById(int id) {
        return this.tasks.stream().filter(i -> i.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void createTask(String title) {
        this.createTask(title, null, null);
    }

    @Override
    public void createTask(String title, String note) {
        this.createTask(title, note, null);
    }

    @Override
    public void createTask(String title, String note, ICategory category) {
        this.tasks.add(new Task(title, note, category));
    }

    @Override
    public void updateTask(int id, String title, Color color) {
        // TODO: Update task
        
    }

    @Override
    public void destroyTask(int id) {
        this.tasks.remove(this.getTaskById(id));
    }

    // region Comparators
    private final class CreatedAtAscComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        }
    }
    private final class CreatedAtDescComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            return new CreatedAtAscComparator().compare(o1, o2) * -1;
        }
    }
    private final class TitleComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
    private final class UpdatedAtAscComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            return o1.getUpdatedAt().compareTo(o2.getUpdatedAt());
        }
    }
    private final class UpdatedAtDescComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            return new UpdatedAtAscComparator().compare(o1, o2) * -1;
        }
    }
    // endregion
    
}