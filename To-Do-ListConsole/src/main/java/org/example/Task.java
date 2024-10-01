package org.example;

import java.time.LocalDate;

//паттерны Prototype and Builder
public class Task {
    private static int idCounter = 0;
    private final int id;
    private String title;
    private String description;
    private int priority;
    private boolean status;
    private LocalDate dueDate;

    private Task(TaskBuilder builder) {
        this.id = ++idCounter;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.status = builder.status;
        this.dueDate = builder.dueDate;
    }

    public Task copy() {
        return new Task.TaskBuilder()
                .setTitle(this.title)
                .setDescription(this.description)
                .setPriority(this.priority)
                .setStatus(this.status)
                .setDueDate(this.dueDate)
                .build();
    }

    public static class TaskBuilder {
        private String title;
        private String description;
        private int priority = 3;
        private boolean status = false;
        private LocalDate dueDate;

        public TaskBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public TaskBuilder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    public int getId() {
        return id;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Des: %s ,Due: %s, Priority: %d, Status: %b",
                id, title, description, dueDate, priority, status);
    }
}