package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageTodoList {
    public ArrayList<Item> list;
    private Scanner scanner;

    public static void main(String[] args) {
        new ManageTodoList();
    }


    public ManageTodoList() {
        list = new ArrayList<Item>();
        scanner = new Scanner(System.in);
        String command = "";
        String taskName = "";
        String crossOff = "";

//        if (newList() == true) {
//            createList();
//        }

        System.out.println(
                "What would you like to do? [1] add a todo list item, "
                        + "[2] cross off a todo list item, [3] show all items");

        command = scanner.nextLine();
        if (command.equals("1")) {
            System.out.println("You entered '1'");
            System.out.println("Enter the task you would like to add");
            taskName = scanner.nextLine();
            System.out.println("You would like to add '" + taskName + "'");
            Item.addItem(taskName);

        } else if (command.equals("2")) {
            System.out.println("You entered '2'");
            printList();
            System.out.println("Which item would you like to cross off?");
            crossOff = scanner.nextLine();
            System.out.println("You would like to cross off '" + crossOff + "'");
            Item.crossOffItem(crossOff);

        } else if (command.equals("3")) {
            System.out.println("You entered '3'");
            printList();
        }
    }

    public void printList() {
        for (Item i : list) {
            System.out.println(i);
        }
    }

    //Makes a new list
//    public void createList() {
//        list = new ArrayList<Item>();
//    }

    //EFFECTS: ask user if want to construct a new list
    public static boolean newList() {
        return false; //stub
    }
}


