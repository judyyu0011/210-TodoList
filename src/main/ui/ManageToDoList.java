package ui;

import model.Task;
import model.ToDoList;
import java.util.Scanner;

public class ManageToDoList {

    private Scanner scanner;
    ToDoList myList = new ToDoList();

    public ManageToDoList() {
        scanner = new Scanner(System.in);
        processOptions();
    }

    public void processOptions() {
        String option = "";

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
            }
        }
    }

    private void addTaskToList() {
        String taskName = "";

        Task newTask = new Task();
        System.out.println("Enter the task you would like to add");
        taskName = scanner.nextLine();
        System.out.println("You would like to add '" + taskName + "'");
        newTask.name = taskName;
        myList.addTask(newTask);
        System.out.println("'" + taskName + "' has been added");
    }

    private void removeTaskFromList() {
        String removeTaskName = "";

        System.out.println("Enter the task you would like to remove");
        removeTaskName = scanner.nextLine();
        myList.removeTask(removeTaskName);
    }

    public static void main(String[] args) {
        new ManageToDoList();
    }

}
