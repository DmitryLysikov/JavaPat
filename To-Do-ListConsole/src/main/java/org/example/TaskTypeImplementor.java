package org.example;
//Паттерн Bridge реализация
public interface TaskTypeImplementor {
    void setPriority(int priority);
    void setDueDate(String dueDate);
    String getType();
}
