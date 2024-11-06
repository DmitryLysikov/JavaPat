//package org.example;
//
//import java.time.LocalDate;
//import java.util.List;
//
////Facade
//public class TaskManagerFacade {
//    private final Taskmanger taskManager = Taskmanger.getInstance();
//
//
//    public void addTask(String title, String description, int priority, LocalDate dueDate) {
//        Task task = new Task.TaskBuilder()
//                .setTitle(title)
//                .setDescription(description)
//                .setPriority(priority)
//                .setDueDate(dueDate)
//                .build();
//        taskManager.addTask(task);
//        System.out.println("Задача добавлена: " + task);
//    }
//
//
//    public void addHighPriorityTask(String title, String description, LocalDate dueDate) {
//        TaskFactory taskFactory = new HighPriorityTaskFactory();
//        taskManager.addTaskFromFactory(taskFactory, title, description, dueDate);
//        System.out.println("Задача с высоким приоритетом добавлена.");
//    }
//
//
//    public void showAllTasks() {
//        List<Task> tasks = taskManager.getTasksByPriority();
//        if (tasks.isEmpty()) {
//            System.out.println("Нет задач.");
//        } else {
//            tasks.forEach(System.out::println);
//        }
//    }
//
//
//    public void markTaskAsCompleted(int taskId) {
//        taskManager.isCompleted(taskId);
//        System.out.println("Задача отмечена как выполненная: ID " + taskId);
//    }
//
//
//    public void deleteTask(int taskId) {
//        taskManager.removeTask(taskId);
//        System.out.println("Задача удалена: ID " + taskId);
//    }
//
//
//    public void copyTask(int taskId) {
//        Task task = taskManager.getTaskById(taskId);
//        if (task != null) {
//            Task clone = task.copy();
//            taskManager.addTask(clone);
//            System.out.println("Задача клонирована: " + clone);
//        } else {
//            System.out.println("Задача с таким ID не найдена.");
//        }
//    }
//}