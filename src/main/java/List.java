import java.util.ArrayList;

public class List {
    ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public String AddItem(String itemName) {
        String message = "added: " + itemName;
        Task item = new Task(itemName);
        this.list.add(item);
        return message;
    }

    public String ListSetAsDone(int rank) {
        String message = this.list.get(rank).setAsComplete();
        message = "Nice! I've marked this task as done:\n" + message;
        return message;
    }

    public String ListSetAsNotDone(int rank) {
        String message = this.list.get(rank).setAsIncomplete();
        message = "OK, I've marked this task as not done yet:\n" + message;
        return message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;

        if (this.list.isEmpty()) {
            return "No items in list";
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!(i == this.list.size() - 1)) {
                builder.append(counter).append(".").append(this.list.get(i).toString()).append("\n");
            } else {
                builder.append(counter).append(".").append(this.list.get(i).toString());
            }
            counter++;
        }

        return builder.toString();
    }
}
