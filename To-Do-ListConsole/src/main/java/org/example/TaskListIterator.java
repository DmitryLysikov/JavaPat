package org.example;

import java.util.List;
//Iterator
public class TaskListIterator implements TaskIterator {
    private final List<Task> tasks;
    private int position = 0;

    public TaskListIterator(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return position < tasks.size();
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more tasks available.");
        }
        return tasks.get(position++);
    }
}
