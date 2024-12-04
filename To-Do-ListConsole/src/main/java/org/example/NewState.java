package org.example;
//State
public class NewState implements TaskState {
    @Override
    public void markInProgress(TaskContext context) {
        System.out.println("Задача переведена в состояние 'В процессе'.");
        context.setState(new InProgressState());
    }

    @Override
    public void markCompleted(TaskContext context) {
        System.out.println("Невозможно завершить новую задачу. Сначала начните её выполнение.");
    }
}
