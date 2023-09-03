package EmployeeSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmployeeDetails 
{
	Connection con;
    //    initialize the system with connection object
    EmployeeDetails(Connection con)
    {
        this.con = con;
    }
    JFrame emenuFrame=new JFrame();
    JFrame eloginFrame =new JFrame();
    
    public boolean Emplogin(String name, String ids) throws SQLException 
    {
    	id=ids;
        Statement statement = this.con.createStatement();
        String q = String.format("select id from employee where name = '%s';", name);
        ResultSet resultSet = statement.executeQuery(q);
//        gets us password if the username exists
        if(resultSet.next()){
//            compare password with user input
            if(resultSet.getString(1).equals(id)){
                return true;
            }
            else {
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

    static String name = "";
    static String id = "";

    public void Emainmenu()
    {
    	
    	 emenuFrame = new JFrame(name+" Profile");

         JLabel welcomeLabel = new JLabel("Welcome to DashBoard");
         welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
         welcomeLabel.setBounds(480,40,800,50);
         emenuFrame.add(welcomeLabel);

         JButton viewEmpButton = new JButton("My Profile");
         viewEmpButton.setBounds(500, 100, 300, 40);
         viewEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         viewEmpButton.setBackground(Color.CYAN);
         viewEmpButton.setFocusPainted(false);
         viewEmpButton.addActionListener(actionEvent -> {
             try {
                 viewEmployee1(id);
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         });
         emenuFrame.add(viewEmpButton);



         JButton editEmpButton = new JButton("Edit ");
         editEmpButton.setBounds(500, 190, 300, 40);
         editEmpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         editEmpButton.setBackground(Color.CYAN);
         editEmpButton.setFocusPainted(false);
         editEmpButton.addActionListener(actionEvent -> {
             try {
                 editEmployee(id,emenuFrame);
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         });
         emenuFrame.add(editEmpButton);

         JButton showNotice = new JButton("Message");
         showNotice.setBounds(500, 270, 300, 40);
         showNotice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         showNotice.setBackground(Color.CYAN);
         showNotice.setFocusPainted(false);
         showNotice.addActionListener(actionEvent -> {
             try {
            	 showNotice(id);
             } catch (Exception throwables) {
                 throwables.printStackTrace();
             }
         });
         emenuFrame.add(showNotice);
         
         JButton applyLeave = new JButton("Apply Leave");
         applyLeave.setBounds(500, 350, 300, 40);
         applyLeave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         applyLeave.setBackground(Color.CYAN);
         applyLeave.setFocusPainted(false);
         applyLeave.addActionListener(actionEvent -> {
             try {
            	 LeaveRequestUI leaveRequestUI = new LeaveRequestUI(con,id,name);
             } catch (Exception throwables) {
                 throwables.printStackTrace();
             }
         });
         emenuFrame.add(applyLeave);
         
         JButton leaveButton = new JButton("Result Leave");
         leaveButton.setBounds(500, 430, 300, 40);
         leaveButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         leaveButton.setBackground(Color.CYAN);
         leaveButton.setFocusPainted(false);
        
         leaveButton.addActionListener(actionEvent -> {
             emenuFrame.dispose();
             try {
            	 leaveEmployee1(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
         });
         emenuFrame.add(leaveButton);


         JButton exitButton = new JButton("LogOut");
         exitButton.setBounds(500, 520, 300, 40);
         exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
         exitButton.setBackground(Color.CYAN);
         exitButton.setFocusPainted(false);

         exitButton.addActionListener(actionEvent -> {
             emenuFrame.dispose();
         });
         emenuFrame.add(exitButton);


         emenuFrame.setSize(12800,750);
         emenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         emenuFrame.setLayout(null);//using no layout managers
         ImageIcon background_image =new ImageIcon((EmployeeDetails.class.getResource("/bg5.jpg"))); 
	      Image img=background_image.getImage();
	      Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
	      background_image=new ImageIcon(temp_img);
	      JLabel background=new JLabel("",background_image,JLabel.CENTER);
	      background.setBounds(0,0,1280,750);
	      emenuFrame.add(background);
	      emenuFrame.setVisible(true);
}
    
    
    	public void viewEmployee1(String employeeId) throws SQLException {
        emenuFrame.setVisible(false);
        JFrame frame = new JFrame("My Details");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(254, 251, 246));

        Statement statement = con.createStatement();
        String query = String.format("SELECT * FROM employee WHERE id = %s;", employeeId);
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            JPanel employeeCard = new JPanel(new GridLayout(0, 2));
            employeeCard.setBackground(new Color(166, 209, 230));
            employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));

            String[] labels = {"ID", "Name", "Gender", "PhoneNum", "Email", "Designation", "Salary"};
            for (String label : labels) {
                JLabel labelComponent = new JLabel(label);
                labelComponent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                employeeCard.add(labelComponent);

                JLabel valueComponent = new JLabel(resultSet.getString(label.toLowerCase()));
                valueComponent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                employeeCard.add(valueComponent);
            }

            panel.add(employeeCard);
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                emenuFrame.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(254, 251, 246));

        panel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 750));
        frame.pack();
        frame.setVisible(true);
    }
    	
    	public void leaveEmployee1(String employeeId) throws SQLException {
            emenuFrame.setVisible(false);
            JFrame frame = new JFrame("My Leave Details");
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBackground(new Color(254, 251, 246));

            Statement statement = con.createStatement();
            String query = String.format("SELECT * FROM leave_requests WHERE eid = %s;", employeeId);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                JPanel employeeCard = new JPanel(new GridLayout(0, 2));
                employeeCard.setBackground(new Color(166, 209, 230));
                employeeCard.setBorder(new EmptyBorder(20, 50, 20, 50));

                String[] labels = {"EID", "EName", "REASON", "LEAVE_TYPE", "START_DATE", "END_DATE", "RESPONSE"};
                for (String label : labels) {
                    JLabel labelComponent = new JLabel(label);
                    labelComponent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    employeeCard.add(labelComponent);

                    JLabel valueComponent = new JLabel(resultSet.getString(label.toLowerCase()));
                    valueComponent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    employeeCard.add(valueComponent);
                }

                panel.add(employeeCard);
            }

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            backButton.setFocusPainted(false);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    emenuFrame.setVisible(true);
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            buttonPanel.setBackground(new Color(254, 251, 246));

            panel.add(buttonPanel);

            JScrollPane scrollPane = new JScrollPane(panel);
            frame.add(scrollPane);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1280, 750));
            frame.pack();
            frame.setVisible(true);
        }
    	
    	
    	
    	
    	public void editEmployee(String employeeId,JFrame parentFrame) throws SQLException {
    		
    	        JFrame frame = new JFrame("Edit Employee");
    	        JPanel panel=new JPanel();

    	        Statement statement = null;
    	        try {
    	            statement = this.con.createStatement();
    	        } catch (SQLException throwables) {
    	            throwables.printStackTrace();
    	        }

    	        String q = String.format("select * from employee where id = %s;",employeeId);
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
    	        backButton.setBounds(500, 30, 200,50);
    	        backButton.setFocusPainted(false);

    	        backButton.addActionListener(actionListener -> {
    	            frame.dispose();
    	            emenuFrame.setVisible(true);
    	        });
    	        panel.add(backButton);

    	        JButton submitButton = new JButton("Submit");
    	        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    	        submitButton.setBounds(500, 30, 200,50);
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
    	                            "designation = '%s', salary = %s where id = %s;", name, genderValSelectedItem,
    	                    phoneNum, email, designation, salary, id);
    	            try {
    	                statemnt.executeUpdate(query);
    	            } catch (SQLException throwables) {
    	                throwables.printStackTrace();
    	            }

    	            emenuFrame.setVisible(true);
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
    	
    	
    	public void showNotice(String ids) {
    	    SwingUtilities.invokeLater(() -> new NoticeDisplayFrame(con, ids));
    	}
        
        public void viewEmployeeLeave(String employeeId) throws SQLException {
            emenuFrame.setVisible(false);

            JFrame frame=new JFrame("Employee Records");
            JPanel panel=new JPanel();

            Statement statement = this.con.createStatement();
            String query = String.format("SELECT * FROM employee WHERE id = %s;", employeeId);
            ResultSet resultSet = statement.executeQuery(query);

            
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
               
            
                int currentSNo = resultSet.getInt(1);
                int currentId=resultSet.getInt(2);
                String leave=resultSet.getString(5);
                String st_date=resultSet.getString(6);
                String en_date=resultSet.getString(7);
                
               
                
                

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
                emenuFrame.setVisible(true);
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

}
