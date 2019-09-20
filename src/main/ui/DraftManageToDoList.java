//package ui;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class DraftManageToDoList {
//    public DraftItem i1;
//    public ArrayList<DraftItem> myList;
//    private Scanner scanner;
//
//    public static void main(String[] args) {
//        new DraftManageToDoList();
//    }
//
//    public DraftManageToDoList() {
//        i1 = new DraftItem();
//        myList = new ArrayList<DraftItem>();
//        scanner = new Scanner(System.in);
//        String command = "";
//        String taskName = "";
//        String crossOff = "";
//
////        if (newList() == true) {
////            createList();
////        }
//
//        System.out.println(
//                "What would you like to do? [1] add a todo list item, "
//                        + "[2] cross off a todo list item, [3] show all items");
//
//        command = scanner.nextLine();
//        if (command.equals("1")) {
//            System.out.println("You entered '1'");
//            System.out.println("Enter the task you would like to add");
//            taskName = scanner.nextLine();
//            System.out.println("You would like to add '" + taskName + "'");
//            i1.addItem(taskName);
//
//        } else if (command.equals("2")) {
//            System.out.println("You entered '2'");
//            printList();
//            System.out.println("Which item would you like to cross off?");
//            crossOff = scanner.nextLine();
//            System.out.println("You would like to cross off '" + crossOff + "'");
//            i1.crossOffItem(crossOff);
//
//        } else if (command.equals("3")) {
//            System.out.println("You entered '3'");
//            printList();
//        }
//    }
//
//    public void addToList(DraftItem i) {
//        myList.add(i);
//    }
//
//
//    public void removeFromList(DraftItem i) {
//        myList.remove(i);
//    }
//
//
//    public DraftItem findByTask(String s) {
//        for (int i = 0; i < myList.size(); i++) {
//            if (s == i1.getTask()) {
//                break;
//            }
//        }
//        return i1;
//    }
//
//    //TODO: fix this method - true if it finds string = task, false if it does not
//    public boolean containInList(String s) {
//        for (int i = 0; i < myList.size(); i++) {
//            if (s == i1.getTask()) {
//                break;
//            }
//        }
//        return true;
//    }
////            Item = Array.index(0);
//
//
//    public void printList() {
//        for (DraftItem i : myList) {
//            i.printItem(i1);
//        }
//    }
//
//    //Makes a new list
////    public void createList() {
////        list = new ArrayList<Item>();
////    }
//
//    //EFFECTS: ask user if want to construct a new list
//    public static boolean newList() {
//        return false; //stub
//    }
//}
//
//
