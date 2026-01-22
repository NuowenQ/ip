import java.util.Scanner;

public class ChatbotApp {
    public CQ bot;
    Scanner sc;

    public ChatbotApp() {
        this.bot = new CQ();
        this.sc = new Scanner(System.in);
    }

    // Overall orchestration logic
    public void run() {
        this.ShowHeader();
        this.bot.Greet();

        while (true) {
            String input = sc.nextLine();
            String[] inputs =  input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.bot.ListItems();
            } else if (inputs[0].equals("mark")) {
                bot.MarkAsDone(Integer.parseInt(inputs[1]));
            } else if  (inputs[0].equals("unmark")) {
                bot.MarkAsNotDone(Integer.parseInt(inputs[1]));
            } else {
                bot.AddToList(input);
            }
        }
        this.bot.Bye();
    }

    public void ShowHeader() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
