package cz.mciesla.ucl.logic.data.dao;

import cz.mciesla.ucl.logic.app.entities.definition.Color;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private int id;
    private UserDAO user;
    private String title;
    private Color color;
    private List<TaskDAO> tasks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryDAO() {
        tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public UserDAO getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public Color getColor() {
        return color;
    }

    public List<TaskDAO> getTasks() {
        return tasks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTasks(List<TaskDAO> tasks) {
        this.tasks = tasks;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
