package ui;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import model.Task;
import model.ToDoList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GUI extends JFrame implements ActionListener {

    private ToDoList list;
    private JTextField field1;
    private JTextField field2;

    private GUI() throws IOException {
        super("ToDoList UI");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome to your todo list! What would you like to do?");


        ImageIcon imageIcon = new ImageIcon(new ImageIcon("todolist-icon.png").getImage()
                .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel();
        picLabel.setIcon(imageIcon);
        add(picLabel);

        add(label, BorderLayout.NORTH);

        list = new ToDoList();

        this.getContentPane().setBackground(new Color(194,210,244));

        list.load("TodoListData");

        addButtons();
        pack();
        setVisible(true);
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

//        JButton saveBtn = new JButton("Save");
//        printBtn.setActionCommand("save");

        addBtn.addActionListener(this);
        completeBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        printBtn.addActionListener(this);
//        saveBtn.addActionListener(this);

        add(addBtn);
        add(completeBtn);
        add(removeBtn);
        add(printBtn);
//        add(saveBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        add(e);

        complete(e);

        remove(e);

        print(e);

//        if (e.getActionCommand().equals("save")) {
//            try {
//                list.save("TodoListData");
//            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    private void complete(ActionEvent e) {
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

        completeNow(e);
    }

    private void completeNow(ActionEvent e) {
        if (e.getActionCommand().equals("mark complete now")) {
            try {
                list.markComplete(field1.getText());
                JOptionPane.showMessageDialog(null,
                        field1.getText() + " has been marked complete");
            } catch (CannotAlterTask cannotAlterTask) {
                cannotAlterTask.printStackTrace();
                JOptionPane.showMessageDialog(null, "This task is already complete");
            }
        }
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
                JOptionPane.showMessageDialog(null, field1.getText() + " has been removed");
            } catch (CannotFindTask cft) {
                cft.printStackTrace();
                JOptionPane.showMessageDialog(null, "This task is not in your list");
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
            if (list.tasks.size() == 0) {
                JOptionPane.showMessageDialog(null, "Your list is empty!");
            }

            for (Task t : list.tasks) {
                JLabel task = new JLabel("- name: " + t.getName() + "; state: " + t.completeOrNot()
                        + "; type: " + t.getType());
                add(task);
            }
        }
    }

    private void addNow(ActionEvent e) {
        if (e.getActionCommand().equals("addnow")) {
            if (field1.getText().equals("general")) {
                list.addGeneralTask(field2.getText(), "", false, "general");
                JOptionPane.showMessageDialog(null, "Your task is added!");
            } else if (field1.getText().equals("school")) {
                try {
                    list.addSchoolTask(field2.getText(), "", false, "school");
                    JOptionPane.showMessageDialog(null, "Your task is added!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }
}
