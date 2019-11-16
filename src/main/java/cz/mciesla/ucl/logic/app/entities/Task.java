package cz.mciesla.ucl.logic.app.entities;

import java.util.ArrayList;
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

    private int id; // TODO: Missing ID (Hibernate)
    private String title;
    private String note;
    private IUser user;
    private boolean done;
    private ICategory category;
    private Date createdAt;
    private Date updatedAt;
    private List<ITag> tags;

    private int tagsCountCache;
    private boolean tagsCountCacheChanged;

    public Task(String title, String note, ICategory category) {
        this.title = title;
        this.note = note;
        this.category = category;
        this.tags = new ArrayList<>();

        this.tagsCountCacheChanged = true;
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
        this.tagsCountCacheChanged = true;
        this.tags.set(i, tag);
    }

    @Override
    public void addTag(ITag tag) {
        this.tagsCountCacheChanged = true;
        this.tags.add(tag);
    }

    @Override
    public int tagsCount() {
        if (this.tagsCountCacheChanged)
            this.tagsCountCache = this.tags.size();
        this.tagsCountCacheChanged = false;
        return this.tagsCountCache;
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