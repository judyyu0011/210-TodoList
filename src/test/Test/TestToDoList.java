package Test;

import model.Task;
import model.ToDoList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestToDoList {
    ToDoList testToDoList;
    Task myTask;
    private model.Task Task;

    @Before
    public void setup(){
        testToDoList = new ToDoList();
        myTask = new Task();
    }

    @Test
    public void testAddTaskNotThere() {
        //check that task is not already there
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("study"));
        //insert task into list
        testToDoList.addTask("study", myTask);
        //check that task is in list once
        assertEquals(1, testToDoList.size());
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
    public void testAddMultipleTasks() {
        Task study = new Task();
        Task work = new Task();
        Task exercise = new Task();

        assertEquals(0, testToDoList.size());
        testToDoList.addTask("study", study);
        testToDoList.addTask("work", work);
        testToDoList.addTask("exercise", exercise);

        assertEquals(3, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("study"));
        assertFalse(testToDoList.doesNotContainTask("work"));
        assertFalse(testToDoList.doesNotContainTask("exercise"));
    }


    @Test
    public void testRemoveTaskThere() {
        testToDoList.addTask("study", myTask);
        assertFalse(testToDoList.doesNotContainTask("study"));

        testToDoList.removeTask("study");

        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(0, testToDoList.size());
    }

    @Test
    public void testRemoveTaskNotThere() {
        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(0, testToDoList.size());

        testToDoList.removeTask("study");

        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(0, testToDoList.size());
    }

    @Test
    public void testRemoveWrongTask() {
        testToDoList.addTask("study", myTask);
        assertFalse(testToDoList.doesNotContainTask("study"));
        assertEquals(1, testToDoList.size());

        testToDoList.removeTask("work");
        assertEquals(1, testToDoList.size());
    }



    @Test
    public void testDoesNotContainTask() {
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testContainTask() {
        testToDoList.addTask("study", myTask);
        assertEquals(1, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testPrintEmptyTest() {
        assertEquals(0, testToDoList.size());
//      TODO:how to do this?
    }
}


