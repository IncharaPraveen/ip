package computa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Deadline {
    protected LocalDateTime start;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String description, String start, String deadline) {
        super(description, deadline);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
    }

    public Event(String description, String start, String deadline, boolean isDone) {
        super(description, deadline, isDone);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getTaskDescription() {
        return getTaskType() + getStatusIcon() + " " + description + " (from: "
                + start.format(DISPLAY_FORMAT) + " to: " + deadline.format(DISPLAY_FORMAT) + ")\n";
    }

    @Override
    public String toFileFormat() {
        int completionStatus = isDone ? 1 : 0;
        return "E | " + completionStatus + " | " + description + " |"
                + start.format(STORAGE_FORMAT) + " |" + deadline.format(STORAGE_FORMAT);
    }
}
