package cq;

import java.util.ArrayList;

/**
 * A class that stores a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

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
     * @param rank the 0 indexed rank of the task.
     * @return the confirmation message.
     */
    public String listSetAsDone(int rank) {
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
    public String listSetAsNotDone(int rank) {
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
    public String removeByRank(int rank) {
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
    public String addToDoItem(String itemName) {
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
     * @param deadLine the deadline in input date format.
     * @return the confirmation message.
     */
    public String addDeadlineItem(String itemName, String deadLine) {
        String message = "Got it. I've added this task:\n";
        DeadlineTask item = new DeadlineTask(itemName, deadLine);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param itemName the task description
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     * @return the confirmation message
     */
    public String addEventItem(String itemName, String startDate, String endDate) {
        String message = "Got it. I've added this task:\n";
        EventTask item = new EventTask(itemName, startDate, endDate);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    /**
     * Returns a string message of tasks consists of all the
     * tasks that match the key word.
     *
     * @param keyWord the key word that the user is searching.
     * @return message in string format.
     */
    public String findMatchedTasks(String keyWord) {
        ArrayList<Task> matchedResults = new ArrayList<>();

        for (Task task : this.list) {
            String name = task.getName();
            if (name.contains(keyWord)) {
                matchedResults.add(task);
            }
        }

        String message = "";
        int counter = 1;
        for (Task task : matchedResults) {
            message += counter + "." + task.toString() + "\n";
            counter++;
        }

        return message.stripTrailing();
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
