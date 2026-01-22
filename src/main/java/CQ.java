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

//    public void Echo(String userInput) {
//        this.ConstructMessage(userInput);
//    }

    public void AddToList(String userInput) {
        String message = cqlist.AddItem(userInput);
        ConstructMessage(message);
    }

    public void ListItems() {
        String message = cqlist.toString();
        this.ConstructMessage(message);
    }

    public void ConstructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
