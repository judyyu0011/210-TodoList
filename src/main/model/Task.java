package model;

public class Task {

    String name;

    public Task() {
        name = "";
    }

    // getters
    public String getName() {
        return name;
    }

    // EFFECTS: print out the task's information
    public void printTask() {
        System.out.println(getName());
    }
}
