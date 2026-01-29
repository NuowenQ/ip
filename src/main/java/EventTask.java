import java.time.LocalDate;

public class EventTask extends Task {
    LocalDate StartDate;
    LocalDate EndDate;

    public EventTask(String name, String StartDate, String EndDate) {
        super(name);
        this.StartDate = LocalDate.parse(StartDate, Task.INPUT_FORMATER);
        this.EndDate = LocalDate.parse(EndDate, Task.INPUT_FORMATER);
    }

    public String getPeriod() {
        return "(from: " + this.StartDate.format(Task.OUTPUT_FORMATER) + " to: " + this.EndDate.format(Task.OUTPUT_FORMATER) + ")";
    }

    public String getStartDate() {
        return this.StartDate.format(Task.INPUT_FORMATER);
    }

    public String getEndDate() {
        return this.EndDate.format(Task.INPUT_FORMATER);
    }

    @Override
    public String getTaskType() {
        return "EventTask";
    }

    @Override
    public String toString() {
        String text = "[E]";
        text += super.toString() + " " + getPeriod();
        return text;
    }
}
