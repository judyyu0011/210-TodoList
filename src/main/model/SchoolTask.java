package model;

public class SchoolTask extends Task {

    Course course;

    public SchoolTask(String name, Course course, Boolean state, String type) {
        super(name, state, type);
        this.course = course;
    }

    // getters
    public String getCourseCode() {
        return course.code;
    }



    // setters
//    public void setCourse(String c) {
//        this.course = c;
//    }



    // EFFECTS: print out the task's information
    @Override
    public void printTask() {
        System.out.println("name: " + getName() + "; course: " + course.code + "; state: " + completeOrNot()
                + "; type: " + getType());
    }

    @Override
    public String toString() {
        return course.code;
    }



//    public void addTask(String name, String course, SchoolTask t) {
//        this.name = name;
//        this.course = course;
//    }

}
