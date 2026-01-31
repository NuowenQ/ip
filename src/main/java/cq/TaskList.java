package cq;

import java.util.ArrayList;

/**
 * A class that stores a list of tasks.
 */
public class TaskList {
    ArrayList<Task> list;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list the initial list of tasks imported from file.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Sets a task as done.
     *
     *@param rank the 0 indexed rank of the task.
     *@return the confirmation message.
     */
    public String ListSetAsDone(int rank) {
        String message = this.list.get(rank).setAsComplete();
        message = "Nice! I've marked this task as done:\n" + message;
        return message;
    }

    /**
     * Sets a task as not done.
     *
     * @param rank the 0 indexed rank of the task.
     * @return the confirmation message
     */
    public String ListSetAsNotDone(int rank) {
        String message = this.list.get(rank).setAsIncomplete();
        message = "OK, I've marked this task as not done yet:\n" + message;
        return message;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks.
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     * Removes a task from the list by rank.
     *
     * @param rank the 0 indexed rank of the task.
     * @return the confirmation message.
     */
    public String RemoveByRank(int rank) {
        String message = "Noted. I've removed this task:\n";
        message += this.list.get(rank).toString();
        this.list.remove(rank);
        message += "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param itemName the task description.
     * @return the confirmation message.
     */
    public String AddToDoItem(String itemName) {
        String message = "Got it. I've added this task:\n";
        ToDoTask item = new ToDoTask(itemName);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param itemName the task description.
     * @param DeadLine the deadline in input date format.
     * @return the confirmation message.
     */
    public String AddDeadlineItem(String itemName, String DeadLine) {
        String message = "Got it. I've added this task:\n";
        DeadlineTask item = new DeadlineTask(itemName, DeadLine);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param itemName the task description
     * @param StartDate the start date of the event
     * @param EndDate the end date of the event
     * @return the confirmation message
     */
    public String AddEventItem(String itemName, String StartDate, String EndDate) {
        String message = "Got it. I've added this task:\n";
        EventTask item = new EventTask(itemName, StartDate, EndDate);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return the formatted task list, or "No items in list" if empty
     */
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
