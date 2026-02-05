package cq;

import java.util.Scanner;


/**
 * Main application class which handles user interaction
 * with Cq chatbot through a command-line interface.
 */
public class ChatbotApp {
    private final Cq bot;
    private final Scanner sc;
    /**
     * Constructs a new ChatbotApp instance.
     * Initializes the chatbot engine and sets up the input scanner
     * for reading user input from the console.
     */
    public ChatbotApp() {
        this.bot = new Cq();
        this.sc = new Scanner(System.in);
    }

    /**
     * Runs a main input loop, continuously reading and processing
     * user commands until "bye" is received.
     */
    public void run() {
        Ui.showHeader();
        this.bot.greet();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ");
            Parser.CommandType command = Parser.parse(input);

            try {
                switch (command) {
                case BYE:
                    this.bot.bye();
                    return;
                case LIST:
                    this.bot.listItems();
                    break;
                case MARK:
                    bot.markAsDone(Integer.parseInt(inputs[1]));
                    break;
                case UNMARK:
                    bot.markAsNotDone(Integer.parseInt(inputs[1]));
                    break;
                case DELETE:
                    bot.removeTaskFromList(Integer.parseInt(inputs[1]));
                    break;
                case TODO:
                    bot.handleTodo(input);
                    break;
                case DEADLINE:
                    bot.handleDeadline(input);
                    break;
                case EVENT:
                    bot.handleEvent(input);
                    break;
                case FIND:
                    bot.findTask(inputs[1]);
                    break;
                case INVALID:
                    throw new InvalidInputException("Invalid input! :(");
                default:
                    throw new IllegalStateException("Unhandled command type: " + command);
                }
            } catch (InvalidInputException e) {
                this.bot.showMessage(e.getMessage());
            }
        }
    }
}
