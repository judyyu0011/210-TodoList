package test;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class TestToDoList {
    ToDoList testToDoList;
    Task myTask;

    @BeforeEach
    public void setup(){
        testToDoList = new ToDoList();
        myTask = new Task();
    }

    @Test
    public void testAddTaskNotThere() throws FileNotFoundException, UnsupportedEncodingException {
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
    public void testDuplicate() throws FileNotFoundException, UnsupportedEncodingException {
        testToDoList.addTask("study", myTask);
        testToDoList.addTask("study", myTask);

        assertFalse(testToDoList.doesNotContainTask("study"));
        assertEquals(1, testToDoList.size());
    }

    @Test
    public void testAddMultipleTasks() throws FileNotFoundException, UnsupportedEncodingException {
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
    public void testRemoveTaskThere() throws FileNotFoundException, UnsupportedEncodingException {
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
    public void testRemoveWrongTask() throws FileNotFoundException, UnsupportedEncodingException {
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
    public void testContainTask() throws FileNotFoundException, UnsupportedEncodingException {
        testToDoList.addTask("study", myTask);
        assertEquals(1, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testPrintEmptyTest() {
        assertEquals(0, testToDoList.size());
//      TODO:how to do this?
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        ToDoList testSaveList = new ToDoList();

        Task study = new Task();
        Task hw = new Task();
        Task work = new Task();
        testToDoList.addTask("study", study);
        testToDoList.addTask("hw", hw);
        testToDoList.addTask("work", work);

        testToDoList.save("testfile1");

        testSaveList.load("testfile1");

        assertFalse(testToDoList.doesNotContainTask("study"));
        assertFalse(testToDoList.doesNotContainTask("hw"));
        assertFalse(testToDoList.doesNotContainTask("work"));
    }

    @Test
    public void testLoad() throws IOException {
        testToDoList.load("testfile");
        assertFalse(testToDoList.doesNotContainTask("judy"));
        assertFalse(testToDoList.doesNotContainTask("yu"));
    }
}


