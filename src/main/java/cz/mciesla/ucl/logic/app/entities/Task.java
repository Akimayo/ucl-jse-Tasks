package cz.mciesla.ucl.logic.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.app.entities.definition.ITask;
import cz.mciesla.ucl.logic.app.entities.definition.IUser;

/**
 * Task
 */
public class Task implements ITask {
    private static int idCounter = 1;

    public static void setLastId(int lastId) {
        idCounter = lastId;
    }

    private int id;
    private String title;
    private String note;
    private IUser user;
    private boolean done;
    private ICategory category;
    private LocalDate deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ITag> tags;

    private int tagsCountCache;
    private boolean tagsCountCacheChanged;

    public Task(IUser user, String title, String note, ICategory category, LocalDate deadline) {
        this.user = user;
        this.title = title;
        this.note = note;
        this.category = category;
        this.tags = new ArrayList<>();
        this.deadline = deadline;
        this.done = false;

        this.tagsCountCacheChanged = true;

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.id = ++idCounter;
    }

    public Task(IUser userEntity, int id, String title, String note, boolean done, ICategory category, LocalDate deadline,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(userEntity, title, note, category, deadline);
        this.id = id;
        this.done = done;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
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
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public void reopen() {
        this.done = false;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public void setNote(String note) {
        this.note = note;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public void setCategory(ICategory category) {
        this.category = category;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        this.updatedAt = LocalDateTime.now();
    }
}
