package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {
    ToDoList testToDoList = new ToDoList();
    Task mySchoolTask = new SchoolTask("essay","ENGL112", false,"school");
    Task myGeneralTask = new GeneralTask("run","exercise", false, "general");

    @Test
    public void testGetName() {
        testToDoList.addSchoolTask("essay", "ENGL112", false, "school", mySchoolTask);
        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertEquals("essay", mySchoolTask.getName()) ;
    }


//    @Test
//    public void testGetCategory() {
//        testToDoList.addGeneralTask("run","exercise", "general", myGeneralTask);
//        assertFalse(testToDoList.doesNotContainTask("run"));
//        assertEquals("run", myGeneralTask.getCategory());
//    }
}
