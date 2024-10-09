package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Паттерн Singleton
public class Taskmanger {
    private static final Taskmanger instance = new Taskmanger();

    private final List<Task> tasks = new ArrayList<>();

    public static Taskmanger getInstance() {
        return instance;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
    public void isCompleted(int id) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setStatus(true));
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addTaskFromFactory(TaskFactory factory, String title, String description, LocalDate dueDate) {
        Task task = factory.createTask(title, description, dueDate);
        addTask(task);
    }

    public List<Task> getTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }
}
