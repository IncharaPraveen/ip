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
        assert tasks != null : "A task list cannot be initialized with null";
        this.tasks = tasks;
    }
    /**
     * Adds a new task to the active task list.
     *
     * @param task The Task object (Todo, Deadline, or Event) to be added.
     */
    public void addTask(Todo task) {
        assert task != null : "The task list cannot contain a null task";
        tasks.add(task);
        assert tasks.get(tasks.size() - 1) == task : "A newly added task must be stored";
    }
    /**
     * Deletes a task from the list based on its index number.
     *
     * @param index The 0-based index of the task to be removed.
     * @return The Task object that was successfully removed.
     * @throws ComputaException If the provided index is out of bounds.
     */
    public Todo deleteTask (int index) {
        Todo removedTask = tasks.remove(index);
        assert removedTask != null : "A valid task index must remove a task";
        return removedTask;
    }

    public Todo getTask(int index) {
        Todo task = tasks.get(index);
        assert task != null : "A valid task index must refer to a task";
        return task;
    }

    public int getSize() {
        return tasks.size();
    }
}
