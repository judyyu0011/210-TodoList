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

    // setters
    public void setName(String n) {
        this.name = n;
    }

    // EFFECTS: print out the task's information
    public void printTask() {
        System.out.println(getName());
    }

//TODO: add number paramter to task

}
