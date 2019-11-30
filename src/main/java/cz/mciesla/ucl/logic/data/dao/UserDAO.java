package cz.mciesla.ucl.logic.data.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private int id;
    private String email;
    private String username;
    private String password; // NOTE: In a real app, the password should never be stored in plaintext, but in a hashed form, etc.
    private List<TaskDAO> tasks;
    private List<CategoryDAO> categories;
    private List<TagDAO> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDAO() {
        this.tasks = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TaskDAO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDAO> tasks) {
        this.tasks = tasks;
    }

    public List<CategoryDAO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDAO> categories) {
        this.categories = categories;
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
}
