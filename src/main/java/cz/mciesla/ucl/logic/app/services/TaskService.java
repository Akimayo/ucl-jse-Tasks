package cz.mciesla.ucl.logic.app.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.Task;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.services.definition.ITaskService;
import cz.mciesla.ucl.logic.app.services.definition.IUserService;
import cz.mciesla.ucl.logic.app.services.definition.TasksOrder;
import cz.mciesla.ucl.logic.data.managers.definition.ITaskManager;

/**
 * TaskService
 */
public class TaskService implements ITaskService {
    private IUserService userService;
    private ITaskManager manager;

    public TaskService(UserService userService) {
        this.manager = userService.getManagerFactory().getTaskManager();
        this.userService = userService;
    }

    @Override
    public ITask[] getAllTasks() {
        if (this.userService.isUserLoggedIn())
            return this.manager.getAllTasksForUser(this.userService.getUserLoggedIn());
        else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasks(TasksOrder order) {
        if (this.userService.isUserLoggedIn()) {
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
            case BY_DEADLINE_ASC:
                comp = new DeadlineAscComparator();
                break;
            case BY_DEADLINE_DESC:
                comp = new DeadlineDescComparator();
                break;
            case BY_TITLE:
            default:
                comp = new TitleComparator();
                break;
            }
            return Stream.of(this.getAllTasks()).sorted(comp).toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] searchTasksForKeyword(String keyword) {
        if (this.userService.isUserLoggedIn()) {
            Pattern p = Pattern.compile(keyword);
            return Stream.of(this.getAllTasks())
                    .filter(i -> p.matcher(i.getTitle()).matches() || p.matcher(i.getNote()).matches()).toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByCategory(ICategory category) {
        if (this.userService.isUserLoggedIn()) {
            return Stream.of(this.getAllTasks()).filter(i -> i.getCategory() != null && i.getCategory().equals(category)).toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTag(ITag tag) {
        if (this.userService.isUserLoggedIn()) {
            return Stream.of(this.getAllTasks()).filter(i -> Stream.of(i.getTags()).anyMatch(j -> j.equals(tag)))
                    .toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag) {
        if (this.userService.isUserLoggedIn()) {
            Set<ITag> tags = new HashSet<>(Arrays.asList(tag));
            return Stream.of(this.getAllTasks()).filter(i -> tags.containsAll(Arrays.asList(i.getTags())))
                    .toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask[] getAllTasksByTags(ITag[] tag, ICategory category) {
        if (this.userService.isUserLoggedIn()) {
            Set<ITag> tags = new HashSet<>(Arrays.asList(tag));
            return Stream.of(this.getAllTasks())
                    .filter(i -> tags.containsAll(Arrays.asList(i.getTags())) && i.getCategory().equals(category))
                    .toArray(ITask[]::new);
        } else
            return new ITask[0];
    }

    @Override
    public ITask getTaskById(int id) {
        if (this.userService.isUserLoggedIn()) {
            return this.manager.getTaskByIdForUser(id, this.userService.getUserLoggedIn());
        } else
            return null;
    }

    @Override
    public void createTask(String title) {
        this.createTask(title, null, null, null);
    }

    @Override
    public void createTask(String title, String note) {
        this.createTask(title, note, null, null);
    }

    @Override
    public void createTask(String title, String note, ICategory category, LocalDate deadline) {
        if (this.userService.isUserLoggedIn())
            this.manager.createTask(new Task(this.userService.getUserLoggedIn(), title, note, category, deadline));
    }

    @Override
    public void updateTask(int id, String title, String note, ICategory category, LocalDate deadline) {
        if (this.userService.isUserLoggedIn()) {
            ITask target = this.manager.getTaskByIdForUser(id, this.userService.getUserLoggedIn());
            if(title != "") target.setTitle(title);
            if(note != "") target.setNote(note);
            if(category != null) target.setCategory(category);
            if(deadline != null) target.setDeadline(deadline);
            this.manager.updateTask(target);
        }
    }
    
    @Override
    public void updateTask(int id, ITag tag) {
        if(this.userService.isUserLoggedIn()) {
            ITask target = this.manager.getTaskByIdForUser(id, this.userService.getUserLoggedIn());
            if(Stream.of(target.getTags()).anyMatch(i -> i.getId() == tag.getId()))
                target.removeTag(tag);
            else target.addTag(tag);
            this.manager.updateTask(target);
        }
    }

    @Override
    public void updateTask(int id) {
        if(this.userService.isUserLoggedIn()) {
            ITask target = this.manager.getTaskByIdForUser(id, this.userService.getUserLoggedIn());
            if(target.isDone()) target.reopen();
            else target.complete();
            this.manager.updateTask(target);
        }
    }

    @Override
    public void destroyTask(int id) {
        if (this.userService.isUserLoggedIn())
            this.manager.deleteTaskByIdForUser(id, this.userService.getUserLoggedIn());
    }

    // region Comparators
    private final class CreatedAtAscComparator implements Comparator<ITask> {
        @Override
        public int compare(final ITask o1, final ITask o2) {
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        }
    }

    private final class CreatedAtDescComparator implements Comparator<ITask> {
        @Override
        public int compare(final ITask o1, final ITask o2) {
            return new CreatedAtAscComparator().compare(o1, o2) * -1;
        }
    }

    private final class TitleComparator implements Comparator<ITask> {
        @Override
        public int compare(final ITask o1, final ITask o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    private final class UpdatedAtAscComparator implements Comparator<ITask> {
        @Override
        public int compare(final ITask o1, final ITask o2) {
            return o1.getUpdatedAt().compareTo(o2.getUpdatedAt());
        }
    }

    private final class UpdatedAtDescComparator implements Comparator<ITask> {
        @Override
        public int compare(final ITask o1, final ITask o2) {
            return new UpdatedAtAscComparator().compare(o1, o2) * -1;
        }
    }
    private final class DeadlineAscComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            if(o1.getDeadline() == null && o2.getDeadline() == null)
                return 0;
            if(o1.getDeadline() == null)
                return -1;
            if(o2.getDeadline() == null)
                return 1;
            return o1.getDeadline().compareTo(o2.getDeadline());
        }
    }
    private final class DeadlineDescComparator implements Comparator<ITask> {
        @Override
        public int compare(ITask o1, ITask o2) {
            if(o1.getDeadline() == null && o2.getDeadline() == null)
                return 0;
            if(o1.getDeadline() == null)
                return -1;
            if(o2.getDeadline() == null)
                return 1;
            return -o1.getDeadline().compareTo(o2.getDeadline());
        }
    }
    // endregion

}
