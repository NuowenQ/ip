package cq.parser;

import cq.enums.UserCommandType;

/**
 * A Parser class that identifies user command types.
 */
public class Parser {

    /**
     * Parse user's command to identify the command type.
     *
     * @param input user's input command.
     * @return the corresponding CommandType, or INVALID if unrecognized.
     */
    public static UserCommandType parse(String input) {
        String[] inputs = input.trim().split(" ");
        String command = inputs[0].toLowerCase();

        return switch (command) {
        case "bye" -> UserCommandType.BYE;
        case "list" -> UserCommandType.LIST;
        case "mark" -> UserCommandType.MARK;
        case "unmark" -> UserCommandType.UNMARK;
        case "delete" -> UserCommandType.DELETE;
        case "todo" -> UserCommandType.TODO;
        case "deadline" -> UserCommandType.DEADLINE;
        case "event" -> UserCommandType.EVENT;
        case "find" -> UserCommandType.FIND;
        case "view_schedule" -> UserCommandType.VIEW_SCHEDULES;
        default -> UserCommandType.INVALID;
        };
    }
}
