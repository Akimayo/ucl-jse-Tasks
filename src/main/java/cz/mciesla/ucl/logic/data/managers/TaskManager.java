package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.managers.definition.ITaskManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager implements ITaskManager {
    /** Keys in the map will be emails of user who owns the task */
    private Map<String, List<TaskDAO>> taskDatabase;
    private MapperFactory mappers;
    @SuppressWarnings("unused")
    private ManagerFactory managers;

    public TaskManager(ManagerFactory managers, MapperFactory mappers) {
        this.taskDatabase = new HashMap<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public ITask[] getAllTasksForUser(IUser user) {
        return this.getDAOsForUserLoggedIn(user).stream().map(i -> mappers.getTaskMapper().mapFromDAODeep(i))
                .toArray(ITask[]::new);
    }

    private List<TaskDAO> getDAOsForUserLoggedIn(IUser user) {
        List<TaskDAO> userTasks = taskDatabase.get(user.getEmail());
        if (userTasks == null) {
            userTasks = new ArrayList<>();
            taskDatabase.put(user.getEmail(), userTasks);
        }
        return userTasks;
    }

    @Override
    public ITask getTaskByIdForUser(int taskId, IUser user) {
        TaskDAO dao = this.getDAOsForUserLoggedIn(user).stream().filter(i -> i.getId() == taskId).findFirst().get();
        if (dao != null)
            return mappers.getTaskMapper().mapFromDAODeep(dao);
        return null;
    }

    @Override
    public void createTask(ITask task) {
        TaskDAO dao = mappers.getTaskMapper().mapToDAODeep(task);
        this.getDAOsForUserLoggedIn(task.getUser()).add(dao);
    }

    @Override
    public void updateTask(ITask task) {
        TaskDAO newDao = mappers.getTaskMapper().mapToDAODeep(task);
        List<TaskDAO> userTasks = this.getDAOsForUserLoggedIn(task.getUser());
        userTasks.set(userTasks.indexOf(userTasks.stream().filter(i -> i.getId() == task.getId()).findFirst().get()),
                newDao);
    }

    @Override
    public void deleteTaskByIdForUser(int taskId, IUser user) {
        List<TaskDAO> userTasks = this.getDAOsForUserLoggedIn(user);
        userTasks.remove(userTasks.indexOf(userTasks.stream().filter(i -> i.getId() == taskId).findFirst().get()));

    }

}
