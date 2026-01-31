package cq;

/**
 * A class that represents a Todo task.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the given description.
     *
     * @param name the description of the task
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Returns the task type identifier.
     *
     * @return the fully qualified class name
     */
    @Override
    public String getTaskType() {
        return "cq.ToDoTask";
    }

    /**
     * Returns the string representation of this task.
     *
     * @return the task details as a formatted string
     */
    @Override
    public String toString() {
        String text = "[T]";
        text += super.toString();
        return text;
    }
}
