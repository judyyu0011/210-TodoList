//package ui;
//
//import java.util.Scanner;
//
//public class DraftItem {
//    private DraftManageToDoList m1;
//    private String task;
//    private int importance;
//    private boolean status;
//    private DraftItem draftItem;
//    private static Scanner scanner;
//
//
//    public DraftItem() {
//        m1 = new DraftManageToDoList();
//        task = "";
//        importance = 0;
//        status = false;
//        scanner = new Scanner(System.in);
//    }
//
//    public void addItem(String taskName) {
//        DraftItem draftItem = new DraftItem();
//        draftItem.task = taskName;
//
//        m1.addToList(draftItem);
//        System.out.println("'" + taskName + "' is added");
//    }
//
//    public void crossOffItem(String task) {
//
//        //TODO: does list contain this item?
//        (m1.findByTask(task)).status = false;
////        myList.get(myList.indexOf(task)).status = false;
//
//        System.out.println("Would you like to remove this task?");
//        String reply = scanner.nextLine();
//
//        if (reply.equals("yes")) {
//            m1.removeFromList(draftItem);
//        }
//        System.out.println("'" + task + "' is crossed off");
//    }
//
//    // EFFECTS: search myList for task
//    public static void searchItem() {
//        String name = "";
//
//        System.out.println("Enter the task you are looking for");
//        name = scanner.nextLine();
//
////        if (list.contains(name)) {
////            System.out.println("Found task");
////        } else {
////            System.out.println("Cannot find task");
////        }
//    }
//
//
//    public String getTask() {
//        return task;
//    }
//
//    public boolean getStatus() {
//        return status;
//    }
//
//    public void printItem(DraftItem i) {
//        System.out.println(i.getTask());
//        System.out.println("status (true = active, false = crossed off): " + i.getStatus());
//
//    }
//
//    public void importance(){
//
//    }
//}
