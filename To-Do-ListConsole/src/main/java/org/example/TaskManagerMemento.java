package org.example;

import java.util.List;
//Memento
public class TaskManagerMemento {
    private final List<Task> tasks;

    public TaskManagerMemento(List<Task> tasks) {
        this.tasks = tasks.stream()
                .map(Task::copy)
                .toList();
    }

    public List<Task> getSavedTasks() {
        return tasks;
    }
}
