package org.example;
//Command
public class MarkTaskAsCompletedCommand implements Command {
    private final TaskManagerInterface taskManager;
    private final int taskId;

    public MarkTaskAsCompletedCommand(TaskManagerInterface taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.isCompleted(taskId);
            System.out.println("Задача отмечена как выполненная: " + task);
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }
}
