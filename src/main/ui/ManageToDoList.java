package ui;

import model.GeneralTask;
import model.SchoolTask;
import model.Task;
import model.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ManageToDoList {

    private Scanner scanner;
    private String option = "";
    String name;

    private ToDoList myList = new ToDoList();
    Task newGeneralTask = new GeneralTask("","", "");
    Task newSchoolTask = new SchoolTask("","", "");


    public ManageToDoList() throws IOException {
        scanner = new Scanner(System.in);
        myList.load("TodoListData");
        run();
    }

    // MODIFIES: this
    // EFFECTS: prompt user for input, then add task, remove task, print list,
    //          or quit given user inpt
    public void run() throws IOException {
        while (true) {
            giveUserOptions();
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
                myList.save("TodoListData");
                break;

            } else {
                System.out.println("This is not a valid option");
            }
        }
    }

    private void giveUserOptions() {
        System.out.println("What would you like to do? [1] add a task, "
                        + "[2] remove a task, [3] show all tasks, [4] quit");
    }

    // MODIFIES: this
    // EFFECTS: scan for user input, then add task to the list
    public void addTaskToList() {

        System.out.println("Enter the task you would like to add");
        name = scanner.nextLine();
        System.out.println("You would like to add '" + name + "'");

        if (myList.doesNotContainTask(name)) {
            addTask();
        } else {
            myList.taskCannotBeAdded();
        }
    }

    public void addTask() {
        String course;
        String category;
        String type = "";

        System.out.println("Is this a school task? yes or no");
        option = scanner.nextLine();
        if (option.equals("yes")) {
            System.out.println("What course is this task for?");
            course = scanner.nextLine();
            myList.addSchoolTask(name, course, type, newSchoolTask);
        } else if (option.equals("no")) {
            System.out.println("What category is this task in?");
            category = scanner.nextLine();
            myList.addGeneralTask(name, category, type, newGeneralTask);
        } else {
            System.out.println("This is not a valid option");
        }
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
//
