package test;

import model.GeneralTask;
import model.SchoolTask;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {
    ToDoList testToDoList = new ToDoList();
    Task mySchoolTask = new SchoolTask("essay","ENGL112", "school");
    Task myGeneralTask = new GeneralTask("run","exercise", "general");

    @Test
    public void testGetName() {
        testToDoList.addSchoolTask("essay", "ENGL112", "school", mySchoolTask);
        assertFalse(testToDoList.doesNotContainTask("essay"));
        assertEquals("essay", mySchoolTask.getName()) ;
    }

//    @Test
//    public void testGetCategory() {
//        testToDoList.addGeneralTask("run","exercise", "general", myGeneralTask);
//        assertFalse(testToDoList.doesNotContainTask("run"));
//        assertEquals("run", myGeneralTask.getCategory());
    //
//    }
}
//
