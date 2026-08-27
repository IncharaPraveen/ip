import java.util.ArrayList;

public class TaskList {
    private ArrayList<Todo> tasks;

    // Constructor for starting fresh
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor for loading from Storage
    public TaskList(ArrayList<Todo> tasks) {
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