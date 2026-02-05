package cq;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Abstract base class representing a task in the chatbot application.
 */
public abstract class Task {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter OUTPUT_FORMATTER =
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
        this.name = name;
        this.completeStatus = false;
    }

    /**
     * Returns the type identifier for this task.
     *
     * @return A string representing the task type.
     */
    public abstract String getTaskType();

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
     * Returns a string representation of this task.
     *
     * @return A string describing the name of the task and complete status.
     */
    @Override
    public String toString() {
        String message = "";

        if (this.getCompleteStatus()) {
            message += "[x]";
        } else {
            message += "[ ]";
        }

        return message + " " + this.name;
    }
}
