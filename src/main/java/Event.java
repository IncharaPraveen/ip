public class Event extends Deadline{
    protected String start;
    public Event(String description, String start, String deadline){
        super(description, deadline);
        this.start = start;
    }
    public Event(String description, String start, String deadline, boolean isDone){
        super(description, deadline, isDone);
        this.start = start;
    }
    @Override
    public String getTaskType(){
        return "[E]";
    }
    @Override
    public String getTaskDescription(){
        return this.getTaskType() + this.getStatusIcon() + " " + description + " (from:" + this.start + " to:" + this.deadline +  ")\n";
        //print task
    }
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;
        return "D | " + isDone + " | " + this.description + "|" + this.start  + "|" + this.deadline;
    }
}
