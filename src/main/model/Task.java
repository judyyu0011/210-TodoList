package model;


import exceptions.TaskAlreadyComplete;

public abstract class Task {
    protected String category;
    protected String name;
    protected String type;
    protected boolean state;

    protected ToDoList list;

    // EFFECTS: constructs a task with name, state and type
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

    public void setStateTrue() {
        this.state = true;
    }


    // MODIFIES: this
    // EFFECTS: set state true if state is false, and return true; throws exception if state is already true
    public boolean changeStateToTrue(String taskName) throws TaskAlreadyComplete {
        if (name.equals(taskName)) {
            if (state) {
                throw new TaskAlreadyComplete();
            }
            setStateTrue();
            System.out.println("'" + name + "' has been marked completed");
            return true;
        }
        return false;
    }

    // REQUIRES: state is not null
    // EFFECTS: return appropriate string given state
    public String completeOrNot() {
        if (getState()) {
            return "completed";
        } else {
            return "not completed";
        }
    }

    // EFFECTS: prints a task
    public abstract void printTask();

}

