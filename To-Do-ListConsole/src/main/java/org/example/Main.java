package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    private static final TaskManagerFacade taskManagerFacade = new TaskManagerFacade();
    private static final Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        while (true) {
            out.println("\nМеню:");
            out.println("1. Добавить задачу");
            out.println("2. Показать все задачи");
            out.println("3. Отметить задачу как выполненную");
            out.println("4. Удалить задачу");
            out.println("5. Копировать задачу");
            out.println("6. Выход");
            out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> taskManagerFacade.showAllTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> deleteTask();
                case 5 -> copyTask();
                case 6 -> {
                    out.println("Выход");
                    return;
                }
                default -> out.println("Неверный выбор!");
            }
        }
    }

    private static void addTask() {
        scanner.nextLine();

        out.println("Выберите тип задачи:");
        out.println("1. Обычная задача");
        out.println("2. Задача с высоким приоритетом");
        int taskType = scanner.nextInt();

        scanner.nextLine();

        switch (taskType) {
            case 1 -> {
                out.print("Введите заголовок задачи: ");
                String title = scanner.nextLine();

                out.print("Введите описание задачи: ");
                String description = scanner.nextLine();

                LocalDate dueDate = getDueDateFromUser();

                out.print("Введите приоритет задачи (1-5): ");
                int priority = getValidatedPriority();

                taskManagerFacade.addTask(title, description, priority, dueDate);
            }
            case 2 -> {
                out.print("Введите заголовок задачи: ");
                String title = scanner.nextLine();

                out.print("Введите описание задачи: ");
                String description = scanner.nextLine();

                LocalDate dueDate = getDueDateFromUser();

                taskManagerFacade.addHighPriorityTask(title, description, dueDate);
            }
            default -> out.println("Неверный выбор.");
        }
    }

    private static int getValidatedPriority() {
        int priority = -1;
        while (priority < 1 || priority > 5) {
            while (!scanner.hasNextInt()) {
                out.print("Введите приоритет от 1 до 5: ");
                scanner.next();
            }
            priority = scanner.nextInt();
        }
        return priority;
    }

    private static LocalDate getDueDateFromUser() {
        LocalDate dueDate = null;
        while (dueDate == null) {
            out.print("Введите срок выполнения задачи (гггг-мм-дд): ");
            String dateInput = scanner.nextLine();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                out.println("Неверный формат даты. Попробуйте снова.");
            }
        }
        return dueDate;
    }

    private static void markTaskAsCompleted() {
        out.print("Введите ID задачи для завершения: ");
        int taskId = scanner.nextInt();
        taskManagerFacade.markTaskAsCompleted(taskId);
    }

    private static void deleteTask() {
        out.print("Введите ID задачи для удаления: ");
        int taskId = scanner.nextInt();
        taskManagerFacade.deleteTask(taskId);
    }

    private static void copyTask() {
        out.print("Введите ID задачи для копирования: ");
        int taskId = scanner.nextInt();
        taskManagerFacade.copyTask(taskId);
    }
}
