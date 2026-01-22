public class CQ {
    String name;

    public CQ() {
        this.name = "CQ";
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

    public void ConstructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
