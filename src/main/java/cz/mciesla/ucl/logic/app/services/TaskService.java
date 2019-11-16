package cz.mciesla.ucl.logic.app.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.app.services.definition.ITaskService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;

/**
 * TaskService
 */
public class TaskService implements ITaskService {
    private IUserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ITask[] getAllTasks() {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            return user.getTasks();
        else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            Comparator<ITask> comp;
            switch (order) {
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
            return (ITask[]) Stream.of(user.getTasks()).sorted(comp).toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            Pattern p = Pattern.compile(keyword);
            return (ITask[]) Stream.of(user.getTasks())
                    .filter(i -> p.matcher(i.getTitle()).matches() || p.matcher(i.getNote()).matches()).toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            return (ITask[]) Stream.of(user.getTasks()).filter(i -> i.getCategory().equals(category)).toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            return (ITask[]) Stream.of(user.getTasks()).filter(i -> Stream.of(i.getTags()).anyMatch(j -> j.equals(tag)))
                    .toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            Set<ITag> tags = new HashSet<>(Arrays.asList(tag));
            return (ITask[]) Stream.of(user.getTasks()).filter(i -> tags.containsAll(Arrays.asList(i.getTags())))
                    .toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            Set<ITag> tags = new HashSet<>(Arrays.asList(tag));
            return (ITask[]) Stream.of(user.getTasks())
                    .filter(i -> tags.containsAll(Arrays.asList(i.getTags())) && i.getCategory().equals(category))
                    .toArray();
        } else
            return new ITask[0];
    }

    @Override
    public ITask getTaskById(int id) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            return Stream.of(user.getTasks()).filter(i -> i.getId() == id).findFirst().get();
        } else
            return null;
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
        IUser user = this.userService.getUserLoggedIn();
        if (user != null)
            user.addTask(new Task(title, note, category));
    }

    @Override
    public void updateTask(int id, String title, Color color) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {

        }
        // TODO: Update task

    }

    @Override
    public void destroyTask(int id) {
        IUser user = this.userService.getUserLoggedIn();
        if (user != null) {
            // FIXME: Use index instead of ID
            user.saveTask(id, null);
        }
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