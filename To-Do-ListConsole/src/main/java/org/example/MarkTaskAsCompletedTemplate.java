package org.example;
//Template method
public class MarkTaskAsCompletedTemplate extends TaskTemplate {
    private final TaskManagerInterface taskManager;
    private final int taskId;

    public MarkTaskAsCompletedTemplate(TaskManagerInterface taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    protected void performAction() {
        if (taskManager.getTaskById(taskId) != null) {
            taskManager.isCompleted(taskId);
            System.out.println("Задача с ID " + taskId + " отмечена как выполненная.");
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }
}
