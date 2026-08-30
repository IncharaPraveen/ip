package computa;

import java.util.ArrayList;
import computa.Computa.ComputaException;
import java.util.List;

public class TaskList {
    private final List<Todo> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Todo> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a new task to the active task list.
     *
     * @param task The Task object (Todo, Deadline, or Event) to be added.
     */
    public void addTask(Todo task) {
        tasks.add(task);
    }
    /**
     * Deletes a task from the list based on its index number.
     *
     * @param index The 0-based index of the task to be removed.
     * @return The Task object that was successfully removed.
     * @throws ComputaException If the provided index is out of bounds.
     */
    public Todo deleteTask (int index) {
        return tasks.remove(index);
    }

    public Todo getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
