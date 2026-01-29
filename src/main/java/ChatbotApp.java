import java.util.Scanner;

public class ChatbotApp {
    public Cq bot;
    Scanner sc;

    public ChatbotApp() {
        this.bot = new Cq();
        this.sc = new Scanner(System.in);
    }

    // Overall orchestration logic
    public void run() {
        Ui.showHeader();
        this.bot.greet();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputs =  input.split(" ");

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    this.bot.listItems();
                } else if (inputs[0].equals("mark")) {
                    bot.markAsDone(Integer.parseInt(inputs[1]));
                } else if  (inputs[0].equals("unmark")) {
                    bot.markAsNotDone(Integer.parseInt(inputs[1]));
                } else if (inputs[0].equals("delete")) {
                    bot.removeTaskFromList(Integer.parseInt(inputs[1]));
                } else if (inputs[0].equals("todo")) {
                        try {
                            if (input.length() <= "todo".length()) {
                                throw new IncompleteDescriptionException("The description for todo is empty");
                            }

                            String description = input.substring("todo".length()).trim();

                            if (description.isEmpty()) {
                                throw new IncompleteDescriptionException("The description for todo is empty");
                            }
                            bot.addToDoToList(input.substring(inputs[0].length() + 1));
                        } catch (IncompleteDescriptionException e) {
                            this.bot.showMessage(e.getMessage());
                        }

                    } else if (inputs[0].equals("deadline")) {
                        try {
                            String subString = input.substring(inputs[0].length() + 1);
                            String[] subStrings = subString.split(" /");
                            if (subStrings.length != 2) {
                                throw new IncompleteDescriptionException("Incorrect description format for deadline task!");
                            }
                            bot.addDeadlineToList(subStrings[0], subStrings[1]);
                        }  catch (IncompleteDescriptionException e) {
                            this.bot.showMessage(e.getMessage());
                        }

                    } else if (inputs[0].equals("event")) {
                        try {
                            String subString = input.substring(inputs[0].length() + 1);
                            String[] subStrings = subString.split(" /");
                            if (subStrings.length != 3) {
                                throw new IncompleteDescriptionException("Incorrect description format for event task!");
                            }
                            bot.addEventToList(subStrings[0], subStrings[1], subStrings[2]); // Change to override method in the future.
                        } catch (IncompleteDescriptionException e) {
                            this.bot.showMessage(e.getMessage());
                        }

                    } else {
                    throw new InvalidInputException("Invalid input ! :(");
                }
            } catch (InvalidInputException e) {
                this.bot.showMessage(e.getMessage());
            }
        }
        this.bot.bye();
    }
}
