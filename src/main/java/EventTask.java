public class EventTask extends Task {
    String StartDate;
    String EndDate;

    public EventTask(String name, String StartDate, String EndDate) {
        super(name);
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public String getPeriod() {
        return "(from: " + this.StartDate + " to: " + this.EndDate + ")";
    }

    @Override
    public String toString() {
        String text = "[E]";
        text += super.toString() + " " + getPeriod();
        return text;
    }
}
