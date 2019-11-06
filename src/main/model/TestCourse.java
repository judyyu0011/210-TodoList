package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {

    private Course course;
    private ArrayList<Course> courses;

    @BeforeEach
    public void setup() {
        course = new Course("210");
        courses = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertEquals("210", course.getCourseCode());
    }



}
