package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {
    Course course = new Course("ENGL112");
    Task mySchoolTask = new SchoolTask("essay",course, false,"school");
    Task myGeneralTask = new GeneralTask("run","exercise", false, "general");

    public TestTask() throws IOException {
    }

    @Test
    public void testGetName() {
        assertEquals("essay", mySchoolTask.getName()) ;
    }

    @Test
    public void testGetState() {
        assertFalse(mySchoolTask.getState());
    }

    @Test
    public void testGetSchoolType() {
        assertEquals("school",mySchoolTask.getType());
    }

    @Test
    public void testGetGeneralType() {
        assertEquals("general",myGeneralTask.getType());
    }


    @Test
    public void testGetCategory() {
        GeneralTask gTask = new GeneralTask("run","exercise", false, "general");
        assertEquals("exercise", gTask.getCategory());
    }

    @Test
    public void testGetCourse() {
        SchoolTask sTask = new SchoolTask("essay",course, false,"school");
        assertEquals("ENGL112", sTask.getCourseCode());
    }

    @Test
    public void testSetStateTrue() {
        mySchoolTask.setStateTrue();
        assertTrue(mySchoolTask.state);
    }

    @Test
    public void testSetType() {
        mySchoolTask.setType("testtype");
        assertEquals("testtype", mySchoolTask.getType());
    }

    @Test
    public void testNotComplete() {
        assertEquals("not completed", myGeneralTask.completeOrNot());
    }

    @Test
    public void testComplete() {
        mySchoolTask.setStateTrue();
        assertEquals("completed", mySchoolTask.completeOrNot());
    }

}
