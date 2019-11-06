package model;

import java.util.ArrayList;

public class Course {

    public String code;
    private Task myTask;
    public ArrayList<Course> courses = new ArrayList<>();

    public Course(String code) {
        this.code = code;
    }

    public boolean courseExists(String courseCode) {
        for (Course c : courses) {
            if (c.code.equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public Course returnCourseGivenCode(String courseCode) {
        for (Course c : courses) {
            if (c.code.equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public String getCourseCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course course = (Course) o;

        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
