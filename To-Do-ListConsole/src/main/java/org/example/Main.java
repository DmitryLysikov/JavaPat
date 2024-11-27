package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    private static Taskmanger taskManager;
    private static final Scanner scanner = new Scanner(in);
    private static final TaskManagerCaretaker caretaker = new TaskManagerCaretaker();
    private static final TaskMediator mediator = new TaskManagerMediator();

    public static void main(String[] args) {
        taskManager = new Taskmanger();
        while (true) {
            out.println("\nМеню:");
            out.println("1. Добавить задачу");
            out.println("2. Показать все задачи");
            out.println("3. Отметить задачу как выполненную");
            out.println("4. Удалить задачу");
            out.println("5. Копировать задачу");
            out.println("6. Сохранить состояние");
            out.println("7. Восстановить состояние");
            out.println("8. Итерация по задачам");
            out.println("9. Выход");
            out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> showTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> deleteTask();
                case 5 -> copyTask();
                case 6 -> saveState();
                case 7 -> restoreState();
                case 8 -> iterateTasks();
                case 9 -> {
                    out.println("Выход");
                    return;
                }
                default -> out.println("Неверный выбор!");
            }
        }
    }

    private static void addTask() {
        scanner.nextLine();
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


        Command addTaskCommand = new AddTaskCommand(taskManager, task);
        addTaskCommand.execute();


        mediator.addTask(title, description, priority, dueDate.toString());
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

    private static void showTasks() {
        List<Task> tasks = taskManager.getTasksByPriority();
        if (tasks.isEmpty()) {
            out.println("Нет задач.");
        } else {
            out.println("Список задач:");
            tasks.forEach(out::println);
        }


        mediator.showAllTasks();
    }

    private static void markTaskAsCompleted() {
        out.print("Введите ID задачи для завершения: ");
        int taskId = scanner.nextInt();


        Command markTaskAsCompletedCommand = new MarkTaskAsCompletedCommand(taskManager, taskId);
        markTaskAsCompletedCommand.execute();


        mediator.markTaskAsCompleted(taskId);
    }

    private static void deleteTask() {
        out.print("Введите ID задачи для удаления: ");
        int taskId = scanner.nextInt();


        Command deleteTaskCommand = new DeleteTaskCommand(taskManager, taskId);
        deleteTaskCommand.execute();


        mediator.deleteTask(taskId);
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


        mediator.copyTask(taskId);
    }

    private static void iterateTasks() {
        TaskIterator iterator = taskManager.getIterator();
        if (!iterator.hasNext()) {
            out.println("Нет задач для отображения.");
            return;
        }
        out.println("Задачи:");
        while (iterator.hasNext()) {
            out.println(iterator.next());
        }
    }

    private static void saveState() {
        caretaker.saveState(taskManager);
        out.println("Состояние сохранено.");
    }

    private static void restoreState() {
        caretaker.restoreState(taskManager);
        out.println("Состояние восстановлено.");
    }
}
