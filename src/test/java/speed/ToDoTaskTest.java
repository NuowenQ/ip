package speed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import speed.enums.TaskType;
import speed.task.ToDoTask;

public class ToDoTaskTest {

    @Test
    public void constructorValidNameSuccess() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("read book", task.getName());
    }

    @Test
    public void constructorNewTaskIsIncomplete() {
        ToDoTask task = new ToDoTask("read book");
        assertFalse(task.getComplete());
    }

    @Test
    public void getTaskTypeReturnsTodo() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals(TaskType.TODO, task.getTaskType());
    }

    @Test
    public void setAsCompleteMarksTaskDone() {
        ToDoTask task = new ToDoTask("read book");
        task.setAsComplete();
        assertTrue(task.getComplete());
    }

    @Test
    public void setAsIncompleteMarksTaskNotDone() {
        ToDoTask task = new ToDoTask("read book");
        task.setAsComplete();
        task.setAsIncomplete();
        assertFalse(task.getComplete());
    }

    @Test
    public void isOnDateAlwaysReturnsFalse() {
        ToDoTask task = new ToDoTask("read book");
        assertFalse(task.isOnDate(LocalDate.of(2025, 2, 19)));
    }

    @Test
    public void toStringNewTaskShowsIncomplete() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void toStringCompletedTaskShowsComplete() {
        ToDoTask task = new ToDoTask("read book");
        task.setAsComplete();
        assertEquals("[T][x] read book", task.toString());
    }
}