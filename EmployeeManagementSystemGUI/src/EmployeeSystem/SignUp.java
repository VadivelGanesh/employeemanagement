package EmployeeSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SignUp 
{
	Connection con;
    //    initialize the system with connection object
    SignUp(Connection con)
    {
        this.con = con;
    }
    JFrame menuFrame=new JFrame();
   // JFrame eloginFrame =new JFrame();
    public void SignUpEmployee() throws SQLException  {

        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Creat EMP Account");
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
        panel.setBackground(new Color(136, 219, 200));
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
            JOptionPane.showMessageDialog(null, "Created Successfully", "Created Successfully", JOptionPane.WARNING_MESSAGE);
            //menuFrame.setVisible(true);
            frame.dispose();
        });       
        panel.add(submitButton);

        panel.setBackground(new Color(154, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280,750));
        frame.pack();
        frame.setVisible(true);

    }
}
