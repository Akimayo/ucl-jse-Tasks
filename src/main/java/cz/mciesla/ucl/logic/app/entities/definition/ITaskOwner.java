package cz.mciesla.ucl.logic.app.entities.definition;

public interface ITaskOwner {
    ITask[] getTasks();
    ITask getTask(int i);
    void saveTask(int i, ITask task);
    void addTask(ITask task);
    int tasksCount();
}
