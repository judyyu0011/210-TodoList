package model;

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
        myGeneralTask = new GeneralTask("","", false, "");
        mySchoolTask = new SchoolTask("","", false, "");
    }

    @Test
    public void testAddGeneralTask() {
        //check that task is not already there
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("run"));
        //insert task into list
        testToDoList.addGeneralTask("run", "exercise", false, "general", myGeneralTask);
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
        testToDoList.addSchoolTask("hw", "cpsc", false, "school", mySchoolTask);
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
        Task essay = new SchoolTask("","", false,"");
        Task eat = new GeneralTask("","", false,"");
        Task exercise = new GeneralTask("","", false,"");

        assertEquals(0, testToDoList.size());
        testToDoList.addSchoolTask("essay", "school",false,"ENGL112", essay);
        testToDoList.addGeneralTask("eat", "general",false,"everday", eat);
        testToDoList.addGeneralTask("run", "general",false,"exercise", exercise);

        assertEquals(3, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertFalse(testToDoList.doesNotContainTask("eat"));
        assertFalse(testToDoList.doesNotContainTask("run"));
    }


    @Test
    public void testMarkComplete() {
        testToDoList.addGeneralTask("run", "exercise", false,"general", myGeneralTask);
        assertFalse(testToDoList.doesNotContainTask("run"));
        assertFalse(testToDoList.get(0).getState());

        testToDoList.markComplete("run");

        assertTrue(testToDoList.get(0).getState());
    }

    @Test
    public void testMarkCompleteNotThere() {
        assertTrue(testToDoList.doesNotContainTask("study"));
        assertEquals(0, testToDoList.size());

        testToDoList.markComplete("study");

        assertTrue(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testRemoveTaskThere() {
        testToDoList.addGeneralTask("run", "exercise", false,"general", myGeneralTask);
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
        testToDoList.addSchoolTask("hw", "cpsc", false,"school", mySchoolTask);
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

        Task essay = new SchoolTask("","", false,"");
        Task eat = new GeneralTask("","", false,"");
        Task exercise = new GeneralTask("","", false,"");
        testToDoList.addSchoolTask("essay", "ENGL112",false,"school", essay);
        testToDoList.addGeneralTask("eat", "everday",false,"general", eat);
        testToDoList.addGeneralTask("run", "exercise",false,"general", exercise);

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

