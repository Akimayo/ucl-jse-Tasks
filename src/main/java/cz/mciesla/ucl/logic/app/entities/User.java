package cz.mciesla.ucl.logic.app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private int id; // TODO: Missing ID (Hibernate)
    private String email;
    private String username;
    private String password;
    private List<ICategory> categories;
    private List<ITag> tags;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;

        this.tasks = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

	public User(int id2, String email2, String username2, String password2, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
	}

	@Override
    public ITask[] getTasks() {
        return this.tasks.toArray(new ITask[0]);
    }

    @Override
    public ITask getTask(int i) {
        return this.tasks.stream().filter(t -> t.getId() == i).findFirst().get();
    }

    @Override
    public void saveTask(int i, ITask task) {
        this.tasks.set(this.tasks.indexOf(this.getTask(i)), task);
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
        return this.categories.stream().filter(c -> c.getId() == i).findFirst().get();
    }

    @Override
    public void saveCategory(int i, ICategory category) {
        this.categories.set(this.categories.indexOf(this.getCategory(i)), category);
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
        return this.tags.stream().filter(g -> g.getId() == i).findFirst().get();
    }

    @Override
    public void saveTag(int i, ITag tag) {
        this.tags.set(this.tags.indexOf(this.getTag(i)), tag);
    }

    @Override
    public void addTag(ITag tag) {
        this.tags.add(tag);
    }

    @Override
    public int tagsCount() {
        return this.tags.size();
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