package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Taskmanger taskmanger = Taskmanger.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Отметить задачу как выполненную");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Выход");
            System.out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> showTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void addTask() {
        scanner.nextLine(); // очищаем буфер после ввода числа
        System.out.print("Введите заголовок задачи: ");
        String title = scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();

        LocalDate dueDate = getDueDateFromUser();

        System.out.print("Введите приоритет задачи (1-5): ");
        int priority = getValidatedPriority();

        Task task = new Task(title, description, priority, false, dueDate);
        taskmanger.addTask(task);
        System.out.println("Задача добавлена!");
    }

    private static LocalDate getDueDateFromUser() {
        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Введите срок выполнения задачи (гггг-мм-дд): ");
            String dateInput = scanner.nextLine();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты. Попробуйте снова.");
            }
        }
        return dueDate;
    }

    private static int getValidatedPriority() {
        int priority = -1;
        while (priority < 1 || priority > 5) {
            while (!scanner.hasNextInt()) {
                System.out.print("Введите приоритет от 1 до 5: ");
                scanner.next();
            }
            priority = scanner.nextInt();
        }
        return priority;
    }

    private static void showTasks() {
        List<Task> tasks = taskmanger.getTasksByPriority();
        if (tasks.isEmpty()) {
            System.out.println("Нет задач.");
        } else {
            System.out.println("Список задач:");
            tasks.forEach(System.out::println);
        }
    }

    private static void markTaskAsCompleted() {
        System.out.print("Введите ID задачи для завершения: ");
        int taskId = scanner.nextInt();
        Task task = taskmanger.getTaskById(taskId);
        if (task != null) {
            taskmanger.isCompleted(taskId);
            System.out.println("Задача отмечена как выполненная.");
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }

    private static void deleteTask() {
        System.out.print("Введите ID задачи для удаления: ");
        int taskId = scanner.nextInt();
        Task task = taskmanger.getTaskById(taskId);
        if (task != null) {
            taskmanger.removeTask(taskId);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Задача с таким ID не найдена.");
        }
    }

}