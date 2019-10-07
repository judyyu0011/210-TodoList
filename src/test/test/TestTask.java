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

        assertEquals(testToDoList.size(), 1);
        assertEquals(myTask.getName(), "study");
    }
}
