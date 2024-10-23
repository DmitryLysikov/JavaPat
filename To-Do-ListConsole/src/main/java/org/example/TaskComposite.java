package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskComposite implements TaskComponent {
    private final List<TaskComponent> tasks = new ArrayList<>();
    private final int id;
    private final String title;

    public TaskComposite(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public void add(TaskComponent task) {
        tasks.add(task);
    }

    @Override
    public void remove(TaskComponent task) {
        tasks.remove(task);
    }

    @Override
    public TaskComponent getChild(int index) {
        return tasks.get(index);
    }

    @Override
    public void showTaskDetails() {
        System.out.println("Составная задача: " + title + " (ID: " + id + ")");
        for (TaskComponent task : tasks) {
            task.showTaskDetails();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }
}
