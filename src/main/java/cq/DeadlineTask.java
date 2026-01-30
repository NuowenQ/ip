package cq;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    LocalDate deadLine;

    public DeadlineTask(String name, String deadLine) {
        super(name);
        this.deadLine = LocalDate.parse(deadLine, Task.INPUT_FORMATER);
    }

    public String getDeadLine() {
        return "(by: " + this.deadLine.format(Task.OUTPUT_FORMATER) + ")";
    }

    public String getDeadLineForFile() {
        return this.deadLine.format(Task.INPUT_FORMATER);
    }

    @Override
    public String getTaskType() {
        return "cq.DeadlineTask";
    }

    @Override
    public String toString() {
        String text = "[D]";
        text += super.toString() + " " + this.getDeadLine();
        return text;
    }
}
