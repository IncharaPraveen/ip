package computa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h.mma");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String getTaskDescription() {
        return getTaskType() + getStatusIcon() + " " + description + " (by: "
                + deadline.format(DISPLAY_FORMAT) + ")\n";
    }

    @Override
    public String toFileFormat() {
        int completionStatus = isDone ? 1 : 0;
        return "D | " + completionStatus + " | " + description + " |"
                + deadline.format(STORAGE_FORMAT);
    }
}
