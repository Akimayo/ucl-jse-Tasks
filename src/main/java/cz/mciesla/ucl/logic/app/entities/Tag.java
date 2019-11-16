package cz.mciesla.ucl.logic.app.entities;

import java.util.stream.Stream;

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

    private int tasksCountCache;
    private boolean tasksCountCacheChanged;

    public Tag(String title, Color color) {
        this.title = title;
        this.color = color;

        this.tasksCountCacheChanged = true;
    }

    private Stream<ITask> getUserTaskStream() {
        return Stream.of(this.user.getTasks());
    }

    @Override
    public ITask[] getTasks() {
        return (ITask[])this.getUserTaskStream().filter(i -> Stream.of(i.getTags()).anyMatch(t -> t.equals(this))).toArray();
    }

    @Override
    public ITask getTask(int i) {
        return this.getTasks()[i];
    }

    @Override
    public void saveTask(int i, ITask task) {
        this.tasksCountCacheChanged = true;
        this.user.saveTask(i, task);
    }

    @Override
    public void addTask(ITask task) {
        this.tasksCountCacheChanged = true;
        this.user.addTask(task);
    }

    @Override
    public int tasksCount() {
        if(this.tasksCountCacheChanged)
            this.tasksCountCache = this.getTasks().length;
        this.tasksCountCacheChanged = false;
        return this.tasksCountCache;
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