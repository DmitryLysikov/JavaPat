package org.example;

public interface TaskMediator {
    void addTask(String title, String description, int priority, String dueDate);
    void markTaskAsCompleted(int taskId);
    void deleteTask(int taskId);
    void showAllTasks();
    void copyTask(int taskId);
}
