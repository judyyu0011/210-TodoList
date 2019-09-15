package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageTodoList {
    public ArrayList<Item> list;
    private Scanner scanner;

    public static void main(String[] args) {
        new ManageTodoList();
    }


    // EFFECTS: constructs a ToDoList that is empty

    public ManageTodoList() {
        scanner = new Scanner(System.in);
        String command = "";
        String taskName = "";
        String crossOff = "";

        if (newList() == true) {
            createList();
        }

        System.out.println(
                "What would you like to do? [1] add a todo list item, "
                        + "[2] cross off a todo list item, [3] show all items");

        command = scanner.nextLine();
        if (command.equals("1")) {
            System.out.println("Enter the task you would like to add");
            taskName = scanner.nextLine();
            Item.addItem(taskName);

        } else if (command.equals("2")) {
            for (Item i : list) {
                System.out.println(i);
            }
            System.out.println("Which item would you like to cross off?");
            crossOff = scanner.nextLine();
            Item.crossOffItem(crossOff);

        } else if (command.equals("3")) {
            for (Item i : list) {
                System.out.println(i);
            }
        }
    }

    //Makes a new list
    public void createList() {
        list = new ArrayList<Item>();
    }

    //EFFECTS: ask user if want to construct a new list
    public static boolean newList() {
        return false; //stub
    }

}

