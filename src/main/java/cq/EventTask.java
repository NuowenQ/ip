package cq;

import java.time.LocalDate;

public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public EventTask(String name, String StartDate, String EndDate) {
        super(name);
        this.startDate = LocalDate.parse(StartDate, Task.INPUT_FORMATTER);
        this.endDate = LocalDate.parse(EndDate, Task.INPUT_FORMATTER);
    }

    public String getPeriod() {
        return "(from: " + this.startDate.format(Task.OUTPUT_FORMATTER) + " to: " + this.endDate.format(Task.OUTPUT_FORMATTER) + ")";
    }

    public String getStartDate() {
        return this.startDate.format(Task.INPUT_FORMATTER);
    }

    public String getEndDate() {
        return this.endDate.format(Task.INPUT_FORMATTER);
    }

    @Override
    public String getTaskType() {
        return "cq.EventTask";
    }

    @Override
    public String toString() {
        String text = "[E]";
        text += super.toString() + " " + getPeriod();
        return text;
    }
}
