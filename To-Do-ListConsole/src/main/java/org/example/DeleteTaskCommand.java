package org.example;
//Command
public class DeleteTaskCommand implements Command {
    private final TaskManagerInterface taskManager;
    private final int taskId;

    public DeleteTaskCommand(TaskManagerInterface taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.removeTask(taskId);
            System.out.println("Задача удалена: " + task);
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }
}
