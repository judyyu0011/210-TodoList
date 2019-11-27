package model;

import java.io.IOException;

public class Course {

    public String code;
    public ToDoList myList = new ToDoList();

    public Course(String code) throws IOException {
        this.code = code;
    }

    // EFFECTS: given the course code, return the corresponding course, return null if course cannot be found
    public Course returnCourseGivenCode(String courseCode) {
        for (Course c : myList.courses) {
            if (c.code.equals(courseCode)) {
                return c;
            }
        }
        return null;
    }


    public String getCourseCode() {
        return code;
    }

//    public ArrayList<Course> getCourses() {
//        return myList.courses;
//    }

//    public void printCourses() {
//        for (Course c : myList.courses) {
//            System.out.println(c.code);
//        }
//    }

}
