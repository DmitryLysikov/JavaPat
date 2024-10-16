package org.example;

public class HighPriorityTaskImplementor implements TaskTypeImplementor{
    @Override
    public void setPriority(int priority) {
        System.out.println("Приоритет задачи с высоким приоритетом установлен на: " + priority);
    }

    @Override
    public void setDueDate(String dueDate) {
        System.out.println("Срок выполнения задачи с высоким приоритетом: " + dueDate);
    }

    @Override
    public String getType() {
        return "Задача с высоким приоритетом";
    }
}
