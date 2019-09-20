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
            }
        }
    }

    public void printList() {
        if (taskList.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (int i = 9; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            t.printTask();
        }

    }
}

