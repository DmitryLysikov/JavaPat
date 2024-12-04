package org.example;

import java.util.List;
import java.util.stream.Collectors;
//Strategy
public class SortByDateStrategy implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()))
                .collect(Collectors.toList());
    }
}


class SortByPriorityStrategy implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()))
                .collect(Collectors.toList());
    }
}


class SortByTitleStrategy implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted((t1, t2) -> t1.getTitle().compareToIgnoreCase(t2.getTitle()))
                .collect(Collectors.toList());
    }
}
