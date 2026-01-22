public class DeadlineTask extends Task {
    String Deadline;

    public DeadlineTask(String name, String Deadline) {
        super(name);
        this.Deadline = Deadline;
    }

    public String getDeadline() {
        return "(by: " + this.Deadline + ")";
    }

    @Override
    public String toString() {
        String text = "[D]";
        text += super.toString() + " " + this.getDeadline();
        return text;
    }
}
