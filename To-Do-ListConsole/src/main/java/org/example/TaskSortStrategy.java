package org.example;

import java.util.List;
//Strategy
public interface TaskSortStrategy {
    List<Task> sort(List<Task> tasks);
}
