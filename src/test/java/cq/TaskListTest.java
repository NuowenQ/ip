package cq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void addToDoItem_validName_increasesSize() {
        taskList.AddToDoItem("read book");
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void removeByRank_decreasesSize() {
        taskList.AddToDoItem("task 1");
        taskList.AddToDoItem("task 2");
        taskList.RemoveByRank(0);
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void toString_emptyList_returnsNoItems() {
        assertEquals("No items in list", taskList.toString());
    }
}
