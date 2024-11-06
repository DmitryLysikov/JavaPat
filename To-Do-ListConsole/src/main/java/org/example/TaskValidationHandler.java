package org.example;
//Chain of Responsibility
public class TaskValidationHandler extends TaskHandler {
    @Override
    public void handle(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            System.out.println("Заголовок задачи не может быть пустым!");
            return;
        }
        if (nextHandler != null) {
            nextHandler.handle(task);
        }
    }
}
