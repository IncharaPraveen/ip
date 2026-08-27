import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Deadline{
    protected LocalDateTime start;
    public Event(String description, String start, String deadline){
        super(description, deadline);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.start= LocalDateTime.parse(start, inputFormat);
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
        String formattedEnd = this.deadline.format(outputFormat);
        return this.getTaskType() + this.getStatusIcon() + " " + description + " (from:" + formattedStart+ " to:" + formattedEnd +  ")\n";
        //print task
    }
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDeadline = this.deadline.format(saveFormat);
        return "E | " + isDone + " | " + this.description + "|" + this.start.format(saveFormat)  + "|" + formattedDeadline;
    }
}
