package speed;

import speed.enums.UserCommandType;
import speed.exceptions.InvalidInputException;
import speed.messages.Response;
import speed.parser.Parser;
import speed.ui.Ui;

/**
 * Main application class which handles user interaction
 * with Cq chatbot through a command-line interface.
 */
public class ChatbotApp {
    private Speed bot;

    /**
     * Constructs a new ChatbotApp instance.
     * Initializes the chatbot engine and sets up the input scanner
     * for reading user input from the console.
     */
    public ChatbotApp(Speed bot) {
        this.bot = bot;
        Ui.showHeader();
        this.bot.greet();
    }

    /**
     * Send a message to greet the user.
     */
    public Response greet() {
        return this.bot.greet();
    }

    /**
     * Parses the user input into a command type and processes it accordingly.
     */
    public Response run(String input) {
        UserCommandType command = Parser.parse(input);
        try {
            return switch (command) {
            case BYE -> this.bot.bye();
            case LIST -> this.bot.listItems();
            case MARK, UNMARK, DELETE, FIND, VIEW_SCHEDULES -> bot.handleSingleArgCommand(input, command);
            case TODO -> bot.handleTodo(input);
            case DEADLINE -> bot.handleDeadline(input);
            case EVENT -> bot.handleEvent(input);
            case INVALID -> throw new InvalidInputException("Invalid input! :(");
            default -> throw new IllegalStateException("Unhandled command type: " + command);
            };
        } catch (InvalidInputException e) {
            this.bot.showMessage(e.getMessage());
            return new Response(e.getMessage(), true);
        }
    }
}
