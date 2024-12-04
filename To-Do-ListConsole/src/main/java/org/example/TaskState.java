package org.example;
//State
public interface TaskState {
    void markInProgress(TaskContext context);
    void markCompleted(TaskContext context);
}
