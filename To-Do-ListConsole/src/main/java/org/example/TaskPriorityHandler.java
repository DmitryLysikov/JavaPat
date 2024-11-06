package org.example;
//Chain of Responsibility
public class TaskPriorityHandler extends TaskHandler {
    @Override
    public void handle(Task task) {
        if (task.getPriority() < 1 || task.getPriority() > 5) {
            System.out.println("Приоритет задачи должен быть от 1 до 5. Установлен приоритет по умолчанию: 3.");
            task.setPriority(3);
        }
        if (nextHandler != null) {
            nextHandler.handle(task);
        }
    }
}
