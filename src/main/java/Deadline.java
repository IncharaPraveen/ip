import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Todo{
    //rn make deadline a LocalDate var, stores date,
    // and then parse the string for deadline into date, and parse the 2nd half as time
    protected LocalDateTime deadline;
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.deadline= LocalDateTime.parse(deadline, inputFormat);
    }


    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.deadline= LocalDateTime.parse(deadline, inputFormat);
    }
    @Override
    public String getTaskType(){
        return "[D]";
    }
    @Override
    public String getTaskDescription(){
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy h.mma");
        String formattedDate = this.deadline.format(outputFormat);
        return this.getTaskType() + this.getStatusIcon() + " " + description + " (by: " + formattedDate + ")\n";
        //print task
    }
    public String toFileFormat() {
        // 1 for done, 0 for undone

        int isDone = this.isDone ? 1 : 0;
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDeadline = this.deadline.format(outputFormat);
        return "D | " + isDone + " | " + this.description + "|" + formattedDeadline;
    }

}
