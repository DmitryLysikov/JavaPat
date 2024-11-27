package org.example;

import java.util.Stack;
//Memento
public class TaskManagerCaretaker {
    private final Stack<TaskManagerMemento> history = new Stack<>();

    public void saveState(Taskmanger taskManager) {
        history.push(taskManager.save());
    }

    public void restoreState(Taskmanger taskManager) {
        if (!history.isEmpty()) {
            TaskManagerMemento memento = history.pop();
            taskManager.restore(memento);
        } else {
            System.out.println("Нет сохраненных состояний!");
        }
    }
}
