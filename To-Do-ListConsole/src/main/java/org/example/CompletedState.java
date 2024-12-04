package org.example;
//State
public class CompletedState implements TaskState {
    @Override
    public void markInProgress(TaskContext context) {
        System.out.println("Невозможно вернуть задачу из завершённого состояния в процесс выполнения.");
    }

    @Override
    public void markCompleted(TaskContext context) {
        System.out.println("Задача уже завершена.");
    }
}
