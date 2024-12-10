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
            out.println("9. Смена состояния задачи");
            out.println("10. Сортировать задачи");
            out.println("11. Шаблонный метод для операций");
            out.println("12. Выход");
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
                case 9 -> changeTaskState();
                case 10 -> sortTasks();
                case 11 -> performTemplateMethod();
                case 12 -> {
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

        TaskTemplate addTaskTemplate = new AddTaskTemplate(taskManager, task);
        addTaskTemplate.executeTaskAction();

        mediator.addTask(title, description, priority, dueDate.toString());
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

        TaskTemplate markCompletedTemplate = new MarkTaskAsCompletedTemplate(taskManager, taskId);
        markCompletedTemplate.executeTaskAction();

        mediator.markTaskAsCompleted(taskId);
    }

    private static void deleteTask() {
        out.print("Введите ID задачи для удаления: ");
        int taskId = scanner.nextInt();

        TaskTemplate deleteTaskTemplate = new DeleteTaskTemplate(taskManager, taskId);
        deleteTaskTemplate.executeTaskAction();

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

    private static void changeTaskState() {
        out.print("Введите ID задачи для изменения состояния: ");
        int taskId = scanner.nextInt();
        Task task = taskManager.getTaskById(taskId);

        if (task != null) {
            TaskContext taskContext = new TaskContext();
            out.println("1. Перевести в процесс выполнения");
            out.println("2. Завершить задачу");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> taskContext.markInProgress();
                case 2 -> taskContext.markCompleted();
                default -> out.println("Неверный выбор.");
            }
        } else {
            out.println("Задача с таким ID не найдена.");
        }
    }

    private static void sortTasks() {
        TaskSorter sorter = new TaskSorter();
        out.println("Выберите метод сортировки:");
        out.println("1. По дате");
        out.println("2. По приоритету");
        out.println("3. По заголовку");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> sorter.setStrategy(new SortByDateStrategy());
            case 2 -> sorter.setStrategy(new SortByPriorityStrategy());
            case 3 -> sorter.setStrategy(new SortByTitleStrategy());
            default -> {
                out.println("Неверный выбор!");
                return;
            }
        }

        List<Task> sortedTasks = sorter.sortTasks(taskManager.getTasksByPriority());
        if (sortedTasks.isEmpty()) {
            out.println("Нет задач для сортировки.");
        } else {
            out.println("Отсортированные задачи:");
            sortedTasks.forEach(out::println);
        }
    }

    private static void performTemplateMethod() {
        out.println("Выберите действие:");
        out.println("1. Добавить задачу");
        out.println("2. Удалить задачу");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addTask();
            case 2 -> deleteTask();
            default -> out.println("Неверный выбор!");
        }
    }

    private static LocalDate getDueDateFromUser() {
        LocalDate dueDate = null;
        while (dueDate == null) {
            out.print("Введите срок выполнения задачи (гггг-мм-дд): ");
            String dateInput = scanner.next();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                out.println("Неверный формат даты. Попробуйте снова.");
            }
        }
        return dueDate;
    }

    private static int getValidatedPriority() {
        int priority;
        do {
            out.print("Введите приоритет от 1 до 5: ");
            while (!scanner.hasNextInt()) {
                out.print("Введите число от 1 до 5: ");
                scanner.next();
            }
            priority = scanner.nextInt();
        } while (priority < 1 || priority > 5);
        return priority;
    }
}
