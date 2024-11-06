package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Taskmanger implements TaskManagerInterface {
    private static final Taskmanger instance = new Taskmanger();
    private final List<Task> tasks = new ArrayList<>();

    private Taskmanger() {}

    public static Taskmanger getInstance() {
        return instance;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public void isCompleted(int id) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setStatus(true));
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> getTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }
}
