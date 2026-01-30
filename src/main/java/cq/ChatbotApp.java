package cq;

import java.util.Scanner;

public class ChatbotApp {
    public Cq bot;
    Scanner sc;

    public ChatbotApp() {
        this.bot = new Cq();
        this.sc = new Scanner(System.in);
    }

    // Overall orchestration logic
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
                    case INVALID:
                        throw new InvalidInputException("Invalid input! :(");
                }
            } catch (InvalidInputException e) {
                this.bot.showMessage(e.getMessage());
            }
        }
    }
}
