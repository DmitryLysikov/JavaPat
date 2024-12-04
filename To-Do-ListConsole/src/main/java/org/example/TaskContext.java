package org.example;
//State
public class TaskContext {
    private TaskState state;

    public TaskContext() {
        this.state = new NewState();
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void markInProgress() {
        state.markInProgress(this);
    }

    public void markCompleted() {
        state.markCompleted(this);
    }
}
