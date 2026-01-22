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

    public void Echo(String userInput) {
        String message = userInput;
        this.ConstructMessage(message);
    }

    public void AddToList(String userInput) {
        String item = userInput;
        System.out.println("____________________________________________________________");
        cqlist.AddItem(item);
        System.out.println("____________________________________________________________");
    }

    public void ListItems() {
        this.ConstructListMessage();
    }

    public void ConstructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void ConstructListMessage() {
        System.out.println("____________________________________________________________");
        cqlist.DisplayItems();
        System.out.println("____________________________________________________________");
    }

}
