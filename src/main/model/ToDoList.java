package model;

import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void removeTask(String taskName) {
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                taskList.remove(t);
                System.out.println("'" + t + "' has been removed");
            } else {
                System.out.println("This task is not in your list");
            }
        }
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

