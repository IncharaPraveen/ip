package computa;

public class Todo {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void changeStatusIcon() {
        isDone = !isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[]";
    }

    public String getTaskDescription() {
        return getTaskType() + getStatusIcon() + " " + description + "\n";
    }

    public String getTaskType() {
        return "[T]";
    }

    public String toFileFormat() {
        int completionStatus = isDone ? 1 : 0;
        return "T | " + completionStatus + " | " + description;
    }
}
