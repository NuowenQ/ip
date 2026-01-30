package cq;

public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "cq.ToDoTask";
    }

    @Override
    public String toString() {
        String text = "[T]";
        text += super.toString();
        return text;
    }
}
