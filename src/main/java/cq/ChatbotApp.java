package cq;

import java.util.Scanner;


/**
 * Main application class which handles user interaction
 * with Cq chatbot through a command-line interface.
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
public class ChatbotApp {
    private Cq bot;
    private final Scanner sc;

    /**
     * Constructs a new ChatbotApp instance.
     * Initializes the chatbot engine and sets up the input scanner
     * for reading user input from the console.
     */
    public ChatbotApp(Cq bot) {
        this.bot = bot;
        this.sc = new Scanner(System.in);
        Ui.showHeader();
        this.bot.greet();
    }

    /**
     * A run method for GUI
     */
    public String run(String input) {
        String[] inputs = input.split(" ");
        Parser.CommandType command = Parser.parse(input);
        try {
            switch (command) {
            case BYE:
                return this.bot.bye();
            case LIST:
                return this.bot.listItems();
            case MARK:
                return bot.markAsDone(Integer.parseInt(inputs[1]));
            case UNMARK:
                return bot.markAsNotDone(Integer.parseInt(inputs[1]));
            case DELETE:
                return bot.removeTaskFromList(Integer.parseInt(inputs[1]));
            case TODO:
                return bot.handleTodo(input);
            case DEADLINE:
                return bot.handleDeadline(input);
            case EVENT:
                return bot.handleEvent(input);
            case FIND:
                return bot.findTask(inputs[1]);
            case INVALID:
                throw new InvalidInputException("Invalid input! :(");
            default:
                throw new IllegalStateException("Unhandled command type: " + command);
            }
        } catch (InvalidInputException e) {
            this.bot.showMessage(e.getMessage());
            return e.getMessage();
        }
    }
}

//    /**
//     * Runs a main input loop, continuously reading and processing
//     * user commands until "bye" is received.
//     */
//    public String run() {
//        Ui.showHeader();
//        this.bot.greet();
//
//        while (sc.hasNextLine()) {
//            String input = sc.nextLine();
//            String[] inputs = input.split(" ");
//            Parser.CommandType command = Parser.parse(input);
//
//            try {
//                switch (command) {
//                case BYE:
//                    return this.bot.bye();
//                    break;
//                case LIST:
//                    return this.bot.listItems();
//                    break;
//                case MARK:
//                    return bot.markAsDone(Integer.parseInt(inputs[1]));
//                    break;
//                case UNMARK:
//                    return bot.markAsNotDone(Integer.parseInt(inputs[1]));
//                    break;
//                case DELETE:
//                    return bot.removeTaskFromList(Integer.parseInt(inputs[1]));
//                    break;
//                case TODO:
//                    return bot.handleTodo(input);
//                    break;
//                case DEADLINE:
//                    return bot.handleDeadline(input);
//                    break;
//                case EVENT:
//                    return bot.handleEvent(input);
//                    break;
//                case FIND:
//                    return bot.findTask(inputs[1]);
//                    break;
//                case INVALID:
//                    throw new InvalidInputException("Invalid input! :(");
//                    return "Invalid input! :(";
//                    break;
//                default:
//                    throw new IllegalStateException("Unhandled command type: " + command);
//                    return "Unhandled command type: " + command;
//                    break;
//                }
//            } catch (InvalidInputException e) {
//                this.bot.showMessage(e.getMessage());
//                return e.getMessage();
//            }
//        }
//    }

