package model;

public class Task {

    public String name;

    public Task() {
        name = "";
    }

    public String getName() {
        return name;
    }

    public void printTask() {
        System.out.println(getName());
    }
}
