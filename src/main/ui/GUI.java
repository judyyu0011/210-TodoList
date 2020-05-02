package ui;

import exceptions.CannotAlterTask;
import exceptions.CannotFindTask;
import model.Task;
import model.ToDoList;
import network.Weather;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class GUI extends JFrame implements ActionListener {

    private ToDoList list;
    private JTextField addTypeField;
    private JTextField addField;
    private JTextField completeField;
    private JTextField removeField;
    private JPanel tablePanel;

    private GUI() throws IOException {
        super("ToDoList");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());
//        setPreferredSize(new Dimension(10000, 300));

        Panel headPanel = new Panel(new FlowLayout());

        JLabel label = new JLabel("Welcome to your To-Do List! What would you like to do?");

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("todolist-icon.png").getImage()
                .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel();
        picLabel.setIcon(imageIcon);

        headPanel.add(picLabel);
        headPanel.add(label);

        getContentPane().add(headPanel, BorderLayout.PAGE_START);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        addButtons(buttonPanel);

        getContentPane().add(buttonPanel, BorderLayout.LINE_START);


        list = new ToDoList();
        list.load("TodoListData");

        String[] columnNames = {"Name", "State", "Type",};
        String [][] data = new String[list.tasks.size()][3];
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][0] = t.getName();
        }
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][1] = t.completeOrNot();
        }
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][2] = t.getType();
        }

        JTable table = new JTable(data, columnNames);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBorder(new EmptyBorder(0, 13, 0, 0));
        tablePanel.add(table.getTableHeader());
        tablePanel.add(table);
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        tablePanel.setBackground(new Color(194,210,244));

//        listPanel = new JPanel();
//        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
//        listPanel.setBorder(new EmptyBorder(0, 13, 0, 0));
//        listPanel.setBackground(new Color(194,210,244));
//        for (Task t : list.tasks) {
//            JLabel task = new JLabel("Name: " + t.getName() + "     state: " + t.completeOrNot()
//                    + "     type: " + t.getType());
//            listPanel.add(task);
//        }
//
//        getContentPane().add(listPanel, BorderLayout.CENTER);
        Weather weather = new Weather();
        DecimalFormat df = new DecimalFormat("#.#");
        JLabel tempLabel = new JLabel("Vancouver: " + df.format(weather.temp - 273.15) + "\u00B0" + "C");
        JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tempPanel.add(tempLabel);
        tempPanel.setBackground(new Color(194,210,244));
        getContentPane().add(tempPanel, BorderLayout.PAGE_END);

        getContentPane().setBackground(new Color(194,210,244));

        pack();
        setVisible(true);
//        setResizable(false);
    }

    private void addButtons(Panel panel) {

        JPanel addTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addTypeField = new JTextField(7);
        JLabel addTypeLabel = new JLabel("Enter 'general or 'school'");
        addTypePanel.add(addTypeField);
        addTypePanel.add(addTypeLabel);

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addBtn = new JButton("Add");
        addField = new JTextField(7);
        addBtn.setActionCommand("add");
        addPanel.add(addField);
        addPanel.add(addBtn);

        JPanel completePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton completeBtn = new JButton("Mark complete");
        completeField = new JTextField(7);
        completeBtn.setActionCommand("mark complete");
        completePanel.add(completeField);
        completePanel.add(completeBtn);

        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeBtn = new JButton("Remove");
        removeField = new JTextField(7);
        removeBtn.setActionCommand("remove");
        removePanel.add(removeField);
        removePanel.add(removeBtn);

        JPanel quitPanel = new JPanel(new FlowLayout());
        JButton quitBtn = new JButton("Quit");
        quitBtn.setActionCommand("save");
        quitPanel.add(quitBtn);

        addBtn.addActionListener(this);
        completeBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        quitBtn.addActionListener(this);

        panel.add(addTypePanel);
        panel.add(addPanel);
        panel.add(completePanel);
        panel.add(removePanel);
        panel.add(quitPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        add(e);

        complete(e);

        remove(e);

        try {
            refresh();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (e.getActionCommand().equals("save")) {
            try {
                list.save("TodoListData");
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }

    }


    private void complete(ActionEvent e) {

        if (e.getActionCommand().equals("mark complete")) {
            try {
                list.markComplete(completeField.getText());
                JOptionPane.showMessageDialog(null,
                        completeField.getText() + " has been marked complete");
            } catch (CannotAlterTask cannotAlterTask) {
                cannotAlterTask.printStackTrace();
                JOptionPane.showMessageDialog(null, "This task is already complete");
            }
        }
    }


    private void remove(ActionEvent e) {

        if (e.getActionCommand().equals("remove")) {
            try {
                list.removeTask(removeField.getText());
                JOptionPane.showMessageDialog(null, removeField.getText() + " has been removed");
            } catch (CannotFindTask cft) {
                cft.printStackTrace();
                JOptionPane.showMessageDialog(null, "This task is not in your list");
            }
        }
    }



    private void add(ActionEvent e) {

        if (e.getActionCommand().equals("add")) {
            if (addTypeField.getText().equals("general")) {
                list.addGeneralTask(addField.getText(), "", false, "general");
                JOptionPane.showMessageDialog(null, "Your general task is added!");
            } else if (addTypeField.getText().equals("school")) {
                try {
                    list.addSchoolTask(addField.getText(), "", false, "school");
                    JOptionPane.showMessageDialog(null, "Your school task is added!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is not a valid type");
            }

        }
    }

    private void refresh() throws IOException {
        list.save("TodoListData");
        getContentPane().remove(tablePanel);
        getContentPane().revalidate();
        getContentPane().repaint();

        list = new ToDoList();
        list.load("TodoListData");

        String[] columnNames = {"Name", "State", "Type",};
        String [][] data = new String[list.tasks.size()][3];
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][0] = t.getName();
        }
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][1] = t.completeOrNot();
        }
        for (int i = 0; i < list.tasks.size(); i++) {
            Task t = list.tasks.get(i);
            data[i][2] = t.getType();
        }

        JTable table = new JTable(data, columnNames);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBorder(new EmptyBorder(0, 13, 0, 0));
        tablePanel.add(table.getTableHeader());
        tablePanel.add(table);
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        tablePanel.setBackground(new Color(194,210,244));

        getContentPane().add(tablePanel, BorderLayout.CENTER);

//        list.save("TodoListData");
//
//        listPanel = new JPanel();
//        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
//        list = new ToDoList();
//        list.load("TodoListData");
//
//        for (Task t : list.tasks) {
//            JLabel task = new JLabel("Name: " + t.getName() + "     state: " + t.completeOrNot()
//                    + "     type: " + t.getType());
//            listPanel.add(task);
//        }
//        getContentPane().add(listPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }
}
