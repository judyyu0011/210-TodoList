package model;

import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String s, Task t) {
        if (!containsTask(s)) {
            t.name = s;
            taskList.add(t);
            System.out.println("'" + s + "' has been added");
        } else {
            System.out.println("This task already exists");
        }
    }

    public void removeTask(String taskName) {
        if (!containsTask(taskName)) {
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

    public boolean containsTask(String taskName) {
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    public void printList() {
        System.out.println("Your current ToDoList tasks are as followed:");
        if (taskList.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            t.printTask();
        }

    }
}

