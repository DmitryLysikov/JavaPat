package org.example;
//Template method
public abstract class TaskTemplate {
    public final void executeTaskAction() {
        startAction();
        performAction();
        endAction();
    }

    protected void startAction() {
        System.out.println("Начало выполнения операции с задачей...");
    }

    protected abstract void performAction();

    protected void endAction() {
        System.out.println("Операция с задачей завершена!\n");
    }
}
