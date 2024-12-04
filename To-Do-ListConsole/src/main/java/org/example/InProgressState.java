package org.example;
//State
public class InProgressState implements TaskState {
    @Override
    public void markInProgress(TaskContext context) {
        System.out.println("Задача уже находится в состоянии 'В процессе'.");
    }

    @Override
    public void markCompleted(TaskContext context) {
        System.out.println("Задача завершена.");
        context.setState(new CompletedState());
    }
}
