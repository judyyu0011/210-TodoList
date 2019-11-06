package model;


import exceptions.TaskAlreadyComplete;

public abstract class Task {
    protected String category;
    protected String name;
    protected String type;
    protected boolean state;

    protected ToDoList list;

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


//    public void setName(String n) {
//        this.name = n;
//    }

//    public void setStateFalse() {
//        this.state = false;
//    }


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
