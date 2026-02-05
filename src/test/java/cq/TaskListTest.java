package cq;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void addToDoItem_validName_increasesSize() {
        taskList.addToDoItem("read book");
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void removeByRank_decreasesSize() {
        taskList.addToDoItem("task 1");
        taskList.addToDoItem("task 2");
        taskList.removeByRank(0);
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void toString_emptyList_returnsNoItems() {
        assertEquals("No items in list", taskList.toString());
    }
}
