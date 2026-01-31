package cq;

import java.time.LocalDate;

/**
 * A class that represents a deadline task.
 */
public class DeadlineTask extends Task {
    private final LocalDate deadLine;

    /**
     * Constructs a DeadlineTask with the given description and deadline.
     *
     * @param name the description of the task
     * @param deadLine the deadline in input date format
     */
    public DeadlineTask(String name, String deadLine) {
        super(name);
        this.deadLine = LocalDate.parse(deadLine, Task.INPUT_FORMATTER);
    }

    /**
     * Returns the formatted deadline string for display.
     *
     * @return the deadline formatted as "(by: MMM dd yyyy)"
     */
    public String getDeadLine() {
        return "(by: " + this.deadLine.format(Task.OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns the deadline in the format used for file storage.
     *
     * @return the deadline in input file format
     */
    public String getDeadLineForFile() {
        return this.deadLine.format(Task.INPUT_FORMATTER);
    }

    /**
     * Returns the task type identifier.
     *
     * @return the fully qualified class name
     */
    @Override
    public String getTaskType() {
        return "cq.DeadlineTask";
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the task details as a formatted string
     */
    @Override
    public String toString() {
        String text = "[D]";
        text += super.toString() + " " + this.getDeadLine();
        return text;
    }
}
