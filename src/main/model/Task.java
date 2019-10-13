package model;


public abstract class Task {
    protected String category;
    protected String course;
    protected String name;
    protected String type;
    protected boolean state;

    public Task(String name, String type, Boolean state) {
        this.name = name;
        this.type = type;
        this.state = state;
    }

    // getters
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean getState() {
        return state;
    }

    // setters
    public void setType(String t) {
        this.type = t;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setStateTrue() {
        this.state = true;
    }

    public void setStateFalse() {
        this.state = false;
    }

    public abstract void printTask();

}
//
