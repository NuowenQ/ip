package cq;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public String listSetAsDone(int rank) {
        String message = this.list.get(rank).setAsComplete();
        message = "Nice! I've marked this task as done:\n" + message;
        return message;
    }

    public String listSetAsNotDone(int rank) {
        String message = this.list.get(rank).setAsIncomplete();
        message = "OK, I've marked this task as not done yet:\n" + message;
        return message;
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public String removeByRank(int rank) {
        String message = "Noted. I've removed this task:\n";
        message += this.list.get(rank).toString();
        this.list.remove(rank);
        message += "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String addToDoItem(String itemName) {
        String message = "Got it. I've added this task:\n";
        ToDoTask item = new ToDoTask(itemName);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String addDeadlineItem(String itemName, String deadLine) {
        String message = "Got it. I've added this task:\n";
        DeadlineTask item = new DeadlineTask(itemName, deadLine);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String addEventItem(String itemName, String startDate, String endDate) {
        String message = "Got it. I've added this task:\n";
        EventTask item = new EventTask(itemName, startDate, endDate);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
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
