public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "ToDoTask";
    }

    @Override
    public String toString() {
        String text = "[T]";
        text += super.toString();
        return text;
    }
}
