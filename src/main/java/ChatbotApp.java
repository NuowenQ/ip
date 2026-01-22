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

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    this.bot.ListItems();
                } else if (inputs[0].equals("mark")) {
                    bot.MarkAsDone(Integer.parseInt(inputs[1]));
                } else if  (inputs[0].equals("unmark")) {
                    bot.MarkAsNotDone(Integer.parseInt(inputs[1]));
                } else if (inputs[0].equals("todo")) {
                        try {
                            if (input.length() <= "todo".length()) {
                                throw new IncompleteDescriptionException("The description for todo is empty");
                            }

                            String description = input.substring("todo".length()).trim();

                            if (description.isEmpty()) {
                                throw new IncompleteDescriptionException("The description for todo is empty");
                            }
                            bot.AddToDoToList(input.substring(inputs[0].length() + 1));
                        } catch (IncompleteDescriptionException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (inputs[0].equals("deadline")) {
                        try {
                            String subString = input.substring(inputs[0].length() + 1);
                            String[] subStrings = subString.split(" /");
                            if (subStrings.length != 2) {
                                throw new IncompleteDescriptionException("Incorrect description format for deadline task!");
                            }
                            bot.AddDeadlineToList(subStrings[0], subStrings[1]);
                        }  catch (IncompleteDescriptionException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (inputs[0].equals("event")) {
                        try {
                            String subString = input.substring(inputs[0].length() + 1);
                            String[] subStrings = subString.split(" /");
                            if (subStrings.length != 3) {
                                throw new IncompleteDescriptionException("Incorrect description format for event task!");
                            }
                            bot.AddEventToList(subStrings[0], subStrings[1], subStrings[2]); // Change to override method in the future.
                        } catch (IncompleteDescriptionException e) {
                            System.out.println(e.getMessage());
                        }

                    } else {
                    throw new InvalidInputException("Invalid input ! :(");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
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
