package org.example;

import java.time.LocalDate;

public interface TaskFactory {
    Task createTask(String title, String description, LocalDate dueDate);
}
