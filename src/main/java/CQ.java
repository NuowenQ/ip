public class CQ {
    String name;
    List cqlist;

    public CQ() {
        this.name = "CQ";
        this.cqlist = new List();
    }

    public void Greet() {
        String message = "Hello I'm " + this.name;
        message += "\nWhat can I do for you?";
        this.ConstructMessage(message);
    }

    public void Bye() {
        String message = "Bye. Hope to see you again soon!";
        this.ConstructMessage(message);
    }

    public void AddToList(String userInput) {
        String message = cqlist.AddItem(userInput);
        ConstructMessage(message);
    }

    public void ListItems() {
        String message = "Here are the tasks in your list:\n";
        message += cqlist.toString();
        this.ConstructMessage(message);
    }

    public void MarkAsDone(int rank) {
        ConstructMessage(cqlist.ListSetAsDone(rank - 1));
    }

    public void MarkAsNotDone(int rank) {
        ConstructMessage(cqlist.ListSetAsNotDone(rank - 1));
    }

    public void ConstructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
