package cq;

/**
 * A class that represents the chatbot itself, providing APIs to resolve tasks.
 */
public class Cq {
    private Ui ui;
    String name;
    Storage storage;
    TaskList cqlist;

    /**
     * Constructs a new Cq chatbot with default settings and loads
     * existing tasks from storage.
     */
    public Cq() {
        this.name = "CQ";
        this.storage = new Storage();
        this.cqlist = new TaskList(storage.loadDataFromFile());
        this.ui = new Ui();
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        ui.constructMessage(message);
    }

    /**
     * Says goodbye to the user and saves tasks to storage.
     */
    public void bye() {
        this.storage.linesToFile(cqlist.getList());
        String message = "Bye. Hope to see you again soon!";
        ui.constructMessage(message);
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param userInput the task description
     */
    public void addToDoToList(String userInput) {
        String message = cqlist.AddToDoItem(userInput);
        ui.constructMessage(message);
    }

    /**
     * Removes a task from the list.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public void removeTaskFromList(int rank) {
        ui.constructMessage(cqlist.RemoveByRank(rank - 1));
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param userInput the task description
     * @param deadLine the deadline string
     */
    public void addDeadlineToList(String userInput, String deadLine) {
        String message = cqlist.AddDeadlineItem(userInput, deadLine.substring(3));
        ui.constructMessage(message);
    }

    /**
     * Adds an Event task to the list.
     *
     * @param userInput the task description
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     */
    public void addEventToList(String userInput, String startDate, String endDate) {
        String message = cqlist.AddEventItem(userInput, startDate.substring(5), endDate.substring(3));
        ui.constructMessage(message);
    }

    /**
     * Lists all tasks to the user.
     */
    public void listItems() {
        String message = "Here are the tasks in your list:\n";
        message += cqlist.toString();
        ui.constructMessage(message);
    }

    /**
     * Marks a task as done.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public void markAsDone(int rank) {
        ui.constructMessage(cqlist.ListSetAsDone(rank - 1));
    }

    /**
     * Marks a task as not done.
     *
     * @param rank the 1 indexed position of the task in the list
     */
    public void markAsNotDone(int rank) {
        ui.constructMessage(cqlist.ListSetAsNotDone(rank - 1));
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
     * Parses and adds a Todo task from user input.
     *
     * @param input the raw user input string
     */
    public void handleTodo(String input) {
        try {
            String description = input.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new IncompleteDescriptionException("The description for todo is empty");
            }
            addToDoToList(description);
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
        }
    }

    /**
     * Parses and adds a Deadline task from user input.
     *
     * @param input the raw user input string
     */
    public void handleDeadline(String input) {
        try {
            String subString = input.substring("deadline".length()).trim();
            String[] subStrings = subString.split(" /");
            if (subStrings.length != 2) {
                throw new IncompleteDescriptionException("Incorrect description format for deadline task!");
            }
            addDeadlineToList(subStrings[0], subStrings[1]);
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
        }
    }

    /**
     * Parses and adds an Event task from user input.
     *
     * @param input the raw user input string
     */
    public void handleEvent(String input) {
        try {
            String subString = input.substring("event".length()).trim();
            String[] subStrings = subString.split(" /");
            if (subStrings.length != 3) {
                throw new IncompleteDescriptionException("Incorrect description format for event task!");
            }
            addEventToList(subStrings[0], subStrings[1], subStrings[2]);
        } catch (IncompleteDescriptionException e) {
            showMessage(e.getMessage());
        }
    }

}
