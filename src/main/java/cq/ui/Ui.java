package cq.ui;

/**
 * A UI class that handles displaying messages and visual elements
 * to the user in command line interface.
 */
public class Ui {

    /**
     * Constructs the chatbot logo and welcome the user.
     */
    public static void showHeader() {
        String logo = "  ____  ___  \n"
                + " / ___|/ _ \\ \n"
                + "| |   | | | |\n"
                + "| |___| |_| |\n"
                + " \\____|\\__\\_\\\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Constructs messages formatted with horizontal borderlines
     * and output to user.
     *
     * @param message the message to display
     */
    public String constructMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
        return message; // For GUI
    }
}
