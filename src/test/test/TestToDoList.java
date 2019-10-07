package test;

import model.GeneralTask;
import model.SchoolTask;
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
    Task myGeneralTask;
    Task mySchoolTask;

    @BeforeEach
    public void setup(){
        testToDoList = new ToDoList();
        myGeneralTask = new GeneralTask("","", "");
        mySchoolTask = new SchoolTask("","", "");
    }

    @Test
    public void testAddGeneralTask() {
        //check that task is not already there
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("run"));
        //insert task into list
        testToDoList.addGeneralTask("run", "exercise", "general", myGeneralTask);
        //check that task is in list once
        assertEquals(1, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("run"));
    }

    @Test
    public void testAddSchoolTask() throws FileNotFoundException, UnsupportedEncodingException {
        //check that task is not already there
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("hw"));
        //insert task into list
        testToDoList.addSchoolTask("hw", "cpsc", "school", mySchoolTask);
        //check that task is in list once
        assertEquals(1, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("hw"));
    }

//    @Test
//    public void testDuplicate() throws FileNotFoundException, UnsupportedEncodingException {
//        testToDoList.addSchoolTask("essay", "school","ENGL112", mySchoolTask);
//        testToDoList.addSchoolTask("essay", "school","ENGL112", mySchoolTask);
//
//        assertFalse(testToDoList.doesNotContainTask("essay"));
//        assertEquals(1, testToDoList.size());
//    }

    @Test
    public void testAddMultipleTasks() throws FileNotFoundException, UnsupportedEncodingException {
        Task essay = new SchoolTask("","", "");
        Task eat = new GeneralTask("","", "");
        Task exercise = new GeneralTask("","", "");

        assertEquals(0, testToDoList.size());
        testToDoList.addSchoolTask("essay", "school","ENGL112", essay);
        testToDoList.addGeneralTask("eat", "general","everday", eat);
        testToDoList.addGeneralTask("run", "general","exercise", exercise);

        assertEquals(3, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertFalse(testToDoList.doesNotContainTask("eat"));
        assertFalse(testToDoList.doesNotContainTask("run"));
    }


    @Test
    public void testRemoveTaskThere() throws FileNotFoundException, UnsupportedEncodingException {
        testToDoList.addGeneralTask("run", "exercise", "general", myGeneralTask);
        assertFalse(testToDoList.doesNotContainTask("run"));

        testToDoList.removeTask("run");

        assertTrue(testToDoList.doesNotContainTask("run"));
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
        testToDoList.addSchoolTask("hw", "cpsc", "school", mySchoolTask);
        assertFalse(testToDoList.doesNotContainTask("hw"));
        assertEquals(1, testToDoList.size());

        testToDoList.removeTask("work");
        assertEquals(1, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("hw"));
    }

    @Test
    public void testDoesNotContainTask() {
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("study"));
    }

//    @Test
//    public void testContainTask() throws FileNotFoundException, UnsupportedEncodingException {
//        testToDoList.addTask("study", myTask);
//        assertEquals(1, testToDoList.size());
//        assertFalse(testToDoList.doesNotContainTask("study"));
//    }

    @Test
    public void testPrintEmptyTest() {
        assertEquals(0, testToDoList.size());
//      TODO:how to do this?
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        ToDoList testSaveList = new ToDoList();

        Task essay = new SchoolTask("","", "");
        Task eat = new GeneralTask("","", "");
        Task exercise = new GeneralTask("","", "");
        testToDoList.addSchoolTask("essay", "school","ENGL112", essay);
        testToDoList.addGeneralTask("eat", "general","everday", eat);
        testToDoList.addGeneralTask("run", "general","exercise", exercise);

        testToDoList.save("testfile1");

        testSaveList.load("testfile1");

        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertFalse(testToDoList.doesNotContainTask("eat"));
        assertFalse(testToDoList.doesNotContainTask("run"));
    }

    @Test
    public void testLoad() throws IOException {
        testToDoList.load("testfile");
        assertFalse(testToDoList.doesNotContainTask("hw"));
        assertFalse(testToDoList.doesNotContainTask("run"));
    }

//    @Test
//    public void testLoadNull() throws IOException {
//        ToDoList testSaveList = new ToDoList();
//        List<String> lines = new ArrayList<>();
//
//
//        testSaveList.save("testfile2");
//
//        assertTrue(lines.contains("N/A"));
//
//    }
}
//

