package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
//Mediator
public class TaskManagerMediator implements TaskMediator {
    private final TaskManagerInterface taskManager;

    public TaskManagerMediator() {
        this.taskManager = new TaskManagerProxy(false);
    }

    @Override
    public void addTask(String title, String description, int priority, String dueDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(dueDate);
            Task task = new Task.TaskBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .setPriority(priority)
                    .setDueDate(parsedDate)
                    .build();
            taskManager.addTask(task);
            System.out.println("Задача добавлена!");
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты.");
        }
    }

    @Override
    public void markTaskAsCompleted(int taskId) {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.isCompleted(taskId);
            System.out.println("Задача отмечена как выполненная.");
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }

    @Override
    public void deleteTask(int taskId) {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.removeTask(taskId);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }

    @Override
    public void showAllTasks() {
        List<Task> tasks = taskManager.getTasksByPriority();
        if (tasks.isEmpty()) {
            System.out.println("Нет задач.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    @Override
    public void copyTask(int taskId) {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            Task clone = task.copy();
            taskManager.addTask(clone);
            System.out.println("Задача клонирована: " + clone);
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }
}
