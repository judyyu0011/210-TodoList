package ui;

import model.Task;
import model.ToDoList;
import java.util.Scanner;

public class ManageToDoList {

    private Scanner scanner;
    private String option = "";
    ToDoList myList = new ToDoList();

    public ManageToDoList() {
        scanner = new Scanner(System.in);
        processOptions();
    }

    public void processOptions() {

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

    private void addTaskToList() {
        String taskName = "";
        Task newTask = new Task();

        System.out.println("Enter the task you would like to add");
        taskName = scanner.nextLine();
        System.out.println("You would like to add '" + taskName + "'");

        myList.addTask(taskName, newTask);
    }

    private void removeTaskFromList() {
        String removeTaskName = "";
        Task newTask = new Task();

        System.out.println("Enter the task you would like to remove");
        removeTaskName = scanner.nextLine();
        myList.removeTask(removeTaskName);
    }

    public static void main(String[] args) {
        new ManageToDoList();
    }

}
