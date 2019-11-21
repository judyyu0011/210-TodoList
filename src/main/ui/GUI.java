package ui;

import model.ToDoList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        add(label, BorderLayout.NORTH);

        list = new ToDoList();

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
        JButton removeBtn = new JButton("Remove");
        JButton printBtn = new JButton("See list");
        JButton quitBtn = new JButton("Leave");


        addBtn.addActionListener(this);
        add(addBtn, BorderLayout.NORTH);
        add(completeBtn, BorderLayout.NORTH);
        add(removeBtn);
        add(printBtn);
        add(quitBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


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

        if (e.getActionCommand().equals("addnow")) {
            if (field1.getText().equals("general")) {
//                JLabel label3 = new JLabel("enter your task's category:");
//                JTextField field3 = new JTextField(7);
//                JButton submitButton = new JButton("submit general task");
//                add(label3);
//                add(field3);
                list.addGeneralTask(field2.getText(), "", false, "general");
//                add(submitButton);
//                submitButton.setActionCommand("submit general task");
//                submitButton.addActionListener(this);
            } else if (field1.getText().equals("school")) {
                try {
                    list.addSchoolTask(field2.getText(), "", false, "school");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

//        if (e.getActionCommand().equals("submit general task")) {
//            list.addGeneralTask(field2.getText(), field3.getText(), false, "general");
//        }
    }
}
