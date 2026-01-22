public class Task {
    String name;
    boolean completeStatus;

    public Task(String name) {
        this.name = name;
        this.completeStatus = false;
    }

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
