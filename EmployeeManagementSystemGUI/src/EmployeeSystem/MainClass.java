package EmployeeSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MainClass 
{
	
	public static void main(String args[])throws Exception
	{
	  Class.forName("com.mysql.cj.jdbc.Driver");
//      creates connection with sql
      String dbName = "ems";
      String db_username = "root";
      String db_password = "root";
      Connection con= DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/"+dbName, db_username, db_password);
      
      JFrame loginFrame1;
      
      JButton submitButton2=new JButton("ADMIN LOGIN");
      submitButton2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      submitButton2.setBounds(250,200,270, 40);
      submitButton2.setBackground(Color.GREEN);
      
      JButton submitButton3=new JButton("EMPLOYEE LOGIN ");
      submitButton3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
      submitButton3.setBounds(250,300,270, 40);
      submitButton3.setBackground(Color.YELLOW);
      loginFrame1=new JFrame("EMPLOYEE MANAGEMENT SYSTEM");
      loginFrame1.add(submitButton2);
      loginFrame1.add(submitButton3);
      
      submitButton2.addActionListener(actionEvent -> 
      {
    	  MainClass m=new MainClass();
	     try {
			m. AdminLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
      });
      submitButton3.addActionListener(actionEvent -> 
      {
    	  MainClass m=new MainClass();
	     try {
			m. EmployeeLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
      });
      loginFrame1.setSize(800,600);
      loginFrame1.setLayout(null);
      loginFrame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      ImageIcon background_image =new ImageIcon((MainClass.class.getResource("/bg9.jpg"))); 
      Image img=background_image.getImage();
      Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
      background_image=new ImageIcon(temp_img);
      JLabel background=new JLabel("",background_image,JLabel.CENTER);
      background.setBounds(0,0,800,600);
      loginFrame1.add(background);
      loginFrame1.setVisible(true);

  }
	
	public void AdminLogin() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
//      creates connection with sql
      String dbName = "ems";
      String db_username = "root";
      String db_password = "root";
      Connection con= DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/"+dbName, db_username, db_password);
		EmployeeManagementSystemGUI ems = new EmployeeManagementSystemGUI(con);
	      ems.loginFrame = new JFrame("Login");
	      JLabel usernameLabel = new JLabel("Username");
	      usernameLabel.setForeground(Color.BLUE);
	      usernameLabel.setBounds(200,150,220,50);
	      usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      ems.loginFrame.add(usernameLabel);
	
	      JTextField usernameField = new JTextField();
	      usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	      usernameField.setBounds(450,150,420,50);
	      ems.loginFrame.add(usernameField);
	
	      JLabel passwordLabel = new JLabel("Password");
	      passwordLabel.setForeground(Color.BLUE);
	      passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      passwordLabel.setBounds(200,250,220,50);
	      ems.loginFrame.add(passwordLabel);
	
	      JPasswordField passwordField = new JPasswordField();
	      passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	      passwordField.setBounds(450,250,420,50);
	      ems.loginFrame.add(passwordField);
	
	      JButton submitButton=new JButton("Submit");
	      submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      
	      submitButton.setBounds(450,400,250, 50);
	
	      submitButton.addActionListener(actionEvent -> 
	      {
	          ems.username = usernameField.getText();
	         ems.password = String.valueOf(passwordField.getPassword());
	          try 
	          {
	              boolean auth = ems.login(ems.username, ems.password);
	              if(auth)
	              {
	                  ems.loginFrame.dispose();
	                  ems.mainMenu();
	              }
	          } 
	          catch (SQLException throwables) 
	          {
	              throwables.printStackTrace();
	          }
	      });
	      ems.loginFrame.add(submitButton);
	
	
	      
	      ems.loginFrame.setSize(1280,750);
	      ems.loginFrame.setLayout(null);
	      ems.loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      ImageIcon background_image =new ImageIcon((MainClass.class.getResource("/Adminlogin.jpg"))); 
	      Image img=background_image.getImage();
	      Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
	      background_image=new ImageIcon(temp_img);
	      JLabel background=new JLabel("",background_image,JLabel.CENTER);
	      background.setBounds(0,0,1280,750);
	      ems.loginFrame.add(background);
	      ems.loginFrame.setVisible(true);
	}
	
	public void EmployeeLogin() throws  Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
//      creates connection with sql
      String dbName = "ems";
      String db_username = "root";
      String db_password = "root";
      Connection con= DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/"+dbName, db_username, db_password);
		EmployeeDetails em = new EmployeeDetails(con);
	      em.eloginFrame = new JFrame("Employee Login");
	      JLabel usernameLabel = new JLabel("Employeename");
	      usernameLabel.setForeground(Color.WHITE);
	      usernameLabel.setBounds(200,150,220,50);
	      usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      em.eloginFrame.add(usernameLabel);
	
	      JTextField usernameField = new JTextField();
	      usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	      usernameField.setBounds(450,150,450,50);
	      em.eloginFrame.add(usernameField);
	
	      JLabel idLabel = new JLabel("Empolyee ID");
	      idLabel.setForeground(Color.WHITE);
	      idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      idLabel.setBounds(200,250,220,50);
	      em.eloginFrame.add(idLabel);
	
	      JPasswordField idField = new JPasswordField();
	      idField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	      idField.setBounds(450,250,450,50);
	      em.eloginFrame.add(idField);
	
	      JButton submitButton=new JButton("SignIn");
	      submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      submitButton.setBounds(450,400,200,40);
	      
	      JButton submitButton1=new JButton("SignUp");
	      submitButton1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	      submitButton1.setBounds(700,400,200,40);
	
	      submitButton.addActionListener(actionEvent -> 
	      {
	    	  if (idField.getPassword()==null) {
	                JOptionPane.showMessageDialog(null, "Please Enter Correct Id.", "Invalid Id", JOptionPane.WARNING_MESSAGE);
	                return; // Exit the ActionListener if phone number is invalid
	            }
	          em.name = usernameField.getText();
	         em.id = String.valueOf(idField.getPassword());
	         
	          try 
	          {
	              boolean auth = em.Emplogin(em.name, em.id);
	              if(auth)
	              {
	                  em.eloginFrame.dispose();
	                  em.Emainmenu();
	              }
	          } 
	          catch (SQLException throwables) 
	          {
	              throwables.printStackTrace();
	          }
	      });
	      em.eloginFrame.add(submitButton);
	      submitButton1.addActionListener(actionEvent -> 
	      {
	          SignUp s=new SignUp(con);
	          try 
	          {
	              
	                  em.eloginFrame.dispose();
	                  s.SignUpEmployee();
	              
	          } 
	          catch (SQLException throwables) 
	          {
	              throwables.printStackTrace();
	          }
	      });
	      em.eloginFrame.add(submitButton1);
	      em.eloginFrame.setSize(1280,750);
	      em.eloginFrame.setLayout(null);
	      em.eloginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      ImageIcon background_image =new ImageIcon((MainClass.class.getResource("/bg4.jpg"))); 
	      Image img=background_image.getImage();
	      Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
	      background_image=new ImageIcon(temp_img);
	      JLabel background=new JLabel("",background_image,JLabel.CENTER);
	      background.setBounds(0,0,1280,750);
	      em.eloginFrame.add(background);
	      em.eloginFrame.setVisible(true);
	}
}


