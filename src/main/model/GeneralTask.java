package model;

public class GeneralTask extends Task {

    public GeneralTask(String name, String category, String type) {
        super(name, type);
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
        System.out.println("name: " + getName() + "; category: " + getCategory() + "; type: " + getType());
    }

}
