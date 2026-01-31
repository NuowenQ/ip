package cq;

/**
 * A UI class that handles displaying messages and visual elements
 * to the user in command line interface.
 */
public class Ui {

    /**
     * Constructs the chatbot logo and welcome the user.
     */
    public static void showHeader() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Constructs messages formatted with horizontal borderlines
     * and output to user.
     *
     * @param message the message to display
     */
    public void constructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
