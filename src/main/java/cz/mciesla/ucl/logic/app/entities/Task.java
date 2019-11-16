package cz.mciesla.ucl.logic.app.entities;

import java.util.Date;
import java.util.List;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

/**
 * Task
 */
public class Task implements ITask {

    private int id;
    private String title;
    private String note;
    private IUser user;
    private boolean done;
    private ICategory category;
    private Date createdAt;
    private Date updatedAt;
    private List<ITag> tags;

    public Task(String title, String note, ICategory category) {
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public ICategory getCategory() {
        return this.category;
    }

    @Override
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
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

    @Override
    public void complete() {
        this.done = true;
    }

    @Override
    public void reopen() {
        this.done = false;
    }

}