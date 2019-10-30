package model;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import exceptions.TooManyTasksIncomplete;
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
        mySchoolTask = new SchoolTask("",null, false, "");
    }

    @Test
    public void testAddGeneralTask() {
        //check that task is not already there
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("run"));
        //insert task into list
        testToDoList.addGeneralTask("run", "exercise", false, "general");
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
        testToDoList.addSchoolTask("hw", "cpsc", false, "school");
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
        Task essay = new SchoolTask("",null, false,"");
        Task eat = new GeneralTask("","", false,"");
        Task exercise = new GeneralTask("","", false,"");

        assertEquals(0, testToDoList.size());
        testToDoList.addSchoolTask("essay", "ENGL112",false,"school");
        testToDoList.addGeneralTask("eat", "everday",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");

        assertEquals(3, testToDoList.size());
        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertFalse(testToDoList.doesNotContainTask("eat"));
        assertFalse(testToDoList.doesNotContainTask("run"));
    }


    @Test
    public void testMarkComplete() throws CannotAlterTask {
        testToDoList.addGeneralTask("run", "exercise", false,"general");
        assertFalse(testToDoList.doesNotContainTask("run"));
        assertFalse(testToDoList.get(0).getState());

        testToDoList.markComplete("run");

        assertTrue(testToDoList.get(0).getState());
    }

    @Test
    public void testMarkCompleteTaskAlreadyComplete() {

        myGeneralTask = new GeneralTask("run","exercise",true,"general");
        testToDoList.addGeneralTask("run", "exercise",true,"general");
        assertTrue(myGeneralTask.getState());
        try {
            testToDoList.markComplete("run");
            fail("Exception was not thrown");
        } catch (CannotAlterTask cannotAlterTask) {
            //expected
        }

    }

    @Test
    public void testMarkCompleteTaskNotComplete() {
        myGeneralTask = new GeneralTask("run","exercise",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");
        assertFalse(testToDoList.get(0).state);
        try {
            testToDoList.markComplete("run");
        } catch (CannotAlterTask cannotAlterTask) {
            fail("No exception should be thrown");
        }
        assertTrue(testToDoList.get(0).state);
    }

    @Test
    public void testMarkCompleteTaskNotThere() {
        myGeneralTask = new GeneralTask("run","exercise",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");
        try {
            testToDoList.markComplete("hello");
            fail("Exception was not thrown");
        } catch (CannotAlterTask cannotAlterTask) {
            //expected
        }
    }

//    @Test
//    public void testRemoveTaskThere() throws CannotFindTask {
//        testToDoList.addGeneralTask("run", "exercise", false,"general", myGeneralTask);
//        assertFalse(testToDoList.doesNotContainTask("run"));
//
//        testToDoList.removeTask("run");
//
//        assertTrue(testToDoList.doesNotContainTask("run"));
//        assertEquals(0, testToDoList.size());
//    }

    @Test
    public void testRemoveTaskNotThere() {
        try {
            testToDoList.removeTask("hello");
            fail("Exception was not thrown");
        } catch (CannotFindTask e) {
            //expected
        }
    }

    @Test
    public void testRemoveTaskThere() {
//        myGeneralTask = new GeneralTask("run","general",false,"exercise");
        testToDoList.addGeneralTask("run", "general",false,"exercise");
        try {
            testToDoList.removeTask("run");
        } catch (CannotFindTask e) {
            fail("No exception should be thrown");
        }
        assertTrue(testToDoList.doesNotContainTask("run"));
        assertEquals(0, testToDoList.size());

    }

//    @Test
//    public void testRemoveTaskNotThere() throws CannotFindTask {
//        assertTrue(testToDoList.doesNotContainTask("study"));
//        assertEquals(0, testToDoList.size());
//
//        testToDoList.removeTask("study");
//
//        assertTrue(testToDoList.doesNotContainTask("study"));
//        assertEquals(0, testToDoList.size());
//    }

//    @Test
//    public void testRemoveWrongTask() throws CannotFindTask {
//        testToDoList.addSchoolTask("hw", "cpsc", false,"school", mySchoolTask);
//        assertFalse(testToDoList.doesNotContainTask("hw"));
//        assertEquals(1, testToDoList.size());
//
//        testToDoList.removeTask("work");
//        assertEquals(1, testToDoList.size());
//        assertFalse(testToDoList.doesNotContainTask("hw"));
//    }

    @Test
    public void testDoesNotContainTask() {
        assertEquals(0, testToDoList.size());
        assertTrue(testToDoList.doesNotContainTask("study"));
    }

    @Test
    public void testTooManyTasksIncomplete() {
        buildFields();
        Task project = new SchoolTask("",null,false,"");
        testToDoList.addSchoolTask("project","110", false,"school");

        assertEquals(6, testToDoList.size());
        try {
            testToDoList.tooManyTasks();
            fail("Exception was not thrown");
        } catch (TooManyTasksIncomplete e) {
            e.printStackTrace();
            //expected
        }
    }

    @Test
    public void testNotTooManyTasksIncomplete() {
        buildFields();
        Task project = new SchoolTask("",null,true,"");
        testToDoList.addSchoolTask("project","110", true,"school");

        assertEquals(6, testToDoList.size());
        try {
            testToDoList.tooManyTasks();
        } catch (TooManyTasksIncomplete e) {
            fail("No exception should be thrown");
        }
    }

    public void buildFields() {
        Task essay = new SchoolTask("",null, false,"");
        Task eat = new GeneralTask("","", false,"");
        Task run = new GeneralTask("","", false,"");
        Task hw = new SchoolTask("",null,false,"");
        Task sleep = new GeneralTask("","", false,"");


        testToDoList.addSchoolTask("essay", "ENGL112",false,"school");
        testToDoList.addGeneralTask("eat", "everday",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");
        testToDoList.addSchoolTask("hw","210",false,"school");
        testToDoList.addGeneralTask("sleep","everday",false,"school");
    }

    @Test
    public void testPrintEmptyTest() {
        assertEquals(0, testToDoList.size());
//      TODO:how to do this?
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        ToDoList testSaveList = new ToDoList();

        Task essay = new SchoolTask("",null, false,"");
        Task eat = new GeneralTask("","", false,"");
        Task exercise = new GeneralTask("","", false,"");
        testToDoList.addSchoolTask("essay", "ENGL112",false,"school");
        testToDoList.addGeneralTask("eat", "everday",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");

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

