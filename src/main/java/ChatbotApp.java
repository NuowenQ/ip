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

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.bot.ListItems();
            } else {
                bot.AddToList(input);
            }
            input = sc.nextLine();
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
