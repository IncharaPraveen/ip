public class Deadline extends Todo{
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }
    @Override
    public String getTaskType(){
        return "[D]";
    }
    @Override
    public String getTaskDescription(){
        return this.getTaskType() + this.getStatusIcon() + " " + description + " (by: " + this.deadline + ")\n";
        //print task
    }
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;
        return "D | " + isDone + " | " + this.description + "|" + this.deadline;
    }

}
