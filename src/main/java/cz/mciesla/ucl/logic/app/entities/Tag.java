package cz.mciesla.ucl.logic.app.entities;

import java.util.List;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

/**
 * Tag
 */
public class Tag implements ITag {
    private int id;
    private IUser user;
    private String title;
    private Color color;

    private List<ITask> tasks;

    public Tag(String title, Color color) {
        this.title = title;
        this.color = color;
    }

    @Override
    public ITask[] getTasks() {
        return this.tasks.toArray(new ITask[0]);
    }

    @Override
    public ITask getTask(int i) {
        // FIXME: Access user collection
        return this.tasks.get(i);
    }

    @Override
    public void saveTask(int i, ITask task) {
        // FIXME: Modify user collection
        this.tasks.set(i, task);
    }

    @Override
    public void addTask(ITask task) {
        // FIXME: Modify user collection
        this.tasks.add(task);
    }

    @Override
    public int tasksCount() {
        return this.tasks.size();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

}