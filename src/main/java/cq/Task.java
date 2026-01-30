package cq;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

abstract public class Task {
    protected static final DateTimeFormatter INPUT_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter OUTPUT_FORMATER = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
    String name;
    boolean completeStatus;

    public Task(String name) {
        this.name = name;
        this.completeStatus = false;
    }

    public abstract String getTaskType();

    public String getName() {
        return this.name;
    }

    public boolean getCompleteStatus() {
        return this.completeStatus;
    }

    public String setAsComplete() {
        this.completeStatus = true;
        return this.toString();
    }

    public String setAsIncomplete() {
        this.completeStatus = false;
        return this.toString();
    }

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
