package Test;

import model.Task;
import model.ToDoList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestToDoList {
    ToDoList testToDoList;
    Task myTask = new Task();
    private model.Task Task;

    @Before
    public void setup(){
        testToDoList = new ToDoList();
    }

    @Test
    public void testAddTaskNotThere() {
        //check that task is not already there
        assertEquals(testToDoList.size(), 0);
        assertTrue(testToDoList.doesNotContainTask("study"));
        //insert task into list
        testToDoList.addTask("study", myTask);
        //check that task is in list once
        assertEquals(testToDoList.size(), 1);
        assertFalse(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testDuplicate() {
        testToDoList.addTask("study", myTask);
        testToDoList.addTask("study", myTask);

        assertFalse(testToDoList.doesNotContainTask("study"));
        assertEquals(1, testToDoList.size());
    }

    @Test
    public void testRemoveTaskThere() {
        testToDoList.addTask("study", myTask);
        assertFalse(testToDoList.doesNotContainTask("study"));

        testToDoList.removeTask("study");

        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(testToDoList.size(),0);
    }

    @Test
    public void testRemoveTaskNotThere() {
        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(testToDoList.size(),0);

        testToDoList.removeTask("study");

        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(testToDoList.size(),0);
    }

    @Test
    public void testDoesNotContainTask() {
        assertEquals(testToDoList.size(), 0);
        assertTrue(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testContainTask() {
        testToDoList.addTask("study", myTask);
        assertEquals(testToDoList.size(), 1);
        assertFalse(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testPrintEmptyTest() {
        assertEquals(testToDoList.size(), 0);
//      TODO:how to do this?
    }
}


