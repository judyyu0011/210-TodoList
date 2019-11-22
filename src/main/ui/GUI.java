package ui;

import com.sun.tools.javac.comp.Flow;
import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import model.Task;
import model.ToDoList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    private JLabel label;
    private ToDoList list;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;


    public GUI() throws IOException {
        super("ToDoList UI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        label = new JLabel("Welcome to your todo list! What would you like to do?");

        ImageIcon icon = new ImageIcon("test.jpg");
        JLabel pic = new JLabel();
        pic.setIcon(icon);
        add(pic);

//        BufferedImage myPicture = ImageIO.read(new File("butterfly.jpg"));
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        add(picLabel);

        add(label, BorderLayout.NORTH);

        list = new ToDoList();

        this.getContentPane().setBackground(new Color(194,210,244));

        addButtons();
        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    private void addButtons() {
        JButton addBtn = new JButton("Add");
        addBtn.setActionCommand("add");

        JButton completeBtn = new JButton("Mark complete");
        completeBtn.setActionCommand("mark complete");

        JButton removeBtn = new JButton("Remove");
        removeBtn.setActionCommand("remove");

        JButton printBtn = new JButton("See list");
        printBtn.setActionCommand("print");


        addBtn.addActionListener(this);
        completeBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        printBtn.addActionListener(this);

        add(addBtn, BorderLayout.NORTH);
        add(completeBtn, BorderLayout.NORTH);
        add(removeBtn);
        add(printBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        add(e);

        if (e.getActionCommand().equals("mark complete")) {
            JLabel markComplete = new JLabel("Which task would you like to mark complete?");
            field1 = new JTextField(7);
            add(markComplete);
            add(field1);
            JButton markcompletenowBtn = new JButton("mark complete now!");
            add(markcompletenowBtn);
            markcompletenowBtn.setActionCommand("mark complete now");
            markcompletenowBtn.addActionListener(this);
        }

        if (e.getActionCommand().equals("mark complete now")) {
            try {
                list.markComplete(field1.getText());
                JLabel completed = new JLabel(field1.getText() + " has been marked complete");
                add(completed);
            } catch (CannotAlterTask cannotAlterTask) {
                cannotAlterTask.printStackTrace();
                JLabel notask = new JLabel("This task is already cimplete");
                add(notask);
            }
        }

        remove(e);

        print(e);
    }

    private void remove(ActionEvent e) {
        if (e.getActionCommand().equals("remove")) {
            JLabel remove = new JLabel("Which task would you like to remove?");
            field1 = new JTextField(7);
            add(remove);
            add(field1);
            JButton removenowBtn = new JButton("remove now!");
            add(removenowBtn);
            removenowBtn.setActionCommand("remove now");
            removenowBtn.addActionListener(this);
        }

        removeNow(e);
    }

    private void removeNow(ActionEvent e) {
        if (e.getActionCommand().equals("remove now")) {
            try {
                list.removeTask(field1.getText());
                JLabel removed = new JLabel(field1.getText() + " has been removed");
                add(removed);
            } catch (CannotFindTask cft) {
                cft.printStackTrace();
                JLabel notask = new JLabel("This task is not in your list");
                add(notask);
            }
        }
    }


    private void add(ActionEvent e) {

        if (e.getActionCommand().equals("add")) {
            JLabel label1 = new JLabel("school or general?");
            JLabel label2 = new JLabel("enter task name:");
            field1 = new JTextField(5);
            field2 = new JTextField(10);
            add(label1);
            add(field1);
            add(label2);
            add(field2);
            JButton addnowBtn = new JButton("add now!");
            add(addnowBtn);
            addnowBtn.setActionCommand("addnow");
            addnowBtn.addActionListener(this);
        }

        addNow(e);
    }

    private void print(ActionEvent e) {
        if (e.getActionCommand().equals("print")) {
            for (Task t : list.tasks) {
                JLabel task = new JLabel("- name: " + t.getName() + "; state: " + t.completeOrNot()
                        + "; type: " + t.getType());
                add(task);
            }
        }
    }

    private void addNow(ActionEvent e) {
        JLabel label3 = new JLabel("Your task is added!");

        if (e.getActionCommand().equals("addnow")) {
            if (field1.getText().equals("general")) {
//                JLabel label3 = new JLabel("enter your task's category:");
//                JTextField field3 = new JTextField(7);
//                JButton submitButton = new JButton("submit general task");
//                add(label3);
//                add(field3);
                list.addGeneralTask(field2.getText(), "", false, "general");
                add(label3);
//                add(submitButton);
//                submitButton.setActionCommand("submit general task");
//                submitButton.addActionListener(this);
            } else if (field1.getText().equals("school")) {
                try {
                    list.addSchoolTask(field2.getText(), "", false, "school");
                    add(label3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
