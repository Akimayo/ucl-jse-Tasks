package cz.mciesla.ucl.logic.data.managers;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;
import cz.mciesla.ucl.logic.data.dao.TaskDAO;
import cz.mciesla.ucl.logic.data.managers.definition.ITaskManager;
import cz.mciesla.ucl.logic.data.mappers.MapperFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager implements ITaskManager {
    /** Keys in the map will be emails of user who owns the task */
    private Map<String, List<TaskDAO>> taskDatabase;
    private MapperFactory mappers;
    private ManagerFactory managers;

    public TaskManager(ManagerFactory managers, MapperFactory mappers) {
        this.taskDatabase = new HashMap<>();
        this.mappers = mappers;
        this.managers = managers;
    }

    @Override
    public ITask[] getAllTasksForUser(IUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITask getTaskByIdForUser(int taskId, IUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createTask(ITask task) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTask(ITask task) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTaskByIdForUser(int taskId, IUser user) {
        // TODO Auto-generated method stub

    }

    // TODO
}
