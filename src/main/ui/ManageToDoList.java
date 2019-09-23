package ui;

import model.Task;
import model.ToDoList;
import java.util.Scanner;

public class ManageToDoList {

    private Scanner scanner;
    private String option = "";
    private ToDoList myList = new ToDoList();

    public ManageToDoList() {
        scanner = new Scanner(System.in);
        processOptions();
    }

    // MODIFIES: this
    // EFFECTS: prompt user for input, then add task, remove task, print list,
    //          or quit given user input
    private void processOptions() {

        while (true) {
            System.out.println("What would you like to do? [1] add a task, "
                            + "[2] remove a task, [3] show all tasks, [4] quit");
            option = scanner.nextLine();
            System.out.println("You selected " + option);

            if (option.equals("1")) {
                addTaskToList();

            } else if (option.equals("2")) {
                myList.printList();
                removeTaskFromList();

            } else if (option.equals("3")) {
                myList.printList();

            } else if (option.equals("4")) {
                break;

            } else {
                System.out.println("This is not a valid option");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: scan for user input, then add task to the list
    private void addTaskToList() {
        String taskName;
        Task newTask = new Task();

        System.out.println("Enter the task you would like to add");
        taskName = scanner.nextLine();
        System.out.println("You would like to add '" + taskName + "'");

        myList.addTask(taskName, newTask);
    }

    // MODIFIES: this
    // EFFECTS: scan for user input, then remove task from the list
    private void removeTaskFromList() {
        String removeTaskName;

        System.out.println("Enter the task you would like to remove");
        removeTaskName = scanner.nextLine();
        myList.removeTask(removeTaskName);
    }

}
