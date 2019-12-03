package cz.mciesla.ucl.logic.data.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private int id;
    private UserDAO user;
    private String title;
    private String note;
    private boolean done;
    private CategoryDAO category;
    private LocalDate deadline;
    private List<TagDAO> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskDAO() {
        tags = new ArrayList<>();
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return this.id;
	}

	public UserDAO getUser() {
		return user;
	}

	public void setUser(UserDAO user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CategoryDAO getCategory() {
		return category;
	}

	public void setCategory(CategoryDAO category) {
		this.category = category;
	}

	public List<TagDAO> getTags() {
		return tags;
	}

	public void setTags(List<TagDAO> tags) {
		this.tags = tags;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDeadline() {
		return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
