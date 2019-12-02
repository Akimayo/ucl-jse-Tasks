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
    private static int idCounter = 1;

    public static void setLastId(int lastId) {
        idCounter = lastId;
    }

    private int id;
    private IUser user;
    private String title;
    private Color color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int tasksCountCache;
    private boolean tasksCountCacheChanged;

    public Category(IUser user, String title, Color color) {
        this.user = user;
        this.title = title;
        this.color = color;

        this.tasksCountCacheChanged = true;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.id = ++idCounter;
    }

    public Category(IUser userEntity, int id, String title, Color color, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this(userEntity, title, color);
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Stream<ITask> getUserTasksStream() {
        return Stream.of(this.user.getTasks()); // FIXME: Use UserService instead
    }

    @Override
    public ITask[] getTasks() {
        return this.getUserTasksStream().filter(i -> i.getCategory().equals(this)).toArray(ITask[]::new);
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
        if (this.tasksCountCacheChanged)
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
        return this.createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
}
