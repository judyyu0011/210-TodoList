package model;


public abstract class Task {
    protected String category;
    protected String course;
    protected String name;
    protected String type;
    protected boolean state;

    public Task(String name, Boolean state, String type) {
        this.name = name;
        this.state = state;
        this.type = type;
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

//    public void setName(String n) {
//        this.name = n;
//    }

    public void setStateTrue() {
        this.state = true;
    }

//    public void setStateFalse() {
//        this.state = false;
//    }

    public String completeOrNot() {
        if (getState()) {
            return "completed";
        } else {
            return "not completed";
        }
    }

    public abstract void printTask();

}
//
