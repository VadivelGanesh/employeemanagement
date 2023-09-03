package EmployeeSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmployeeManagementSystemGUI {

    Connection con;
    //    initialize the system with connection object
    EmployeeManagementSystemGUI(Connection con){
        this.con = con;
    }


    public boolean login(String username, String password) throws SQLException {
        Statement statement = this.con.createStatement();
        String q = String.format("select password from admin where username = '%s';", username);
        ResultSet resultSet = statement.executeQuery(q);
//        gets us password if the username exists
        if(resultSet.next()){
//            compare password with user input
            if(resultSet.getString(1).equals(password)){
                return true;
            }
            else {
                JFrame popup = new JFrame("Invalid password");
                JLabel popupMsg = new JLabel("The password you entered is invalid.");
                popupMsg.setBounds(20,10,300,50);
                popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                popup.add(popupMsg);

                JButton button = new JButton("OK");
                button.setBounds(120,60,70,20);
                button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                button.addActionListener(actionEvent2 -> {
                    popup.dispose();
                });
                popup.add(button);

                popup.setLayout(null);
                popup.setSize(350, 150);
                popup.setVisible(true);
                return false;
            }
        }
        else {
            JFrame popup = new JFrame("Invalid username");
            JLabel popupMsg = new JLabel("The username you entered does not exist.");
            popupMsg.setBounds(20,10,500,50);
            popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            popup.add(popupMsg);

            JButton button = new JButton("OK");
            button.setBounds(170,60,70,20);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            button.addActionListener(actionEvent2 -> {
                popup.dispose();
            });
            popup.add(button);

            popup.setLayout(null);
            popup.setSize(450, 150);
            popup.setVisible(true);
            return false;
        }
    }

    static String username = "";
    static String password = "";

    JFrame menuFrame;
    JFrame loginFrame;

    public void viewEmployee() throws SQLException {
        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Employee Records");
        JPanel panel=new JPanel();

        Statement statement = this.con.createStatement();
        String q = "select * from employee";
        ResultSet resultSet = statement.executeQuery(q);

        while (resultSet.next()) {
            JPanel employeeCard = new JPanel();

            JLabel idLabel = new JLabel("ID");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(1)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameVal = new JLabel(resultSet.getString(2));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderLabel = new JLabel("Gender");
            genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel genderVal = new JLabel(resultSet.getString(3));
            genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneLabel = new JLabel("Phone Number");
            phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel phoneVal = new JLabel(resultSet.getString(4));
            phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel emailVal = new JLabel(resultSet.getString(5));
            emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel designationLabel = new JLabel("Designation");
            designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel designationVal = new JLabel(resultSet.getString(6));
            designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel salaryLabel = new JLabel("Salary");
            salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel salaryVal = new JLabel(String.valueOf(resultSet.getDouble(7)));
            salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            employeeCard.add(idLabel);
            employeeCard.add(idVal);
            employeeCard.add(nameLabel);
            employeeCard.add(nameVal);
            employeeCard.add(genderLabel);
            employeeCard.add(genderVal);
            employeeCard.add(phoneLabel);
            employeeCard.add(phoneVal);
            employeeCard.add(emailLabel);
            employeeCard.add(emailVal);
            employeeCard.add(designationLabel);
            employeeCard.add(designationVal);
            employeeCard.add(salaryLabel);
            employeeCard.add(salaryVal);

            employeeCard.setSize(1000, 400);
            employeeCard.setBackground(new Color(166, 209, 230));
            employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            employeeCard.setLayout(cardLayout);
            panel.add(employeeCard);
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(500, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(254, 251, 246));
        panel.add(buttonPanel);


    //        panel.setPreferredSize(new Dimension(500,500));
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,750));
        frame.pack();
        frame.setVisible(true);

    }


    public void addEmployee() throws SQLException  {

        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Employee Records");
        JPanel panel=new JPanel();

        JLabel idLabel = new JLabel("Enter ID");
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField("");
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Enter gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Male", "Female"});
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Enter Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField("");
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        phoneVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JLabel emailLabel = new JLabel("Enter Email Address");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField("");
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Enter Designation");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField("");
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Enter Salary ($)");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField("");
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        panel.add(idLabel);
        panel.add(idVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(designationLabel);
        panel.add(designationVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
        	 if (idVal.getText()!=null) {
                 JOptionPane.showMessageDialog(null, "Please Enter Correct Id.", "Invalid Id", JOptionPane.WARNING_MESSAGE);
                 return; // Exit the ActionListener if phone number is invalid
             }
            Statement statement = null;
            try {
                statement = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            int id = Integer.parseInt(idVal.getText());
            String name = nameVal.getText();
            String gender = (String) genderVal.getSelectedItem();
            String phoneNum = phoneVal.getText();
            String email = emailVal.getText();
            String designation = designationVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());

            // Validate phone number length
            if (phoneNum.length() != 10) {
                JOptionPane.showMessageDialog(null, "Phone number must have exactly 10 digits.", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
                return; // Exit the ActionListener if phone number is invalid
            }

            String q = String.format("insert into employee values (%d, '%s', '%s', '%s', '%s', '%s', %f);", id, name, gender,
                    phoneNum, email, designation, salary);
            try {
                statement.executeUpdate(q);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            menuFrame.setVisible(true);
            frame.dispose();
        });       
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,750));
        frame.pack();
        frame.setVisible(true);

    }
 public void editEmployee() throws SQLException {
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Edit Employee");

        JLabel label = new JLabel("Enter employee id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            menuFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
        	 if (idVal.getText()==null) {
                 JOptionPane.showMessageDialog(null, "Please Enter Correct Id.", "Invalid Id", JOptionPane.WARNING_MESSAGE);
                 return; // Exit the ActionListener if phone number is invalid
             }
            int id = Integer.parseInt(idVal.getText());
            String q = String.format("select * from employee where id = %d;", id);
            Statement statement = null;
            try {
                statement = this.con.createStatement();
                ResultSet resultSet = statement.executeQuery(q);
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
    //                    return true;
                    editEmployeeHelper(id, frame);

                }
            else{
                JFrame popup = new JFrame("Invalid ID");
                JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                popupMsg.setBounds(20,10,300,50);
                popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                popup.add(popupMsg);

                JButton button = new JButton("OK");
                button.setBounds(120,60,70,20);
                button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                button.addActionListener(actionEvent2 -> {
                    popup.dispose();
                });
                popup.add(button);

                popup.setLayout(null);
                popup.setSize(350, 150);
                popup.setVisible(true);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1280,750));
        frame.setVisible(true);
    }

    public void editEmployeeHelper(int id, JFrame parentFrame) throws SQLException {
        JFrame frame = new JFrame("Edit Employee");
        JPanel panel=new JPanel();

        Statement statement = null;
        try {
            statement = this.con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String q = String.format("select * from employee where id = %d;",id);
        ResultSet resultSet = statement.executeQuery(q);
        resultSet.next();
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField nameVal = new JTextField(resultSet.getString(2));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        String gender = resultSet.getString(3);
        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Male", "Female"});
        genderVal.setSelectedIndex(gender.equals("Male") ? 0 : 1 );
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField phoneVal = new JTextField(resultSet.getString(4));
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        phoneVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField emailVal = new JTextField(resultSet.getString(5));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel designationLabel = new JLabel("Designation");
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField designationVal = new JTextField(resultSet.getString(6));
        designationVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JTextField salaryVal = new JTextField(String.valueOf(resultSet.getDouble(7)));
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(designationLabel);
        panel.add(designationVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            Statement statemnt = null;
            try {
                statemnt = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String name = nameVal.getText();
            String genderValSelectedItem = (String) genderVal.getSelectedItem();
            String phoneNum = phoneVal.getText();
            String email = emailVal.getText();
            String designation = designationVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());
            if (phoneNum.length() != 10) {
                JOptionPane.showMessageDialog(null, "Phone number must have exactly 10 digits.", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
                return; // Exit the ActionListener if phone number is invalid
            }

            String query = String.format("update employee set name = '%s', gender = '%s', phoneNum = '%s', email = '%s', " +
                            "designation = '%s', salary = %s where id = %d;", name, genderValSelectedItem,
                    phoneNum, email, designation, salary, id);
            try {
                statemnt.executeUpdate(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            menuFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,750));
        frame.pack();
        frame.setVisible(true);
        parentFrame.dispose();
    }

    public void deleteEmployee(){
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Delete Employee");

        JLabel label = new JLabel("Enter employee id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);
      
        backButton.addActionListener(actionListener -> {
        	menuFrame.setVisible(true);
            frame.dispose();
        });
        frame.add(backButton);

        JButton submitButton = new JButton("Delete");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            if (idVal.getText()==null) {
                JOptionPane.showMessageDialog(null, "Please Enter Correct Id.", "Invalid Id", JOptionPane.WARNING_MESSAGE);
                return; // Exit the ActionListener if phone number is invalid
            }
            int id = Integer.parseInt(idVal.getText());
            String q = String.format("select * from employee where id = %d;", id);
            Statement statement = null;
            try {
                statement = this.con.createStatement();
                ResultSet resultSet = statement.executeQuery(q);
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));

                    String deleteQuery = String.format("delete from employee where id = %d;", id);
                    statement.executeUpdate(deleteQuery);
                    menuFrame.setVisible(true);
                    frame.dispose();

                }
                else{
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    button.addActionListener(actionEvent2 -> {
                        popup.dispose();
                    });
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1280,750));
        frame.setVisible(true);
    }


    public void mainMenu(){
        menuFrame = new JFrame("Employee Management System");

        JLabel welcomeLabel = new JLabel("ADMIN PAGE");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.GREEN);
        welcomeLabel.setBounds(500,50,800,50);
        menuFrame.add(welcomeLabel);

        JButton viewEmpButton = new JButton("View all employees");
        viewEmpButton.setBounds(200, 140, 300, 40);
        viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        viewEmpButton.setBackground(Color.PINK);
        viewEmpButton.setFocusPainted(false);
        viewEmpButton.addActionListener(actionEvent -> {
            try {
                viewEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(viewEmpButton);

        JButton addEmpButton = new JButton("Add an employee");
        addEmpButton.setBounds(200, 200, 300, 40);
        addEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addEmpButton.setBackground(Color.PINK);
        addEmpButton.setFocusPainted(false);
        addEmpButton.addActionListener(actionEvent -> {
            try {
                addEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(addEmpButton);

        JButton editEmpButton = new JButton("Edit an employee");
        editEmpButton.setBounds(200, 270, 300, 40);
        editEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editEmpButton.setBackground(Color.PINK);
        editEmpButton.setFocusPainted(false);
        editEmpButton.addActionListener(actionEvent -> {
            try {
                editEmployee();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(editEmpButton);

        JButton deleteEmpButton = new JButton("Delete an employee");
        deleteEmpButton.setBounds(200, 340, 300, 40);
        deleteEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deleteEmpButton.setBackground(Color.PINK);
        deleteEmpButton.setFocusPainted(false);
        deleteEmpButton.addActionListener(deleteEvent -> {
            deleteEmployee();
        });
        menuFrame.add(deleteEmpButton);
        
        JButton sendButton = new JButton("Send Message");
        sendButton.setBounds(200, 410, 300, 40);
        sendButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        sendButton.setBackground(Color.PINK);
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(deleteEvent -> {
            sendMsg();
        });
        menuFrame.add(sendButton);
        
        JButton LeaveEmpButton = new JButton("Employee Leave");
        LeaveEmpButton .setBounds(200, 480, 300, 40);
        LeaveEmpButton .setFont(new Font("Times New Roman", Font.PLAIN, 20));
        LeaveEmpButton .setBackground(Color.PINK);
        LeaveEmpButton .setFocusPainted(false);
        LeaveEmpButton .addActionListener(deleteEvent -> {
            try {
				viewEmployeeLeave();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        menuFrame.add(LeaveEmpButton );

        
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(600, 480, 300, 40);
        exitButton.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        exitButton.setBackground(Color.RED);
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> {
            menuFrame.dispose();
        });
        menuFrame.add(exitButton);


        menuFrame.setSize(1280,750);
        menuFrame.setLayout(null);//using no layout managers
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);//making the frame visible
        ImageIcon background_image =new ImageIcon((EmployeeManagementSystemGUI.class.getResource("/bg1.jpg"))); 
        Image img=background_image.getImage();
        Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
        background_image=new ImageIcon(temp_img);
        JLabel background=new JLabel("",background_image,JLabel.CENTER);
        background.setBounds(0,0,1280,750);
        menuFrame.add(background);
        menuFrame.setVisible(true);

    }
     
    public void viewEmployeeLeave() throws SQLException {
        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Employee Records");
        JPanel panel=new JPanel();

        Statement statement = this.con.createStatement();
        String q = "select * from leave_requests";
        ResultSet resultSet = statement.executeQuery(q);

        while (resultSet.next()) {
            JPanel employeeCard = new JPanel();
            

            JLabel idLabel = new JLabel("S_No");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(1)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            
            JLabel idLabel1 = new JLabel("ID");
            idLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel idVal1 = new JLabel(String.valueOf(resultSet.getInt(2)));
            idVal1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel nameVal = new JLabel(resultSet.getString(3));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel reasonLabel = new JLabel("Reason");
            reasonLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel reasonVal = new JLabel(resultSet.getString(4));
            reasonVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel LTLabel = new JLabel("Leave_Type");
            LTLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel LTVal = new JLabel(resultSet.getString(5));
            LTVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel SDLabel = new JLabel("Start_Date");
            SDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel SDVal = new JLabel(resultSet.getString(6));
            SDVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel EDLabel = new JLabel("End_Date");
            EDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel EDVal = new JLabel(resultSet.getString(7));
            EDVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel resLabel = new JLabel("Response");
            resLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            JLabel resVal = new JLabel(String.valueOf(resultSet.getString(8)));
            resVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            
            //employeeCard.add(idLabel);
           //  employeeCard.add(idVal);
            employeeCard.add(idLabel1);
            employeeCard.add(idVal1);
            employeeCard.add(nameLabel);
            employeeCard.add(nameVal);
            employeeCard.add(reasonLabel);
            employeeCard.add(reasonVal);
            employeeCard.add(LTLabel);
            employeeCard.add(LTVal);
            employeeCard.add(SDLabel);
            employeeCard.add(SDVal);
            employeeCard.add(EDLabel);
            employeeCard.add(EDVal);
            employeeCard.add(resLabel);
            employeeCard.add(resVal);
           
            JButton acceptButton = new JButton("Accept");
            JButton declineButton = new JButton("Decline");
            int currentSNo = resultSet.getInt(1);
            int currentId=resultSet.getInt(2);
            String leave=resultSet.getString(5);
            String st_date=resultSet.getString(6);
            String en_date=resultSet.getString(7);
            acceptButton.addActionListener(actionListener -> {
            	int days=numberOfDays(st_date,en_date);
//            	 Map<String, Integer> leaveValues = retrieveLeaveValues(id);
//     	        int casualLeave = leaveValues.get("casual_leave");
//     	        int annualLeave = leaveValues.get("annual_leave");
//     	        int personalLeave = leaveValues.get("personal_leave");
//
//            	if (leave.equals("Casual Leave") && days > casualLeave) {
//    	            JOptionPane.showMessageDialog(this, "Not enough casual leave days.");
//    	            return;
//    	        } else if (leave.equals("Annual Leave") && days > annualLeave) {
//    	            JOptionPane.showMessageDialog(this, "Not enough annual leave days.");
//    	            return;
//    	        } else if (leave.equals("Personal Leave") && days > personalLeave) {
//    	            JOptionPane.showMessageDialog(this, "Not enough personal leave days.");
//    	            return;
//    	        }
                updateResponse(currentSNo,currentId, "ACCEPTED",leave,days);
                JOptionPane.showMessageDialog(frame, "Leave request accepted!");
                
            });

            declineButton.addActionListener(actionListener -> {
                updateResponse1(currentId, "DECLINED");
                JOptionPane.showMessageDialog(frame, "Leave request declined!");
                
                
            });
            if(resultSet.getString(8).equals("REQUEST PROCESSING"))
            {
            employeeCard.add(acceptButton);
            employeeCard.add(declineButton);
            }
            

            employeeCard.setSize(1000, 400);
            employeeCard.setBackground(new Color(166, 209, 230));
            employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            employeeCard.setLayout(cardLayout);
            panel.add(employeeCard);
        }
        

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(500, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(254, 251, 246));
        panel.add(buttonPanel);


    //        panel.setPreferredSize(new Dimension(500,500));
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);
        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 0, 50, 0));

        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,750));
        frame.pack();
        frame.setVisible(true);

    }
    
    private void updateResponse(int id,int ids, String response,String leave,int nodays) {
        try {
        	
            String updateQuery = "UPDATE leave_requests SET response = ? WHERE s_no = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, response);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
           preparedStatement.close();
           
        // Update the casual_leave column
           if(leave.equals("Casual Leave"))
           {
           String updateCasualLeaveQuery = "UPDATE employee SET casual_leave = casual_leave - ? WHERE id = ?";
           PreparedStatement updateCasualLeaveStatement = con.prepareStatement(updateCasualLeaveQuery);
           updateCasualLeaveStatement.setInt(1, nodays);
           updateCasualLeaveStatement.setInt(2, ids);
           updateCasualLeaveStatement.executeUpdate();
           updateCasualLeaveStatement.close();
           }
           else if(leave.equals("Annual Leave"))
           {
        	   String updateCasualLeaveQuery = "UPDATE employee SET annual_leave = annual_leave - ? WHERE id = ?";
               PreparedStatement updateCasualLeaveStatement = con.prepareStatement(updateCasualLeaveQuery);
               updateCasualLeaveStatement.setInt(1, nodays);
               updateCasualLeaveStatement.setInt(2, ids);
               updateCasualLeaveStatement.executeUpdate();
               updateCasualLeaveStatement.close();
           }
           else
           {
        	   String updateCasualLeaveQuery = "UPDATE employee SET personal_leave = personal_leave - ? WHERE id = ?";
               PreparedStatement updateCasualLeaveStatement = con.prepareStatement(updateCasualLeaveQuery);
               updateCasualLeaveStatement.setInt(1, nodays);
               updateCasualLeaveStatement.setInt(2, ids);
               updateCasualLeaveStatement.executeUpdate();
               updateCasualLeaveStatement.close();   
           }
         
           
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while updating response: " + e.getMessage());
        }
    }
    private void updateResponse1(int id, String response) {
        try {
        	
            String updateQuery = "UPDATE leave_requests SET response = ? WHERE s_no = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, response);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
           preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while updating response: " + e.getMessage());
        }
    }
  
  public static int numberOfDays(String start,String end)
  {
	  String startDateStr = start;
      String endDateStr = end;

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      LocalDate startDate = LocalDate.parse(startDateStr, formatter);
      LocalDate endDate = LocalDate.parse(endDateStr, formatter);

      int daysDifference = (int) ChronoUnit.DAYS.between(startDate, endDate);
      return daysDifference;
  }


    public void sendMsg() {
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Send Message");
        frame.setLayout(null);
        frame.setSize(new Dimension(1280, 750));
        
        JLabel label = new JLabel("Enter employee id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);
        
        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        idVal.setBounds(500, 200, 200, 40);
        frame.add(idVal);
        
        // ... (your existing code)
        JLabel label1 = new JLabel("Message");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label1.setBounds(250,300,200,50);
        frame.add(label1);
        
        
        JTextArea msgVal = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(msgVal);
        scrollPane.setBounds(500, 300, 300, 150); // Adjust dimensions as needed
        frame.add(scrollPane);

        // ... (your existing code)

        JButton submitButton = new JButton("Send");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setBounds(300, 550, 150, 40);
        submitButton.setFocusPainted(false); 
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setBounds(550, 550, 150, 40);
        backButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            String idText = idVal.getText();
            if (idText.isEmpty() || !idText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Please Enter a Valid ID.", "Invalid ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int ids = Integer.parseInt(idText);
            String msgs = msgVal.getText();

            String checkExistenceQuery = "SELECT COUNT(*) FROM empmsgs WHERE id = ?";
            String insertQuery = "INSERT INTO empmsgs (id, msg) VALUES (?, ?)";
            String updateQuery = "UPDATE empmsgs SET msg = ?, timestamp = CURRENT_TIMESTAMP WHERE id = ?";

            try (PreparedStatement checkExistenceStmt = con.prepareStatement(checkExistenceQuery);
                 PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                 PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {

                checkExistenceStmt.setInt(1, ids);
                ResultSet existenceResult = checkExistenceStmt.executeQuery();
                existenceResult.next();
                int messageCount = existenceResult.getInt(1);

                if (messageCount == 0) {
                    insertStmt.setInt(1, ids);
                    insertStmt.setString(2, msgs);
                    insertStmt.executeUpdate();
                } else {
                    updateStmt.setString(1, msgs);
                    updateStmt.setInt(2, ids);
                    updateStmt.executeUpdate();
                }

                // Show success message in a popup
                JOptionPane.showMessageDialog(null, "Message sent/updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Perform further actions if needed
                menuFrame.setVisible(true);
                frame.dispose();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while processing the request.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(submitButton);
        frame.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menuFrame.setVisible(true);
            	frame.dispose();
            }
           });
        frame.setVisible(true);
    }
}
