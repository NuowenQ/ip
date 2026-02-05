package cq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to storage.
 * Tasks are stored in a text file with a predefined format.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/cq.txt";
    private final ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Loads tasks from the storage file.
     * Creates the file and directory if they do not exist.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty list if the file does not exist.
     */
    public ArrayList<Task> loadDataFromFile() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            return taskList;
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return taskList;
        }

        try {
            java.util.List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    this.taskList.add(task);
                }
            }
            return taskList;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return taskList;
    }
    /**
     * Parses a single line from the storage file into a Task object.
     * @param line The line to parse.
     * @return The parsed Task, or null if the line is invalid.
     */
    public Task parseTaskFromLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3 || parts.length > 5) {
                return null;
            }

            String taskType = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String name = parts[2].trim();

            Task task;
            switch (taskType) {
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
    /**
     * Saves the given list of tasks to the storage file.
     * Creates the data directory if it does not exist.
     *
     * @param list The list of tasks to save.
     */
    public void linesToFile(ArrayList<Task> list) {
        try {
            Files.createDirectories(Path.of(DIRECTORY_PATH));

            ArrayList<String> lines = taskToLines(list);
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
    /**
     * Converts a list of tasks into their string representations for file storage.
     *
     * @param list The list of tasks to convert.
     * @return An ArrayList of formatted strings representing each task.
     */
    public ArrayList<String> taskToLines(ArrayList<Task> list) {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : list) {
            String status;
            String taskType = task.getTaskType();
            String line;

            if (task.getCompleteStatus()) {
                status = "1";
            } else {
                status = "0";
            }

            switch (taskType) {
            case "cq.ToDoTask": //T | 1 | read book
                line = "T | " + status + " | " + task.getName();
                break;

            case "cq.DeadlineTask":
                DeadlineTask deadlineTask = (DeadlineTask) task;
                line = "D | " + status + " | " + deadlineTask.getName() + " | " + deadlineTask.getDeadLineForFile();
                break;

            case "cq.EventTask":
                EventTask eventTask = (EventTask) task;
                line = "E | "
                        + status
                        + " | "
                        + task.getName()
                        + " | "
                        + eventTask.getStartDate()
                        + " | "
                        + eventTask.getEndDate();
                break;

            default:
                continue;
            }

            lines.add(line);
        }

        return lines;
    }
}
