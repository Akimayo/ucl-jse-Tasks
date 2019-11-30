package cz.mciesla.ucl.logic.app.services.definition;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;

/**
 * This interface describes a class which should contain all application logic related to tasks
 * management and manipulation
 */
public interface ITaskService {
    /** Returns all tasks (in the default order) */
    ITask[] getAllTasks();

    /** Returns all tasks in desired order */
    ITask[] getAllTasks(TasksOrder order);

    /** Returns all tasks for keyword (search) in title or note */
    ITask[] searchTasksForKeyword(String keyword);

    /** Returns all tasks for given category */
    ITask[] getAllTasksByCategory(ICategory category);

    /** Returns all tasks for given tag */
    ITask[] getAllTasksByTag(ITag tag);

    /** Returns all tasks for given set of tags */
    ITask[] getAllTasksByTags(ITag[] tag);

    /** Returns all tasks for given set of tags and category */
    ITask[] getAllTasksByTags(ITag[] tag, ICategory category);

    /** Returns one task by its ID */
    ITask getTaskById(int id);

    /** Creates a task with title */
    void createTask(String title);

    /** Creates a task with title and note */
    void createTask(String title, String note);

    /** Creates a task with title, note and category */
    void createTask(String title, String note, ICategory category);

    /** Finds a task by its ID and updates it */
    void updateTask(int id, String title, String note, ICategory category);

    /** Finds a task by its ID and destroys it */
    void destroyTask(int id);
}
