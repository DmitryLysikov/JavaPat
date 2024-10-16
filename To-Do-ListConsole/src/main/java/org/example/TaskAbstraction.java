package org.example;
//Паттерн Bridge абстрация
public abstract class TaskAbstraction {
    protected TaskTypeImplementor implementor;

    public TaskAbstraction(TaskTypeImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract void setPriority(int priority);
    public abstract void setDueDate(String dueDate);
    public abstract void showInfo();
}
