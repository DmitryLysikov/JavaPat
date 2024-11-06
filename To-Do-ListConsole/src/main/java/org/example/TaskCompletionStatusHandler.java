package org.example;

//Chain of Responsibility
public class TaskCompletionStatusHandler extends TaskHandler {
    @Override
    public void handle(Task task) {
        task.setStatus(false);
        if (nextHandler != null) {
            nextHandler.handle(task);
        }
    }
}
