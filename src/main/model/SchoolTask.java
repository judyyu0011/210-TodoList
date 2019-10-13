package model;

public class SchoolTask extends Task {

    public SchoolTask(String name, String course, Boolean state, String type) {
        super(name, state, type);
        this.course = course;
    }

    // getters
    public String getCourse() {
        return course;
    }


    // setters
//    public void setCourse(String c) {
//        this.course = c;
//    }


    // EFFECTS: print out the task's information
    @Override
    public void printTask() {
        System.out.println("name: " + getName() + "; course: " + getCourse() + "; state: " + completeOrNot()
                + "; type: " + getType());
    }


//    public void addTask(String name, String course, SchoolTask t) {
//        this.name = name;
//        this.course = course;
//    }

}
