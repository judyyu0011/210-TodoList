package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDoList implements Loadable, Saveable {

    private ArrayList<Task> taskList;

    // EFFECTS: list is empty
    public ToDoList() {
        taskList = new ArrayList<>();
    }


    public void addSchoolTask(String name, String course, String type, Task t) {
        t = new SchoolTask(name, course, type);
        t.setType("school");
        taskList.add(t);
        taskIsAdded(name);
    }

    public void addGeneralTask(String name, String category, String type, Task t) {
        t = new GeneralTask(name, category, type);
        t.setType("general");
        taskList.add(t);
        taskIsAdded(name);
    }

    // MODIFIES: this
    // EFFECTS: remove the task from the list, if the task is in the list
    public void removeTask(String taskName) {
        if (doesNotContainTask(taskName)) {
            System.out.println("This task is not in your list");
        }
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                taskList.remove(t);
                System.out.println("'" + t.name + "' has been removed");
                break;
            }
        }
    }

    // EFFECTS: return true if the list does not contain the task,
    //          otherwise return false
    public boolean doesNotContainTask(String taskName) {
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: print the list if there are tasks in the list,
    //          otherwise say there is no task
    public void printList() {
        System.out.println("Your current ToDoList tasks are as followed:");
        if (taskList.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (Task t : taskList) {
            t.printTask();
        }
    }

//    @Override
//    public String toString() {
//        return "hi";
//    }

    // EFFECTS: prints that the task is added
    private void taskIsAdded(String taskName) {
        System.out.println("'" + taskName + "' has been added");
    }

    // EFFECTS: prints that the task cannot be added
    public void taskCannotBeAdded() {
        System.out.println("This task already exists, it cannot be added");
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return taskList.size();
    }

    @Override
    public void load() throws IOException {
        taskList = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(file));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);

            if (partsOfLine.get(2).equals("school")) {
                Task t = new SchoolTask("","", "");
                t.course = partsOfLine.get(1);
                declareNameAndType(partsOfLine, t);
            } else {
                Task t = new GeneralTask("","", "");
                t.category = partsOfLine.get(1);
                declareNameAndType(partsOfLine, t);
            }
        }
        printList();
    }

    private void declareNameAndType(ArrayList<String> partsOfLine, Task t) {
        t.name = partsOfLine.get(0);
        t.type = partsOfLine.get(2);
        taskList.add(t);
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter("TodoListData", "UTF-8");
        System.out.println("Your file contains:");
        for (Task t : taskList) {
            if (t == null) {
                lines.add("N/A");
                System.out.println("N/A");
            } else if (t != null) {
                if (t.type.equals("school")) {
                    lines.add(t.name + " " + t.course + " " + t.type);
                    System.out.println(t.name + " " + t.course + " " + t.type);
                } else {
                    lines.add(t.name + " " + t.category + " " + t.type);
                    System.out.println(t.name + " " + t.category + " " + t.type);
                }
            }
        }
        write(lines, writer);
    }

    private void write(List<String> lines, PrintWriter writer) {
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }
}


