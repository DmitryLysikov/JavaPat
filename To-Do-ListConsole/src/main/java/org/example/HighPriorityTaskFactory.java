package org.example;

import java.time.LocalDate;
//Abstract factory
public class HighPriorityTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String title, String description, LocalDate dueDate) {
        return new Task.TaskBuilder()
                .setTitle(title)
                .setDescription(description)
                .setPriority(1)
                .setDueDate(dueDate)
                .build();
    }
}
