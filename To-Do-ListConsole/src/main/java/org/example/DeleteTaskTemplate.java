package org.example;
//Template method
public class DeleteTaskTemplate extends TaskTemplate {
    private final TaskManagerInterface taskManager;
    private final int taskId;

    public DeleteTaskTemplate(TaskManagerInterface taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    protected void performAction() {
        if (taskManager.getTaskById(taskId) != null) {
            taskManager.removeTask(taskId);
            System.out.println("Задача удалена с ID: " + taskId);
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }
}
