package model;

public class SchoolTask extends Task {

    Course course;

    // EFFECTS: constructs a school task
    public SchoolTask(String name, Course course, Boolean state, String type) {
        super(name, state, type);
        this.course = course;
    }


    public String getCourseCode() {
        return course.code;
    }


    // EFFECTS: print out the task's information
    @Override
    public void printTask() {
        System.out.println("name: " + getName() + "; course: " + course.code + "; state: " + completeOrNot()
                + "; type: " + getType());
    }

    // EFFECTS: return the course code
    @Override
    public String toString() {
        return course.code;
    }
}
