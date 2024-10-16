package org.example;

public class HighPriorityTask extends TaskAbstraction{
    public HighPriorityTask(TaskTypeImplementor implementor) {
        super(implementor);
    }

    @Override
    public void setPriority(int priority) {
        implementor.setPriority(priority);
    }

    @Override
    public void setDueDate(String dueDate) {
        implementor.setDueDate(dueDate);
    }

    @Override
    public void showInfo() {
        System.out.println("Тип задачи: " + implementor.getType());
    }
}
