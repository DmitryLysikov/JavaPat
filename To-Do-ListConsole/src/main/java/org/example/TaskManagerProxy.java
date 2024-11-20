package org.example;

import java.util.List;
//Proxy паттерн
public class TaskManagerProxy implements TaskManagerInterface {
    private final Taskmanger taskManager;
    private final boolean isAdmin;

    public TaskManagerProxy(boolean isAdmin) {
        this.taskManager = Taskmanger.getInstance();
        this.isAdmin = isAdmin;
    }

    @Override
    public void addTask(Task task) {
        taskManager.addTask(task);
    }

    @Override
    public void removeTask(int id) {
        if (isAdmin) {
            taskManager.removeTask(id);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Удаление задачи доступно только администратору.");
        }
    }

    @Override
    public void isCompleted(int id) {
        taskManager.isCompleted(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @Override
    public Task getTaskById(int id) {
        return taskManager.getTaskById(id);
    }

    @Override
    public List<Task> getTasksByPriority() {
        return taskManager.getTasksByPriority();
    }

    @Override
    public TaskIterator getIterator() {
        return new TaskListIterator(getAllTasks());
    }
}
