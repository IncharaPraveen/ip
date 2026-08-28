package computa;
/**
 * Represents a basic task without any specific date or time constraints.
 */
public class Todo {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Todo task with the specified description.
     * The task is marked as undone by default.
     *
     * @param description The text description of the task.
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Constructs a Todo task with a specified description and completion status.
     *
     * @param description The text description of the task.
     * @param isDone      The completion status of the task (true if done, false if undone).
     */
    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Toggles the completion status of the task.
     */
    public void changeStatusIcon(){ this.isDone = !isDone; }
    /**
     * Retrieves the status icon representing whether the task is completed.
     *
     * @return A string containing "[X]" if the task is done, or "[]" if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]");
        // mark done task with X
    }
    /**
     * Formats the task into a readable string for the user interface.
     *
     * @return A formatted string displaying the task type, status, and description.
     */
    public String getTaskDescription(){
        return this.getTaskType() + this.getStatusIcon() + " " + description + "\n";
        //print task
    }
    /**
     * Retrieves the specific task type identifier.
     *
     * @return A string representing the task type ("[T]" for Todo).
     */
    public String getTaskType(){return "[T]";}
    //public String markTask();
    //public String unmarkTask();
    // convert any task into format ready for storage in the file, can js do as a regular desc, then
    /**
     * Formats the task into a standardized string for saving to the hard drive.
     *
     * @return A formatted string separated by pipes representing the task's save state.
     */
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + this.description;
    }

}
