package cz.mciesla.ucl.logic.app.entities;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import cz.mciesla.ucl.logic.app.entities.definition.Color;
import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

/**
 * Category
 */
public class Category implements ICategory {
    private int id; // TODO: Missing ID (Hibernate)
    private IUser user; // TODO: Missing user (Hibernate/Constructor)
    private String title;
    private Color color;

    private int tasksCountCache;
    private boolean tasksCountCacheChanged;

    public Category(String title, Color color) {
        this.title = title;
        this.color = color;

        this.tasksCountCacheChanged = true;
    }

    public Category(IUser userEntity, int id2, String title2, Color color2, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
	}

	private Stream<ITask> getUserTasksStream() {
        return Stream.of(this.user.getTasks()); // FIXME: Use UserService instead
    }

    @Override
    public ITask[] getTasks() {
        return (ITask[]) this.getUserTasksStream().filter(i -> i.getCategory().equals(this)).toArray();
    }

    @Override
    public ITask getTask(int i) {
        return this.getTasks()[i];
    }

    @Override
    public void saveTask(int i, ITask task) {
        this.user.saveTask(i, task);
    }

    @Override
    public void addTask(ITask task) {
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

    @Override
    public LocalDateTime getCreatedAt() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        // TODO Auto-generated method stub
        return null;
    }
}