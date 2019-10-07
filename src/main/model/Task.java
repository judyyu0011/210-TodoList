package model;


public abstract class Task {
    protected String category;
    protected String course;
    protected String name;
    protected String type;

    public Task(String name, String type) {
        this.name = name;
        this.type = type;

    }

    // getters
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    // setters
    public void setType(String t) {
        this.type = t;
    }

    public void setName(String n) {
        this.name = n;
    }

    public abstract void printTask();

}
//
