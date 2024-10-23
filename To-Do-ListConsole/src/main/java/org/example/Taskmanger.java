package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Паттерн Singleton и Composite
public class Taskmanger {
    private static final Taskmanger instance = new Taskmanger();

    // Список для хранения компонентов задач (TaskComponent — это общий интерфейс для TaskLeaf и TaskComposite)
    private final List<TaskComponent> taskComponents = new ArrayList<>();

    // Старый список задач оставляем для совместимости
    private final List<Task> tasks = new ArrayList<>();

    // Получение экземпляра синглтона
    public static Taskmanger getInstance() {
        return instance;
    }

    // Добавление обычной задачи через старый интерфейс
    public void addTask(Task task) {
        tasks.add(task);
        taskComponents.add(new TaskLeaf(task)); // Добавляем TaskLeaf в общий список компонентов
    }

    // Добавление задачи через паттерн Composite
    public void addTask(TaskComponent taskComponent) {
        taskComponents.add(taskComponent);
    }

    // Удаление задачи по ID (старый метод)
    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        taskComponents.removeIf(component -> component.getId() == id); // Удаляем компонент из общего списка
    }

    // Отметка задачи как выполненной (старый метод)
    public void isCompleted(int id) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> task.setStatus(true));

//        taskComponents.stream()
//                .filter(taskComponent -> taskComponent.getId() == id)
//                .forEach(TaskComponent::markCompleted); // Отмечаем выполненной и в общем списке компонентов
    }

    // Получение всех задач (старый метод)
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    // Получение всех задач (новый метод для поддержки TaskComponent)
    public List<TaskComponent> getTasks() {
        return new ArrayList<>(taskComponents);
    }

    // Получение задачи по ID (старый метод)
    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Получение задачи-компонента по ID (новый метод)
    public TaskComponent getTaskComponentById(int id) {
        return taskComponents.stream()
                .filter(taskComponent -> taskComponent.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Добавление задачи через фабрику (старый метод)
    public void addTaskFromFactory(TaskFactory factory, String title, String description, LocalDate dueDate) {
        Task task = factory.createTask(title, description, dueDate);
        addTask(task);
    }

    // Получение задач, отсортированных по приоритету (старый метод)
    public List<Task> getTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }

    // Создание составной задачи (метод для Composite)
    public TaskComposite createCompositeTask(String title) {
        TaskComposite compositeTask = new TaskComposite((int) (Math.random() * 10),title);
        addTask(compositeTask); // Добавляем составную задачу в список
        return compositeTask;
    }

    // Удаление компонента задачи по ID
    public void removeTaskComponent(int id) {
        taskComponents.removeIf(taskComponent -> taskComponent.getId() == id);
    }
}
