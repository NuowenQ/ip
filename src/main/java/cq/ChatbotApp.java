package cq;

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
