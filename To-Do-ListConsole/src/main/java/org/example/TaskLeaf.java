package org.example;

public class TaskLeaf implements TaskComponent {
    private final Task task;

    public TaskLeaf(Task task) {
        this.task = task;
    }

    @Override
    public void add(TaskComponent task) {
        // Операция не поддерживается для листовых задач
        throw new UnsupportedOperationException("Нельзя добавить подзадачи к простой задаче.");
    }

    @Override
    public void remove(TaskComponent task) {
        // Операция не поддерживается для листовых задач
        throw new UnsupportedOperationException("Нельзя удалить подзадачи из простой задачи.");
    }

    @Override
    public TaskComponent getChild(int index) {
        // Операция не поддерживается для листовых задач
        throw new UnsupportedOperationException("У простой задачи нет подзадач.");
    }

    @Override
    public void showTaskDetails() {
        System.out.println(task.toString());
    }

    @Override
    public int getId() {
        return task.getId();
    }
}
