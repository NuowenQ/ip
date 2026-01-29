public class Cq {
    private Ui ui;
    String name;
    List cqlist;

    public Cq() {
        this.name = "CQ";
        this.cqlist = new List();
        this.cqlist.loadDataFromFile();
        this.ui = new Ui();
    }

    public void greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        ui.constructMessage(message);
    }

    public void bye() {
        cqlist.linesToFile();
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
}
