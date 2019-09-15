package ui;

import java.util.ArrayList;
import java.util.Scanner;
import ui.ManageTodoList;

public class Item {
    private String task;
    private int importance;
    private boolean status;
    private static Scanner scanner;

    public Item() {
        task = "";
        importance = 0;
        status = false;
        scanner = new Scanner(System.in);
    }

    public static void addItem(String taskName) {
        Item item = new Item();
        item.task = taskName;
        list.add(item);
    }

    public static void crossOffItem(String task) {
        list.get(list.indexOf(task)).status = false;
    }

    // EFFECTS: search myList for task
    public static void searchItem() {
        String name = "";

        System.out.println("Enter the task you are looking for");
        name = scanner.nextLine();

        if (list.contains(name)) {
            System.out.println("Found task");
        } else {
            System.out.println("Cannot find task");
        }
    }

    public void importance(){

    }
}
