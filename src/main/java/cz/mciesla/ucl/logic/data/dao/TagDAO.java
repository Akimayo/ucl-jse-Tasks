package cz.mciesla.ucl.logic.data.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.mciesla.ucl.logic.app.entities.definition.Color;

public class TagDAO {
    private int id;
    private UserDAO user;
    private String title;
    private Color color;
    private List<TaskDAO> tasks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TagDAO() {
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<TaskDAO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDAO> tasks) {
        this.tasks = tasks;
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
