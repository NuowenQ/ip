package cq;

import cq.enums.UserCommandType;
import cq.exceptions.InvalidInputException;
import cq.parser.Parser;
import cq.ui.Ui;

/**
 * Main application class which handles user interaction
 * with Cq chatbot through a command-line interface.
 */
public class ChatbotApp {
    private Cq bot;

    /**
     * Constructs a new ChatbotApp instance.
     * Initializes the chatbot engine and sets up the input scanner
     * for reading user input from the console.
     */
    public ChatbotApp(Cq bot) {
        this.bot = bot;
        Ui.showHeader();
        this.bot.greet();
    }

    /**
     * A run method for GUI
     */
    public String run(String input) {
        String[] inputs = input.split(" ");
        UserCommandType command = Parser.parse(input);
        try {
            return switch (command) {
            case BYE -> this.bot.bye();
            case LIST -> this.bot.listItems();
            case MARK -> bot.markAsDone(Integer.parseInt(inputs[1]));
            case UNMARK -> bot.markAsNotDone(Integer.parseInt(inputs[1]));
            case DELETE -> bot.removeTaskFromList(Integer.parseInt(inputs[1]));
            case TODO -> bot.handleTodo(input);
            case DEADLINE -> bot.handleDeadline(input);
            case EVENT -> bot.handleEvent(input);
            case FIND -> bot.findTask(inputs[1]);
            case VIEW_SCHEDULES -> bot.showSchedule(inputs[1]);
            case INVALID -> throw new InvalidInputException("Invalid input! :(");
            default -> throw new IllegalStateException("Unhandled command type: " + command);
            };
        } catch (InvalidInputException e) {
            this.bot.showMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
