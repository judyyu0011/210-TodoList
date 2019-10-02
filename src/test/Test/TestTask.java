package Test;

import model.Task;
import model.ToDoList;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestTask {
    ToDoList testToDoList = new ToDoList();
    Task myTask = new Task();

    @Test
    public void testGetName() throws IOException {
        testToDoList.addTask("study", myTask);
        assertFalse(testToDoList.doesNotContainTask("study"));

        assertEquals(testToDoList.size(), 1);
        assertEquals(myTask.getName(), "study");
    }
}
