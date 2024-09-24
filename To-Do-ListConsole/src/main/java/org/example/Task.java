package org.example;

import java.time.LocalDate;
//паттерн Prototype
public class Task {
    private static int idCounter = 0;
    private int id;
    private String title;
    private String description;
    private int priority;
    private boolean status;
    private LocalDate dueDate;

    public Task(String title, String description, int priority, boolean status, LocalDate dueDate) {
        this.dueDate = dueDate;
        this.id = ++idCounter;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public Task(Task that) {
        this(that.title, that.description, that.priority, that.status, that.dueDate);
    }

    public Task copy() {
        return new Task(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Des: %s ,Due: %s, Priority: %d, Status: %b",
                id, title, description ,dueDate, priority, status);
    }
}
