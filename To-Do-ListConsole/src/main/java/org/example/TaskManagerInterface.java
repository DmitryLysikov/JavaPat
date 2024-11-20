package org.example;

import java.util.List;

public interface TaskManagerInterface {
    void addTask(Task task);
    void removeTask(int id);
    void isCompleted(int id);
    List<Task> getAllTasks();
    Task getTaskById(int id);
    List<Task> getTasksByPriority();
    TaskIterator getIterator();
}
