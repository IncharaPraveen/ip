package computa;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Todo> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Todo> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Todo task) {
        tasks.add(task);
    }

    public Todo deleteTask(int index) {
        return tasks.remove(index);
    }

    public Todo getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
