package cz.mciesla.ucl.logic.app.entities;

import java.util.List;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.ITaskOwner;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

/**
 * User
 */
public class User implements IUser, ITaskOwner {

    private List<ITask> tasks;
    private int id;
    private String email;
    private String username;
    private String password;
    private List<ICategory> categories;
    private List<ITag> tags;

    public User(String email2, String username2, String password2) {
	}

	@Override
    public ITask[] getTasks() {
        return this.tasks.toArray(new ITask[0]);
    }

    @Override
    public ITask getTask(int i) {
        // WTF: ?
        return this.tasks.get(i);
    }

    @Override
    public void saveTask(int i, ITask task) {
        // WTF: ?
        this.tasks.set(i, task);
    }

    @Override
    public void addTask(ITask task) {
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
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public ICategory[] getCategories() {
        return this.categories.toArray(new ICategory[0]);
    }

    @Override
    public ICategory getCategory(int i) {
        // WTF: ?
        return this.categories.get(i);
    }

    @Override
    public void saveCategory(int i, ICategory category) {
        // WTF:
        this.categories.set(i, category);
    }

    @Override
    public void addCategory(ICategory category) {
        this.categories.add(category);
    }

    @Override
    public int categoriesCount() {
        return this.categories.size();
    }

    @Override
    public ITag[] getTags() {
        return this.tags.toArray(new ITag[0]);
    }

    @Override
    public ITag getTag(int i) {
        // WTF: ?
        return this.tags.get(i);
    }

    @Override
    public void saveTag(int i, ITag tag) {
        // WTF: ?
        this.tags.set(i, tag);
    }

    @Override
    public void addTag(ITag tag) {
        this.tags.add(tag);
    }

    @Override
    public int tagsCount() {
        return this.tags.size();
    }

}