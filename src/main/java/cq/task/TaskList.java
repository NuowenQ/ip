package cq.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cq.enums.TaskType;

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
        String message;

        try {
            String taskName = this.list.get(rank).getName();
            this.list.remove(rank);
            message = "Noted. I've removed this task:\n";
            message += taskName;
            message += "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            message = "Invalid input, index out of bound!";
        }

        return message;
    }

    /**
     * Find tasks that match the given date
     *
     * @param date date that the user wants to search
     * @return the macthed tasks
     */
    public String findMatchedTasksToDate(String date) {
        LocalDate targetDate = LocalDate.parse(date, Task.INPUT_FORMATTER);

        return IntStream.range(0, list.size())
                .filter(i -> list.get(i).isOnDate(targetDate))
                .mapToObj(i -> (i + 1) + "." + list.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Removes a task from the list by rank.
     *
     * @param task Type of the task
     * @param descriptions The descriptions for the tasks
     * @return string of the confirmation message
     */
    public String addTaskToList(TaskType task, String ...descriptions) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");

        switch (task) {
        case TODO:
            ToDoTask todoTask = new ToDoTask(descriptions[0]);
            sb.append(todoTask.toString()).append("\n");
            list.add(todoTask);
            break;
        case DEADLINE:
            DeadlineTask deadlineTask = new DeadlineTask(descriptions[0], descriptions[1]);
            sb.append(deadlineTask.toString()).append("\n");
            list.add(deadlineTask);
            break;
        case EVENT:
            EventTask eventTask = new EventTask(descriptions[0], descriptions[1], descriptions[2]);
            sb.append(eventTask.toString()).append("\n");
            list.add(eventTask);
            break;
        default:
            throw new IllegalArgumentException("Unsupported task type: " + task);
        }

        sb.append("Now you have ").append(this.getNumberOfTasks()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param itemName the task description.
     * @return the confirmation message.
     */
    public String addToDoItem(String itemName) {
        return addTaskToList(TaskType.TODO, itemName);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param itemName the task description.
     * @param deadLine the deadline in input date format.
     * @return the confirmation message.
     */
    public String addDeadlineItem(String itemName, String deadLine) {
        return addTaskToList(TaskType.DEADLINE, itemName, deadLine);
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
        return addTaskToList(TaskType.EVENT, itemName, startDate, endDate);
    }

    /**
     * Returns a string message of tasks consists of all the
     * tasks that match the key word.
     *
     * @param keyWord the key word that the user is searching.
     * @return message in string format.
     */
    public String findMatchedTasks(String keyWord) {
        return IntStream.range(0, list.size())
                .filter(i -> list.get(i).getName().contains(keyWord))
                .mapToObj(i -> (i + 1) + "." + list.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return the formatted task list, or "No items in list" if empty
     */
    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "No items in list";
        }

        return IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + "." + list.get(i).toString())
                .collect(Collectors.joining("\n"));
    }
}
