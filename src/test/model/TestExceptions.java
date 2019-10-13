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
        testToDoList.addGeneralTask("run", "general",true,"exercise", newTask);
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
        testToDoList.addGeneralTask("run", "general",false,"exercise", newTask);
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
        testToDoList.addGeneralTask("run", "general",false,"exercise", newTask);
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
        testToDoList.addGeneralTask("run", "general",false,"exercise", newTask);
        try {
            testToDoList.removeTask("run");
        } catch (CannotFindTask e) {
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testTooManyTasksIncomplete() {
        Task essay = new SchoolTask("","", false,"");
        Task eat = new GeneralTask("","", false,"");
        Task run = new GeneralTask("","", false,"");
        Task hw = new SchoolTask("","",false,"");
        Task sleep = new GeneralTask("","", false,"");
        Task project = new SchoolTask("","",false,"");

        testToDoList.addSchoolTask("essay", "ENGL112",false,"school", essay);
        testToDoList.addGeneralTask("eat", "everday",false,"general", eat);
        testToDoList.addGeneralTask("run", "exercise",false,"general", run);
        testToDoList.addSchoolTask("hw","210",false,"school",hw);
        testToDoList.addGeneralTask("sleep","everday",false,"school",sleep);
        testToDoList.addSchoolTask("project","110", false,"school",project);

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
        Task essay = new SchoolTask("","", false,"");
        Task eat = new GeneralTask("","", false,"");
        Task run = new GeneralTask("","", false,"");
        Task hw = new SchoolTask("","",false,"");
        Task sleep = new GeneralTask("","", false,"");
        Task project = new SchoolTask("","",true,"");

        testToDoList.addSchoolTask("essay", "ENGL112",false,"school", essay);
        testToDoList.addGeneralTask("eat", "everday",false,"general", eat);
        testToDoList.addGeneralTask("run", "exercise",false,"general", run);
        testToDoList.addSchoolTask("hw","210",false,"school",hw);
        testToDoList.addGeneralTask("sleep","everday",false,"school",sleep);
        testToDoList.addSchoolTask("project","110", true,"school",project);

        assertEquals(6, testToDoList.size());
        try {
            testToDoList.tooManyTasks();
        } catch (TooManyTasksIncomplete e) {
            fail("No exception should be thrown");
        }
    }

}
