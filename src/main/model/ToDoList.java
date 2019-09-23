package model;

import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add task to ToDoList, if task is not already in list
    public void addTask(String taskName, Task t) {
        if (doesNotContainTask(taskName)) {
            t.name = taskName;
            taskList.add(t);
            System.out.println("'" + taskName + "' has been added");
        } else {
            System.out.println("This task already exists, it cannot be added");
        }
    }


    // MODIFIES: this
    // EFFECTS: remove task from List, if task is in list
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

    // EFFECTS: return true if the list does not contain the task, otherwise return false
    private boolean doesNotContainTask(String taskName) {
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                return false;
            }
        }
        return true;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: print list if there are tasks in list, otherwise say there is no task
    public void printList() {
        System.out.println("Your current ToDoList tasks are as followed:");
        if (taskList.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (Task t : taskList) {
            t.printTask();
        }

    }
}

