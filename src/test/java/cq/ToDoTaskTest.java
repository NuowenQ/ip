package cq;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void constructor_validName_success() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("read book", task.getName());
    }

    @Test
    public void toString_newTask_showsIncomplete() {
        ToDoTask task = new ToDoTask("read book");
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void toString_completedTask_showsComplete() {
        ToDoTask task = new ToDoTask("read book");
        task.setAsComplete();
        assertEquals("[T][x] read book", task.toString());
    }
}
