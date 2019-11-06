package model;

public class Course {

    public String code;
    public ToDoList myList = new ToDoList();

    public Course(String code) {
        this.code = code;
    }


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

    public void printCourses() {
        for (Course c : myList.courses) {
            System.out.println(c.code);
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        Course course = (Course) o;
//
//        return code.equals(course.code);
//    }
//
//    @Override
//    public int hashCode() {
//        return code.hashCode();
//    }
}
