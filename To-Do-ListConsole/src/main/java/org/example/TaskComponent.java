package org.example;

public interface TaskComponent {
    void add(TaskComponent task);
    void remove(TaskComponent task);
    TaskComponent getChild(int index);
    void showTaskDetails();
    int getId();
}
