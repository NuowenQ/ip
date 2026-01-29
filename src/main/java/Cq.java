public class Cq {
    String name;
    List cqlist;

    public Cq() {
        this.name = "CQ";
        this.cqlist = new List();
        this.cqlist.loadDataFromFile();
    }

    public void greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        this.constructMessage(message);
    }

    public void bye() {
        cqlist.linesToFile();
        String message = "Bye. Hope to see you again soon!";
        this.constructMessage(message);
    }

    public void addToDoToList(String userInput) {
        String message = cqlist.AddToDoItem(userInput);
        constructMessage(message);
    }

    public void removeTaskFromList(int rank) {
        constructMessage(cqlist.RemoveByRank(rank - 1));
    }

    public void addDeadlineToList(String userInput, String deadLine) {
        String message = cqlist.AddDeadlineItem(userInput, deadLine.substring(3));
        constructMessage(message);
    }

    public void addEventToList(String userInput, String startDate, String endDate) {
        String message = cqlist.AddEventItem(userInput, startDate.substring(5), endDate.substring(3));
        constructMessage(message);
    }

    public void listItems() {
        String message = "Here are the tasks in your list:\n";
        message += cqlist.toString();
        this.constructMessage(message);
    }

    public void markAsDone(int rank) {
        constructMessage(cqlist.ListSetAsDone(rank - 1));
    }

    public void markAsNotDone(int rank) {
        constructMessage(cqlist.ListSetAsNotDone(rank - 1));
    }

    public void constructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
