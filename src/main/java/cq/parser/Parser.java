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
        String[] inputs = input.split(" ");
        String command = inputs[0].toLowerCase();

        switch (command) {
        case "bye":
            return UserCommandType.BYE;
        case "list":
            return UserCommandType.LIST;
        case "mark":
            return UserCommandType.MARK;
        case "unmark":
            return UserCommandType.UNMARK;
        case "delete":
            return UserCommandType.DELETE;
        case "todo":
            return UserCommandType.TODO;
        case "deadline":
            return UserCommandType.DEADLINE;
        case "event":
            return UserCommandType.EVENT;
        case "find":
            return UserCommandType.FIND;
        case "view_schedule":
            return UserCommandType.VIEW_SCHEDULES;
        default:
            return UserCommandType.INVALID;
        }
    }
}
