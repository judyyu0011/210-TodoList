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


    // MODIFIES: this
    // EFFECTS: add a SchoolTask to the todolist
    public void addSchoolTask(String name, String course, Boolean state, String type, Task t) {
        t = new SchoolTask(name, course, state, type);
        t.setType("school");
        taskList.add(t);
        taskIsAdded(name);
    }

    // MODIFIES: this
    // EFFECTS: add a GeneralTask to the todolist
    public void addGeneralTask(String name, String category, Boolean state, String type, Task t) {
        t = new GeneralTask(name, category, state, type);
        t.setType("general");
        taskList.add(t);
        taskIsAdded(name);
    }

    public void markComplete(String taskName) {
        if (doesNotContainTask(taskName)) {
            System.out.println("This task is not in your list");
        }
        for (Task t : taskList) {
            if (t.name.equals(taskName)) {
                if (t.state) {
                    System.out.println("This task has already been marked completed");
                    break;
                }
                t.setStateTrue();
                System.out.println("'" + t.name + "' has been marked completed");
                break;
            }
        }
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

    public Task get(int i) {
        return taskList.get(i);
    }

    @Override
    public void load(String file) throws IOException {
        taskList = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(file));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);

            if (partsOfLine.get(2).equals("school")) {
                Task t = new SchoolTask("","", false, "");
                t.course = partsOfLine.get(1);
                declareNameStateType(partsOfLine, t);
            } else {
                Task t = new GeneralTask("","", false,"");
                t.category = partsOfLine.get(1);
                declareNameStateType(partsOfLine, t);
            }
        }
        printList();
    }

    private void declareNameStateType(ArrayList<String> partsOfLine, Task t) {
        t.name = partsOfLine.get(0);
        t.state = Boolean.parseBoolean(partsOfLine.get(2));
        t.type = partsOfLine.get(3);
        taskList.add(t);
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    @Override
    public void save(String file) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        System.out.println("Your file contains:");
        for (Task t : taskList) {
            if (t == null) {
                lines.add("N/A");
                System.out.println("N/A");
            } else if (t != null) {
                if (t.type.equals("school")) {
                    lines.add(t.name + " " + t.course + " " + t.state + " " + t.type);
                    System.out.println(t.name + " " + t.course + " " + t.state + " " + t.type);
                } else {
                    lines.add(t.name + " " + t.category + " " + t.state + " " + t.type);
                    System.out.println(t.name + " " + t.category + " " + t.state + " " + t.type);
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
