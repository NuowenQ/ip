import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class List {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/cq.txt";

    ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public void loadDataFromFile() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            return;
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try {
            java.util.List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    this.list.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public Task parseTaskFromLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3 || parts.length > 5) {
                return null;
            }

            String taskType = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String name =  parts[2].trim();

            Task task;
            switch(taskType) {
                case "T":
                    if (parts.length != 3) {
                        return null;
                    }

                    task = new ToDoTask(name);
                    break;

                case "D":
                    if (parts.length != 4) {
                        return null;
                    }

                    String deadLine = parts[3].trim();
                    task = new DeadlineTask(name, deadLine);
                    break;

                case "E":
                    if (parts.length != 5) {
                        return null;
                    }

                    String startDate = parts[3].trim();
                    String endDate = parts[4].trim();
                    task = new EventTask(name, startDate, endDate);
                    break;
                default:
                    return null;
            }

            if (isDone) {
                task.setAsComplete();
            }

            return task;

        } catch (Exception e) {
            System.out.println("Error parsing line: " + e.getMessage());
            return null;
        }
    }

    public void linesToFile() {
        try {
            Files.createDirectories(Path.of(DIRECTORY_PATH));

            ArrayList<String> lines = taskToLines();
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public ArrayList<String> taskToLines() {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : this.list) {
            String status;
            String taskType = task.getTaskType();
            String line;

            if (task.getCompleteStatus()) {
                status = "1";
            } else {
                status = "0";
            }

            switch(taskType) {
                case "ToDoTask": //T | 1 | read book
                    line = "T | " + status + " | " + task.getName();
                    break;

                case "DeadlineTask":
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    line = "D | " + status + " | " + deadlineTask.getName() + " | " + deadlineTask.getDeadline();
                    break;

                case "EventTask":
                    EventTask eventTask = (EventTask) task;
                    line = "E | " + status + " | " + task.getName() + " | " + eventTask.getStartDate() + " | " + eventTask.getEndDate();
                    break;

                default:
                    continue;
            }

            lines.add(line);
        }

        return lines;
    }

    public String ListSetAsDone(int rank) {
        String message = this.list.get(rank).setAsComplete();
        message = "Nice! I've marked this task as done:\n" + message;
        return message;
    }

    public String ListSetAsNotDone(int rank) {
        String message = this.list.get(rank).setAsIncomplete();
        message = "OK, I've marked this task as not done yet:\n" + message;
        return message;
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public String RemoveByRank(int rank) {
        String message = "Noted. I've removed this task:\n";
        message += this.list.get(rank).toString();
        this.list.remove(rank);
        message += "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String AddToDoItem(String itemName) {
        String message = "Got it. I've added this task:\n";
        ToDoTask item = new ToDoTask(itemName);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String AddDeadlineItem(String itemName, String DeadLine) {
        String message = "Got it. I've added this task:\n";
        DeadlineTask item = new DeadlineTask(itemName, DeadLine);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    public String AddEventItem(String itemName, String StartDate, String EndDate) {
        String message = "Got it. I've added this task:\n";
        EventTask item = new EventTask(itemName, StartDate, EndDate);
        message += item.toString() + "\n";
        this.list.add(item);
        message += "Now you have " + this.getNumberOfTasks() + " tasks in the list.";
        return message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;

        if (this.list.isEmpty()) {
            return "No items in list";
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!(i == this.list.size() - 1)) {
                builder.append(counter).append(".").append(this.list.get(i).toString()).append("\n");
            } else {
                builder.append(counter).append(".").append(this.list.get(i).toString());
            }
            counter++;
        }

        return builder.toString();
    }
}
