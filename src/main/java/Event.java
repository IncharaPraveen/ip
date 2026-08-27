import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Deadline{
    protected LocalDateTime start;
    public Event(String description, String start, String deadline){
        super(description, deadline);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.start= LocalDateTime.parse(deadline, inputFormat);
    }
    public Event(String description, String start, String deadline, boolean isDone){
        super(description, deadline, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.start= LocalDateTime.parse(deadline, inputFormat);
    }
    @Override
    public String getTaskType(){
        return "[E]";
    }
    @Override
    public String getTaskDescription(){
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String formattedStart = this.start.format(outputFormat);
        return this.getTaskType() + this.getStatusIcon() + " " + description + " (from:" + formattedStart+ " to:" + this.deadline +  ")\n";
        //print task
    }
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;

        return "D | " + isDone + " | " + this.description + "|" + this.start  + "|" + this.deadline;
    }
}
