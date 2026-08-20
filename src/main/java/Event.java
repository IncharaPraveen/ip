public class Event extends Deadline{
    protected String start;
    public Event(String description, String start, String deadline){
        super(description, deadline);
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
}
