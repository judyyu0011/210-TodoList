package model;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import exceptions.TooManyTasksIncomplete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class TestExceptions {
    Task newTask;
    ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList();
        newTask = new GeneralTask("","",false,"");

    }

    @Test
    public void testMarkCompleteTaskAlreadyComplete() {

        newTask = new GeneralTask("run","general",true,"exercise");
        testToDoList.addGeneralTask("run", "general",true,"exercise");
        assertTrue(newTask.getState());
        try {
            testToDoList.markComplete("run");
            fail("Exception was not thrown");
        } catch (CannotAlterTask cannotAlterTask) {
            //expected
        }

    }

    @Test
    public void testMarkCompleteTaskNotComplete() {
        newTask = new GeneralTask("run","general",false,"exercise");
        testToDoList.addGeneralTask("run", "general",false,"exercise");
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
        newTask = new GeneralTask("run","general",false,"exercise");
        testToDoList.addGeneralTask("run", "general",false,"exercise");
        try {
            testToDoList.markComplete("hello");
            fail("Exception was not thrown");
        } catch (CannotAlterTask cannotAlterTask) {
            //expected
        }
    }

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
        newTask = new GeneralTask("run","general",false,"exercise");
        testToDoList.addGeneralTask("run", "general",false,"exercise");
        try {
            testToDoList.removeTask("run");
        } catch (CannotFindTask e) {
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testTooManyTasksIncomplete() {
        buildFields();
        Task project = new SchoolTask("","",false,"");
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
        Task project = new SchoolTask("","",true,"");
        testToDoList.addSchoolTask("project","110", true,"school");

        assertEquals(6, testToDoList.size());
        try {
            testToDoList.tooManyTasks();
        } catch (TooManyTasksIncomplete e) {
            fail("No exception should be thrown");
        }
    }

    public void buildFields() {
        Task essay = new SchoolTask("","", false,"");
        Task eat = new GeneralTask("","", false,"");
        Task run = new GeneralTask("","", false,"");
        Task hw = new SchoolTask("","",false,"");
        Task sleep = new GeneralTask("","", false,"");


        testToDoList.addSchoolTask("essay", "ENGL112",false,"school");
        testToDoList.addGeneralTask("eat", "everday",false,"general");
        testToDoList.addGeneralTask("run", "exercise",false,"general");
        testToDoList.addSchoolTask("hw","210",false,"school");
        testToDoList.addGeneralTask("sleep","everday",false,"school");
    }

}
