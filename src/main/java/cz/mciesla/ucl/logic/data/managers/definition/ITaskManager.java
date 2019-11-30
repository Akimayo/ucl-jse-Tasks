package cz.mciesla.ucl.logic.data.managers.definition;

import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

public interface ITaskManager {
    ITask[] getAllTasksForUser(IUser user);
    ITask getTaskByIdForUser(int taskId, IUser user);
    void createTask(ITask task);
    void updateTask(ITask task);
    void deleteTaskByIdForUser(int taskId, IUser user);
}
