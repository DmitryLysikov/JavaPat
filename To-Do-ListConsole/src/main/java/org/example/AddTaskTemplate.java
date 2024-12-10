package org.example;
//Template method
public class AddTaskTemplate extends TaskTemplate {
    private final TaskManagerInterface taskManager;
    private final Task task;

    public AddTaskTemplate(TaskManagerInterface taskManager, Task task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    @Override
    protected void performAction() {
        taskManager.addTask(task);
        System.out.println("Задача добавлена: " + task);
    }
}
