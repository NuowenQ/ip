package cq.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import cq.enums.TaskType;

/**
 * Abstract base class representing a task in the chatbot application.
 */
public abstract class Task {
    public static final int TODO_TASK_INFORMATION_LENGTH = 1;
    public static final int DEADLINE_TASK_INFORMATION_LENGTH = 2;
    public static final int EVENT_TASK_INFORMATION_LENGTH = 3;
    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter OUTPUT_FORMATTER =
        DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
    private final String name;
    private boolean completeStatus;

    /**
     * Constructs a new Task with the specified name.
     * The task is initially marked as incomplete.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        assert (name != null);
        assert (!name.isBlank());

        this.name = name;
        this.completeStatus = false;
    }

    /**
     * Returns the type identifier for this task.
     *
     * @return A string representing the task type.
     */
    public abstract TaskType getTaskType();

    /**
     * Returns the name of this task.
     *
     * @return The task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether this task is complete.
     *
     * @return True if the task is complete, false otherwise.
     */
    public boolean getCompleteStatus() {
        return this.completeStatus;
    }

    /**
     * Marks this task as complete.
     *
     * @return The string representation of this task after marking as complete.
     */
    public String setAsComplete() {
        this.completeStatus = true;
        return this.toString();
    }

    /**
     * Marks this task as incomplete.
     *
     * @return The string representation of this task after marking as incomplete.
     */
    public String setAsIncomplete() {
        this.completeStatus = false;
        return this.toString();
    }

    /**
     * Checks if the task falls on the given date.
     *
     * @param date the date to check against.
     * @return true if the task is relevant to the date.
     */
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return A string describing the name of the task and complete status.
     */
    @Override
    public String toString() {
        String status = this.getCompleteStatus() ? "[x]" : "[ ]";
        return status + " " + this.name;
    }
}
