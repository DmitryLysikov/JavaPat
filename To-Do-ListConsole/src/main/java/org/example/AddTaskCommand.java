package org.example;
//Command
public class AddTaskCommand implements Command {
    private final TaskManagerInterface taskManager;
    private final Task task;

    public AddTaskCommand(TaskManagerInterface taskManager, Task task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    @Override
    public void execute() {
        TaskHandler validationHandler = new TaskValidationHandler();
        TaskHandler priorityHandler = new TaskPriorityHandler();
        TaskHandler completionStatusHandler = new TaskCompletionStatusHandler();

        validationHandler.setNextHandler(priorityHandler);
        priorityHandler.setNextHandler(completionStatusHandler);
        validationHandler.handle(task);

        taskManager.addTask(task);
        System.out.println("Задача добавлена: " + task);
    }
}
