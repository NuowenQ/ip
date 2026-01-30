package cq;

public class Cq {
    private Ui ui;
    String name;
    Storage storage;
    TaskList cqlist;

    public Cq() {
        this.name = "CQ";
        this.storage = new Storage();
        this.cqlist = new TaskList(storage.loadDataFromFile());
        this.ui = new Ui();
    }

    public void greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        ui.constructMessage(message);
    }

    public void bye() {
        this.storage.linesToFile(cqlist.getList());
        String message = "Bye. Hope to see you again soon!";
        ui.constructMessage(message);
    }

    public void addToDoToList(String userInput) {
        String message = cqlist.AddToDoItem(userInput);
        ui.constructMessage(message);
    }

    public void removeTaskFromList(int rank) {
        ui.constructMessage(cqlist.RemoveByRank(rank - 1));
    }

    public void addDeadlineToList(String userInput, String deadLine) {
        String message = cqlist.AddDeadlineItem(userInput, deadLine.substring(3));
        ui.constructMessage(message);
    }

    public void addEventToList(String userInput, String startDate, String endDate) {
        String message = cqlist.AddEventItem(userInput, startDate.substring(5), endDate.substring(3));
        ui.constructMessage(message);
    }

    public void listItems() {
        String message = "Here are the tasks in your list:\n";
        message += cqlist.toString();
        ui.constructMessage(message);
    }

    public void markAsDone(int rank) {
        ui.constructMessage(cqlist.ListSetAsDone(rank - 1));
    }

    public void markAsNotDone(int rank) {
        ui.constructMessage(cqlist.ListSetAsNotDone(rank - 1));
    }

    public void showMessage(String message) {
        ui.constructMessage(message);
    }

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
