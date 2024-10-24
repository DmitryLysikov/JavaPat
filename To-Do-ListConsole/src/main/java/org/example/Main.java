package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    private static final Taskmanger taskManager = Taskmanger.getInstance();
    private static final Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        while (true) {
            out.println("\nМеню:");
            out.println("1. Добавить задачу");
            out.println("2. Показать все задачи");
            out.println("3. Отметить задачу как выполненную");
            out.println("4. Удалить задачу");
            out.println("5. Копировать задачу");
            out.println("6. Добавить составную задачу");
            out.println("7. Выход");
            out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> showTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> deleteTask();
                case 5 -> copyTask();
                case 6 -> addCompositeTask();
                case 7 -> {
                    out.println("Выход");
                    return;
                }
                default -> out.println("Неверный выбор!");
            }
        }
    }

    private static void addTask() {
        scanner.nextLine(); // очищаем сканер

        // Выбор типа задачи
        out.println("Выберите тип задачи:");
        out.println("1. Обычная задача");
        out.println("2. Задача с высоким приоритетом");
        int taskType = scanner.nextInt();

        scanner.nextLine(); // очищаем сканер после ввода числа

        TaskAbstraction taskAbstraction;
        switch (taskType) {
            case 1 -> {
                out.print("Введите заголовок задачи: ");
                String title = scanner.nextLine();

                out.print("Введите описание задачи: ");
                String description = scanner.nextLine();

                LocalDate dueDate = getDueDateFromUser();

                out.print("Введите приоритет задачи (1-5): ");
                int priority = getValidatedPriority();

                Task task = new Task.TaskBuilder()
                        .setTitle(title)
                        .setDescription(description)
                        .setPriority(priority)
                        .setDueDate(dueDate)
                        .build();

                taskManager.addTask(new TaskLeaf(task));
            }
            case 2 -> {
                taskAbstraction = new HighPriorityTask(new HighPriorityTaskImplementor());
                out.print("Введите приоритет задачи (1-5): ");
                int priority = getValidatedPriority();
                taskAbstraction.setPriority(priority);

                scanner.nextLine();

                out.print("Введите срок выполнения задачи (гггг-мм-дд): ");
                String dueDate = scanner.nextLine();
                taskAbstraction.setDueDate(dueDate);

                taskAbstraction.showInfo();
            }
            default -> {
                out.println("Неверный выбор. Создается обычная задача.");
                out.print("Введите заголовок задачи: ");
                String title = scanner.nextLine();

                out.print("Введите описание задачи: ");
                String description = scanner.nextLine();

                LocalDate dueDate = getDueDateFromUser();

                out.print("Введите приоритет задачи (1-5): ");
                int priority = getValidatedPriority();

                Task task = new Task.TaskBuilder()
                        .setTitle(title)
                        .setDescription(description)
                        .setPriority(priority)
                        .setDueDate(dueDate)
                        .build();

                taskManager.addTask(new TaskLeaf(task));
            }
        }

        out.println("Задача добавлена");
    }

    private static void addCompositeTask() {
        scanner.nextLine(); // очищаем сканер

        out.print("Введите название составной задачи: ");
        String title = scanner.nextLine();
        TaskComposite compositeTask = taskManager.createCompositeTask(title);

        // Добавление подзадач в составную задачу
        out.println("Добавьте задачи в составную задачу.");
        boolean addingTasks = true;
        while (addingTasks) {
            Task task = createSimpleTask();
            TaskLeaf taskLeaf = new TaskLeaf(task);
            compositeTask.add(taskLeaf);

            scanner.nextLine();

            out.println("Добавить ещё одну задачу? (y/n): ");
            String answer = scanner.nextLine();
            addingTasks = answer.equalsIgnoreCase("y");
        }

        taskManager.addTask(compositeTask);
        out.println("Составная задача добавлена.");
    }

    private static Task createSimpleTask() {
        out.print("Введите заголовок задачи: ");
        String title = scanner.nextLine();
        out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        LocalDate dueDate = getDueDateFromUser();
        out.print("Введите приоритет задачи (1-5): ");
        int priority = getValidatedPriority();

        return new Task.TaskBuilder()
                .setTitle(title)
                .setDescription(description)
                .setPriority(priority)
                .setDueDate(dueDate)
                .build();
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

    private static void showTasks() {
        List<TaskComponent> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            out.println("Нет задач.");
        } else {
            out.println("Список задач:");
            tasks.forEach(TaskComponent::showTaskDetails);
        }
    }

    private static void markTaskAsCompleted() {
        out.print("Введите ID задачи для завершения: ");
        int taskId = scanner.nextInt();
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.isCompleted(taskId);
            out.println("Задача отмечена как выполненная.");
        } else {
            out.println("Задача с таким ID не найдена.");
        }
    }

    private static void deleteTask() {
        out.print("Введите ID задачи для удаления: ");
        int taskId = scanner.nextInt();
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            taskManager.removeTask(taskId);
            out.println("Задача удалена.");
        } else {
            out.println("Задача с таким ID не найдена.");
        }
    }

    private static void copyTask() {
        out.print("Введите ID задачи для копирования: ");
        int taskId = scanner.nextInt();
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            Task clone = task.copy();
            taskManager.addTask(clone);
            out.println("Задача клонирована: " + clone);
        } else {
            out.println("Задача с таким ID не найдена.");
        }
    }
}
