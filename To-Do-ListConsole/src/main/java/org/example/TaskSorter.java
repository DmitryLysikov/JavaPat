package org.example;

import java.util.List;
//Strategy
public class TaskSorter {
    private TaskSortStrategy strategy;

    public void setStrategy(TaskSortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Task> sortTasks(List<Task> tasks) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия сортировки не установлена");
        }
        return strategy.sort(tasks);
    }
}
