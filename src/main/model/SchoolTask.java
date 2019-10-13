package model;

public class SchoolTask extends Task {

    public SchoolTask(String name, String course, Boolean state, String type) {
        super(name, type, state);
        this.course = course;
    }

    // getters
    public String getCourse() {
        return course;
    }


    // setters
    public void setCourse(String c) {
        this.course = c;
    }


    // EFFECTS: print out the task's information
    @Override
    public void printTask() {
        String state;

        if (getState()) {
            state = "completed";
        } else {
            state = "not completed";
        }

        System.out.println("name: " + getName() + "; course: " + getCourse() + "; state: " + state
                + "; type: " + getType());
    }


    public void addTask(String name, String course, SchoolTask t) {
        this.name = name;
        this.course = course;

    }

}
