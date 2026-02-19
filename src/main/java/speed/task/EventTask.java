package speed.task;

import java.time.LocalDate;

import speed.enums.TaskType;

/**
 * Represents a task that spans a period of time with a start and end date.
 * An EventTask is displayed with an [E] prefix and includes the event period.
 */
public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new EventTask with the specified name and date range.
     *
     * @param name      The name or description of the event.
     * @param startDate The start date of the event in the format specified by Task.INPUT_FORMATTER.
     * @param endDate   The end date of the event in the format specified by Task.INPUT_FORMATTER.
     */
    public EventTask(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPeriod() {
        return "(from: "
                + this.startDate.format(Task.OUTPUT_FORMATTER)
                + " to: "
                + this.endDate.format(Task.OUTPUT_FORMATTER)
                + ")";
    }

    public String getStartDate() {
        return this.startDate.format(Task.INPUT_FORMATTER);
    }

    public String getEndDate() {
        return this.endDate.format(Task.INPUT_FORMATTER);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return !date.isBefore(this.startDate) && !date.isAfter(this.endDate);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        String text = "[E]";
        text += super.toString() + " " + getPeriod();
        return text;
    }
}
