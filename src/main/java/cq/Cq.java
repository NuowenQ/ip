package cq;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cq.exceptions.IncompleteDescriptionException;
import cq.exceptions.InvalidInputException;
import cq.messages.Response;
import cq.storage.Storage;
import cq.task.Task;
import cq.task.TaskList;
import cq.ui.Ui;
import javafx.application.Platform;

/**
 * A class that represents the chatbot itself, providing APIs to resolve tasks.
 */
public class Cq {
    private final Ui ui;
    private final String name;
    private final Storage storage;
    private final TaskList cqList;

    /**
     * Constructs a new Cq chatbot with default settings and loads
     * existing tasks from storage.
     */
    public Cq() {
        this.name = "Speed";
        this.storage = new Storage();
        this.cqList = new TaskList(storage.loadDataFromFile());
        this.ui = new Ui();
    }

    /**
     * Greets the user with a welcome message.
     */
    public Response greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        ui.constructMessage(message);
        return new Response(message);
    }

    /**
     * Says goodbye to the user and saves tasks to storage.
     */
    public Response bye() {
        this.storage.linesToFile(cqList.getList());
        String message = "Bye. Hope to see you again soon!";
        Platform.exit();
        return new Response(ui.constructMessage(message));
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param userInput the task description
     */
    public String addToDoToList(String userInput) {
        String message = cqList.addToDoItem(userInput);
        assert !message.isEmpty();
        return ui.constructMessage(message);
    }

    /**
     * Removes a task from the list.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public Response removeTaskFromList(int rank) {
        Response response = cqList.removeByRank(rank - 1);
        ui.constructMessage(response.getMessage());
        return response;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param userInput the task description
     * @param deadLine the deadline string
     */
    public Response addDeadlineToList(String userInput, String deadLine) {
        try {
            LocalDate deadLineDate = extractDate(deadLine);
            String message = cqList.addDeadlineItem(userInput, deadLineDate);
            ui.constructMessage(message);
            return new Response(message);
        } catch (InvalidInputException e) {
            ui.constructMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        }
    }

    /**
     * Adds an Event task to the list.
     *
     * @param userInput the task description
     * @param start the start date of the event
     * @param end the end date of the event
     */
    public Response addEventToList(String userInput, String start, String end) {
        try {
            LocalDate startDate = extractDate(start);
            LocalDate endDate = extractDate(end);

            if (endDate.isBefore(startDate)) {
                throw new InvalidInputException("End date cannot be before start date!"); // change to respond.
            }

            String message = cqList.addEventItem(userInput, startDate, endDate);
            ui.constructMessage(message);
            return new Response(message);

        } catch (InvalidInputException e) {
            ui.constructMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        }
    }

    /**
     * Extract date from a given input
     * @param input input string from the user
     * @return Parsed date
     * @throws InvalidInputException if no valid date is found
     */
    public LocalDate extractDate(String input) throws InvalidInputException {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return LocalDate.parse(matcher.group(), Task.INPUT_FORMATTER);
        }
        throw new InvalidInputException("Date format is invalid!");
    }

    /**
     * Lists all tasks to the user.
     */
    public Response listItems() {
        String message = "Here are the tasks in your list:\n";
        message += cqList.toString();
        return new Response(ui.constructMessage(message));
    }

    /**
     * Marks a task as done.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public Response markAsDone(int rank) {
        Response response = cqList.listSetAsDone(rank - 1);
        ui.constructMessage(response.getMessage());
        return response;
    }

    /**
     * Marks a task as not done.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public Response markAsNotDone(int rank) {
        Response response = cqList.listSetAsNotDone(rank - 1);
        ui.constructMessage(response.getMessage());
        return response;
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        ui.constructMessage(message);
    }

    /**
     * Constructs a message displaying tasks that match the given keyword.
     *
     * @param keyWord The keyword used to match tasks.
     */
    public Response findTask(String keyWord) {
        String message = "Here are the matching tasks in your list:\n";
        message += cqList.findMatchedTasks(keyWord);
        return new Response(ui.constructMessage(message));
    }

    /**
     * Constructs a message displaying tasks scheduled on the given date.
     *
     * @param date The date used to search for matching tasks.
     */
    public Response showSchedule(String date) {
        String message = "Here are the tasks on " + date + ":\n";
        message += cqList.findMatchedTasksToDate(date);
        return new Response(ui.constructMessage(message));
    }

    /**
     * Parses and adds a Todo task from user input.
     *
     * @param input the raw user input string
     */
    public Response handleTodo(String input) {
        try {
            String description = input.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new IncompleteDescriptionException("The description for todo is empty");
            }
            return new Response(addToDoToList(description));
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        }
    }

    /**
     * Parses and adds a Deadline task from user input.
     *
     * @param input the raw user input string
     */
    public Response handleDeadline(String input) {
        try {
            String subString = input.substring("deadline".length()).trim();
            String[] subStrings = subString.split(" /by");
            if (subStrings.length != Task.DEADLINE_TASK_INFORMATION_LENGTH) {
                throw new IncompleteDescriptionException("Incorrect description format for deadline task!");
            }
            return addDeadlineToList(subStrings[0], subStrings[1].trim());
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        } catch (DateTimeParseException e) {
            showMessage("Wrong date format for deadline task!");
            return new Response("Wrong date format for deadline task!", true);
        }
    }

    /**
     * Parses and adds an Event task from user input.
     *
     * @param input the raw user input string
     */
    public Response handleEvent(String input) {
        try {
            String subString = input.substring("event".length()).trim();
            String[] subStrings = subString.split(" /");
            if (subStrings.length != Task.EVENT_TASK_INFORMATION_LENGTH) {
                throw new IncompleteDescriptionException("Incorrect description format for event task!");
            }
            return addEventToList(subStrings[0], subStrings[1], subStrings[2]);
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        }
    }
}
