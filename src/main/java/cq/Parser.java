package cq;

/**
 * A Parser class that identifies user command types.
 */
public class Parser {

    /**
     * Represents the command types the chatbot can process.
     */
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    /**
     * Parse user's command to identify the command type.
     *
     * @param input user's input command.
     * @return the corresponding CommandType, or INVALID if unrecognized.
     */
    public static CommandType parse(String input) {
        String[] inputs = input.split(" ");
        String command = inputs[0].toLowerCase();

        switch (command) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "delete":
                return CommandType.DELETE;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            default:
                return CommandType.INVALID;
        }
    }
}
