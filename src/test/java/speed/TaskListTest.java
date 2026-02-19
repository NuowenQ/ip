package speed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import speed.messages.Response;
import speed.task.TaskList;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void addToDoItemValidNameIncreasesSize() {
        taskList.addToDoItem("read book");
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void addDeadlineItemIncreasesSize() {
        taskList.addDeadlineItem("submit report", LocalDate.of(2025, 3, 1));
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void addEventItemIncreasesSize() {
        taskList.addEventItem("conference",
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 3));
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void addMultipleTasksIncreasesSize() {
        taskList.addToDoItem("task 1");
        taskList.addDeadlineItem("task 2", LocalDate.of(2025, 3, 1));
        taskList.addEventItem("task 3",
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 3));
        assertEquals(3, taskList.getNumberOfTasks());
    }

    @Test
    public void removeByRankValidRankDecreasesSize() {
        taskList.addToDoItem("task 1");
        taskList.addToDoItem("task 2");
        taskList.removeByRank(0);
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void removeByRankInvalidRankReturnsError() {
        taskList.addToDoItem("task 1");
        Response response = taskList.removeByRank(5);
        assertTrue(response.isError());
    }

    @Test
    public void removeByRankInvalidRankKeepsSize() {
        taskList.addToDoItem("task 1");
        taskList.removeByRank(5);
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void listSetAsDoneValidRankSuccess() {
        taskList.addToDoItem("read book");
        Response response = taskList.listSetAsDone(0);
        assertFalse(response.isError());
        assertTrue(taskList.getList().get(0).getComplete());
    }

    @Test
    public void listSetAsDoneInvalidRankReturnsError() {
        Response response = taskList.listSetAsDone(0);
        assertTrue(response.isError());
    }

    @Test
    public void listSetAsNotDoneValidRankSuccess() {
        taskList.addToDoItem("read book");
        taskList.listSetAsDone(0);
        Response response = taskList.listSetAsNotDone(0);
        assertFalse(response.isError());
        assertFalse(taskList.getList().get(0).getComplete());
    }

    @Test
    public void listSetAsNotDoneInvalidRankReturnsError() {
        Response response = taskList.listSetAsNotDone(0);
        assertTrue(response.isError());
    }

    @Test
    public void findMatchedTasksMatchingKeywordReturnsTasks() {
        taskList.addToDoItem("read book");
        taskList.addToDoItem("read notes");
        taskList.addToDoItem("buy groceries");
        String result = taskList.findMatchedTasks("read");
        assertTrue(result.contains("read book"));
        assertTrue(result.contains("read notes"));
        assertFalse(result.contains("buy groceries"));
    }

    @Test
    public void findMatchedTasksNoMatchReturnsEmpty() {
        taskList.addToDoItem("read book");
        String result = taskList.findMatchedTasks("xyz");
        assertEquals("", result);
    }

    @Test
    public void findMatchedTasksToDateReturnsMatchingTasks() {
        taskList.addDeadlineItem("submit report", LocalDate.of(2025, 3, 1));
        taskList.addDeadlineItem("other task", LocalDate.of(2025, 4, 1));
        String result = taskList.findMatchedTasksToDate("2025-03-01");
        assertTrue(result.contains("submit report"));
        assertFalse(result.contains("other task"));
    }

    @Test
    public void findMatchedTasksToDateEventWithinRangeMatches() {
        taskList.addEventItem("conference",
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 5));
        String result = taskList.findMatchedTasksToDate("2025-03-03");
        assertTrue(result.contains("conference"));
    }

    @Test
    public void findMatchedTasksToDateEventOutsideRangeNoMatch() {
        taskList.addEventItem("conference",
                LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 5));
        String result = taskList.findMatchedTasksToDate("2025-03-06");
        assertFalse(result.contains("conference"));
    }

    @Test
    public void toStringEmptyListReturnsNoItems() {
        assertEquals("No items in list", taskList.toString());
    }

    @Test
    public void toStringNonEmptyListReturnsFormattedTasks() {
        taskList.addToDoItem("read book");
        taskList.addToDoItem("buy milk");
        String result = taskList.toString();
        assertTrue(result.contains("1.[T][ ] read book"));
        assertTrue(result.contains("2.[T][ ] buy milk"));
    }
}