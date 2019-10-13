package model;

public class GeneralTask extends Task {

    public GeneralTask(String name, String category,  Boolean state, String type) {
        super(name, type, state);
        this.category = category;
    }

    // getters
    public String getCategory() {
        return category;
    }

    // setters
    public void setCategory(String c) {
        this.category = c;
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

        System.out.println("name: " + getName() + "; category: " + getCategory() + "; state: " + state
                + "; type: " + getType());
    }

}
