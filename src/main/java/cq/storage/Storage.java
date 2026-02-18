package cq.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import cq.enums.TaskType;
import cq.task.DeadlineTask;
import cq.task.EventTask;
import cq.task.Task;
import cq.task.ToDoTask;

/**
 * Handles loading and saving tasks to storage.
 * Tasks are stored in a text file with a predefined format.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/cq.txt";

    /**
     * Loads tasks from the storage file.
     * Creates the file and directory if they do not exist.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty list if the file does not exist.
     */
    public ArrayList<Task> loadDataFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
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
                    taskList.add(task);
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
            String taskType = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String name = parts[2].trim();
            Task task = getTask(taskType, parts, name);

            if (task != null && isDone) {
                task.setAsComplete();
            }

            return task;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static Task getTask(String taskType, String[] parts, String name) {
        int descriptionLength = 2;

        return switch (taskType) {
        case "T" -> parts.length == Task.TODO_TASK_INFORMATION_LENGTH + descriptionLength
                ? new ToDoTask(name)
                : null;
        case "D" -> parts.length == Task.DEADLINE_TASK_INFORMATION_LENGTH + descriptionLength
                ? new DeadlineTask(name, LocalDate.parse(parts[3].trim(), Task.INPUT_FORMATTER))
                : null;
        case "E" -> parts.length == Task.EVENT_TASK_INFORMATION_LENGTH + descriptionLength
                ? new EventTask(name,
                    LocalDate.parse(parts[3].trim(), Task.INPUT_FORMATTER),
                    LocalDate.parse(parts[4].trim(), Task.INPUT_FORMATTER))
                : null;
        default -> null;
        };
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
            String status = task.getComplete() ? "1" : "0";
            TaskType taskType = task.getTaskType();
            String line;

            switch (taskType) {
            case TODO:
                line = "T | " + status + " | " + task.getName();
                break;
            case DEADLINE:
                DeadlineTask deadlineTask = (DeadlineTask) task;
                line = "D | " + status + " | " + deadlineTask.getName() + " | " + deadlineTask.getDeadLineForFile();
                break;
            case EVENT:
                EventTask eventTask = (EventTask) task;
                line = "E | " + status + " | " + task.getName() + " | " + eventTask.getStartDate() + " | "
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
