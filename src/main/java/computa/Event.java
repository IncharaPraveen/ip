package computa;
/**
 * Represents an event task that occurs within a specific start and end time frame.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Deadline {
    protected LocalDateTime start;
    /**
     * Creates an Event task with a specified description, start time, and end time.
     * The task is marked as undone by default.
     *
     * @param description The text description of the event.
     * @param start       The starting date and time, expected in yyyy-MM-dd HHmm format.
     * @param deadline    The ending date and time, expected in yyyy-MM-dd HHmm format.
     * @throws DateTimeParseException If the date strings do not match the expected format.
     */
    public Event(String description, String start, String deadline){
        super(description, deadline);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.start= LocalDateTime.parse(start, inputFormat);
    }
    /**
     * Creates an Event task with a specified description, start time, end time, and completion status.
     *
     * @param description The text description of the event.
     * @param start       The starting date and time, expected in yyyy-MM-dd HHmm format.
     * @param deadline    The ending date and time, expected in yyyy-MM-dd HHmm format.
     * @param isDone      The completion status of the task.
     * @throws DateTimeParseException If the date strings do not match the expected format.
     */
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
