package model;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import exceptions.TooManyTasksIncomplete;
import network.Weather;
import observer.Subject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ToDoList extends Subject implements Loadable, Saveable {

    public ArrayList<Task> tasks;
    public ArrayList<Course> courses;
    private Course course;
    private int maxincomplete = 5;

    // EFFECTS: constructs an empty todolist with tasks and courses
    public ToDoList() throws IOException {
        tasks = new ArrayList<>();
        courses = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: add a school task to the todolist
    public void addSchoolTask(String name, String courseCode, Boolean state, String type) throws IOException {
        Task t;
        course = new Course("");
        if (!courseExists(courseCode)) {
            Course course = new Course(courseCode);
            courses.add(course);
            t = new SchoolTask(name, course, state, type);
        } else {
            course = course.returnCourseGivenCode(courseCode);
            t = new SchoolTask(name, course, state, type);
        }
        t.setType("school");
        tasks.add(t);
        taskIsAdded(name);
    }

    // EFFECTS: return true if course can be found in list of courses
    public boolean courseExists(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: add a GeneralTask to the todolist
    public void addGeneralTask(String name, String category, Boolean state, String type) {
        Task t = new GeneralTask(name, category, state, type);
        t.setType("general");
        tasks.add(t);
        taskIsAdded(name);
    }

    // MODIFIES: this
    // EFFECTS: changes state of the task to true if it exists and its state is false,
    //          throw exception if task cannot be found
    public void markComplete(String taskName) throws CannotAlterTask {
        if (doesNotContainTask(taskName)) {
            throw new CannotFindTask();
        }
        for (Task t : tasks) {
            if (t.changeStateToTrue(taskName)) {
                break;
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: remove the task from the list, if the task is in the list
    //          throw exception if task cannot be found
    public void removeTask(String taskName) throws CannotFindTask {
        if (doesNotContainTask(taskName)) {
            throw new CannotFindTask();
        }
        for (Task t : tasks) {
            if (t.name.equals(taskName)) {
                tasks.remove(t);
                System.out.println("'" + t.name + "' has been removed");
                break;
            }
        }
    }

    // EFFECTS: return true if the list does not contain the task,
    //          otherwise return false
    public boolean doesNotContainTask(String taskName) {
        for (Task t : tasks) {
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
        if (tasks.size() == 0) {
            System.out.println("There is no task in the list");
        }
        for (Task t : tasks) {
            t.printTask();
        }
    }

    // EFFECTS: throw exception if incomplete tasks > maxincomplete
    public void tooManyTasks() throws TooManyTasksIncomplete {
        int numincomplete = 0;
        for (Task t : tasks) {
            if (!t.state) {
                numincomplete++;
            }
        }
        if (numincomplete > maxincomplete) {
            throw new TooManyTasksIncomplete();
        }
    }


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
        return tasks.size();
    }

    // EFFECTS: return the ith task in the list
    public Task get(int i) {
        return tasks.get(i);
    }

    // EFFECTS: load tasks from data file
    @Override
    public void load(String file) throws IOException {
        tasks = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(file));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);

            loadTaskToLine(partsOfLine);
        }
        printList();
    }

    private void loadTaskToLine(ArrayList<String> partsOfLine) throws IOException {
        course = new Course("");
        Course c;
        if (partsOfLine.get(3).equals("school")) {
            if (courseExists(partsOfLine.get(1))) {
                c = course.returnCourseGivenCode(partsOfLine.get(1));
                Task t = new SchoolTask("",c, false, "");
                declareNameStateType(partsOfLine, t);
            } else {
                c = new Course(partsOfLine.get(1));
                courses.add(c);
                Task t = new SchoolTask("",c, false, "");
                declareNameStateType(partsOfLine, t);
            }
        } else {
            Task t = new GeneralTask("","", false,"");
            t.category = partsOfLine.get(1);
            declareNameStateType(partsOfLine, t);
        }
    }

    private void declareNameStateType(ArrayList<String> partsOfLine, Task t) {
        t.name = partsOfLine.get(0);
        t.state = Boolean.parseBoolean(partsOfLine.get(2));
        t.type = partsOfLine.get(3);
        tasks.add(t);
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: save tasks to data file
    @Override
    public void save(String file) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        System.out.println("Your file contains:");
        for (Task t : tasks) {
            saveTask(lines, t);
        }
        write(lines, writer);
    }

    private void saveTask(List<String> lines, Task t) {
        if (t == null) {
            lines.add("N/A");
            System.out.println("N/A");
        } else {
            if (t.type.equals("school")) {
                lines.add(t.name + " " + t.toString() + " " + t.state + " " + t.type);
                System.out.println(t.name + " " + t.toString() + " " + t.state + " " + t.type);
            } else {
                lines.add(t.name + " " + t.category + " " + t.state + " " + t.type);
                System.out.println(t.name + " " + t.category + " " + t.state + " " + t.type);
            }
        }
    }

    private void write(List<String> lines, PrintWriter writer) {
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

}
