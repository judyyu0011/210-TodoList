package model;

import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Task> taskList;

    // EFFECTS: list is empty
    public ToDoList() {
        taskList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add task to ToDoList, if task is not already in list
    public void addTask(String taskName, Task t) {
        if (doesNotContainTask(taskName)) {
            t.name = taskName;
            taskList.add(t);
            taskIsAdded(taskName);
        } else {
            taskCannotBeAdded();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the task from the list, if the task is in the list
    public void removeTask(String taskName) {
        if (doesNotContainTask(taskName)) {
            System.out.println("This task is not in your list");
        }
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                taskList.remove(t);
                System.out.println("'" + t.name + "' has been removed");
                break;
            }
        }
    }

    // EFFECTS: return true if the list does not contain the task,
    //          otherwise return false
    public boolean doesNotContainTask(String taskName) {
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: print the list if there are tasks in the list,
    //          otherwise say there is no task
    public void printList() {
        System.out.println("Your current ToDoList tasks are as followed:");
        if (taskList.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (Task t : taskList) {
            t.printTask();
        }
    }

    // EFFECTS: prints that the task is added
    private void taskIsAdded(String taskName) {
        System.out.println("'" + taskName + "' has been added");
    }

    // EFFECTS: prints that the task cannot be added
    private void taskCannotBeAdded() {
        System.out.println("This task already exists, it cannot be added");
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return taskList.size();
    }
}

