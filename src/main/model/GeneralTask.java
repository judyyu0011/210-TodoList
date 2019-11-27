package model;

public class GeneralTask extends Task {

    // EFFECTS: constructs a general task
    public GeneralTask(String name, String category,  Boolean state, String type) {
        super(name, state, type);
        this.category = category;
    }


    public String getCategory() {
        return category;
    }


    // EFFECTS: print out the task's information
    @Override
    public void printTask() {
        System.out.println("name: " + getName() + "; category: " + getCategory() + "; state: " + completeOrNot()
                + "; type: " + getType());
    }

}
