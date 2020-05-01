package ui;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import exceptions.TooManyTasksIncomplete;
import model.Course;
import model.GeneralTask;
import model.SchoolTask;
import model.ToDoList;
import network.Weather;
import observer.Subject;

import java.io.IOException;
import java.util.Scanner;

public class ManageToDoList extends Subject {

    private Scanner scanner;
    private String option = "";
    private String name;
    private Course course = new Course("");
    private Weather weather = new Weather();

    private ToDoList myList = new ToDoList();
    GeneralTask newGeneralTask = new GeneralTask("","", false,"");
    SchoolTask newSchoolTask = new SchoolTask("",null, false,"");


    public ManageToDoList() throws IOException {
        scanner = new Scanner(System.in);
        myList.load("TodoListData");
        run();
    }

    // MODIFIES: this
    // EFFECTS: prompt user for input, then add task, remove task, print list,
    //          or quit given user input
    private void run() throws IOException {
        while (true) {
            addObserver(weather);
            giveUserOptions();

            if (option.equals("1")) {
                optionAdd();

            } else if (option.equals("2")) {
                optionMarkComplete();

            } else if (option.equals("3")) {
                optionRemove();

            } else if (option.equals("4")) {
                optionPrint();

            } else if (option.equals("5")) {
                notifyObservers(myList);
                myList.save("TodoListData");
                break;

            } else {
                System.out.println("This is not a valid option");
            }
        }
    }


    private void giveUserOptions() {
        System.out.println(" ");
        System.out.println("What would you like to do? ");
        System.out.println("[1] add a task, ");
        System.out.println("[2] mark task as completed, ");
        System.out.println("[3] remove a task, ");
        System.out.println("[4] show all tasks, ");
        System.out.println("[5] quit");
        option = scanner.nextLine();
        System.out.println("You selected " + option);
    }

    private void optionAdd() {

        System.out.println("Enter the task you would like to add");
        name = scanner.nextLine();
        System.out.println("You would like to add '" + name + "'");

        if (myList.doesNotContainTask(name)) {
            try {
                addTask();
            } catch (TooManyTasksIncomplete | IOException e) {
                e.printStackTrace();
                System.out.println("Add unsuccessful, too many incomplete tasks");
            }
        } else {
            myList.taskCannotBeAdded();
        }
    }

    private void addTask() throws TooManyTasksIncomplete, IOException {
        String course;
        String category;
        String type = "";

        myList.tooManyTasks();

        System.out.println("what type of task is this? [school] or [general]");
        option = scanner.nextLine();
        if (option.equals("school")) {
            System.out.println("What course is this task for?");
            course = scanner.nextLine();
            myList.addSchoolTask(name, course, false, type);
        } else if (option.equals("general")) {
            System.out.println("What category is this task in?");
            category = scanner.nextLine();
            myList.addGeneralTask(name, category, false, type);
        } else {
            System.out.println("This is not a valid option");
        }
    }


    private void optionMarkComplete() {
        myList.printList();

        System.out.println("Enter the task you would like to mark complete");
        name = scanner.nextLine();
        System.out.println("You would like to mark '" + name + "' as complete");
        try {
            myList.markComplete(name);
        } catch (CannotAlterTask e) {
            e.printStackTrace();
            System.out.println("This task does not exist or is already complete");
        } finally {
            System.out.println("Continuing...");
        }
    }


    private void optionRemove() {
        myList.printList();

        System.out.println("Enter the task   would like to remove");
        name = scanner.nextLine();
        try {
            myList.removeTask(name);
        } catch (CannotFindTask e) {
            e.printStackTrace();
            System.out.println("This task is not in your list");
        } finally {
            System.out.println("Continuing...");
        }
    }

    private void optionPrint() {
        myList.printList();
    }

}

