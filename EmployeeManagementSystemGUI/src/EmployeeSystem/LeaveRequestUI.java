package EmployeeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LeaveRequestUI extends JFrame {
	
	LeaveRequestUI(String employeeReason1, Date startDate2, Date endDate2, String leaveType2)
	{
		
	}
	private JTextArea employeeReason;
    private Date startDate;
    private Date endDate;
    private String leaveType;
	
    private JComboBox<String> leaveTypeComboBox;
    private JDatePicker startDatePicker;
    private JDatePicker endDatePicker;
    private JButton submitButton;
   
    Connection con;
    String id;
    String name;
    public boolean b=false;
    public LeaveRequestUI(Connection con,String id,String name) 
    {
    	this.con=con;
    	this.id=id;
    	this.name=name;
    	JFrame frame=new JFrame();
        employeeReason = new JTextArea();
        
        JScrollPane scrollPane = new JScrollPane(employeeReason);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        employeeReason.setBounds(300, 400, 250, 100);
        frame.add(employeeReason);
        leaveTypeComboBox = new JComboBox<>(new String[]{"Casual Leave", "Annual Leave", "Personal"});
        startDatePicker = new JDatePicker();
        endDatePicker = new JDatePicker();
        submitButton = new JButton("Submit");

        JLabel LT = new JLabel("Leave Type:");
	    LT.setForeground(Color.BLUE);
	    LT.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	    LT.setBounds(100,100,150,50);
	    frame.add(LT);
        
        leaveTypeComboBox.setForeground(Color.BLUE);
        leaveTypeComboBox.setBounds(300,100,220,50);
        leaveTypeComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(leaveTypeComboBox);
        
        JLabel ST = new JLabel("Start Date:");
	    ST.setForeground(Color.BLUE);
	    ST.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	    ST.setBounds(100,200,220,50);
	    frame.add(ST);
	     
	    startDatePicker.setForeground(Color.BLUE);
	    startDatePicker.setBounds(300,200,100,30);
	    startDatePicker.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(startDatePicker);
        
        JLabel ET = new JLabel("End Date:");
	    ET.setForeground(Color.BLUE);
	    ET.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	    ET.setBounds(100,300,220,50);
	    frame.add(ET);
	     
	    endDatePicker.setForeground(Color.BLUE);
	    endDatePicker.setBounds(300,300,100,30);
	    endDatePicker.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        frame.add(endDatePicker);
        
        JLabel LR = new JLabel("Leave Reason:");
	    LR.setForeground(Color.BLUE);
	    LR.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	    LR.setBounds(100,400,220,50);
	    frame.add(LR);
	     
	    
        
       submitButton.setBounds(100,550,150,50);
       submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
       frame.add(submitButton);
       
       JButton backButton=new JButton("Back");
       backButton.setBounds(300,550,150,50);
       backButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
       frame.add(backButton);
       
       
        // Event handling
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					submitLeaveRequest();
					if(b==true)
					{
						frame.dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
       
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
          frame.setSize(1280,750);
	      frame.setLayout(null);
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      ImageIcon background_image =new ImageIcon((LeaveRequestUI.class.getResource("/bg7.jpg"))); 
	      Image img=background_image.getImage();
	      Image temp_img=img.getScaledInstance(1280, 750, Image.SCALE_SMOOTH);
	      background_image=new ImageIcon(temp_img);
	      JLabel background=new JLabel("",background_image,JLabel.CENTER);
	      background.setBounds(0,0,1280,750);
	      frame.add(background);
	      frame.setVisible(true);
    }
    public static int empleave(Date startDate, Date endDate) {
        long daysDifference = ChronoUnit.DAYS.between(
            startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
        return (int) daysDifference;
    }

    private Map<String, Integer> retrieveLeaveValues(String employeeId) throws SQLException {
        Map<String, Integer> leaveValues = new HashMap<>();
        
        String query = "SELECT casual_leave, annual_leave, personal_leave FROM employee WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            leaveValues.put("casual_leave", resultSet.getInt("casual_leave"));
            leaveValues.put("annual_leave", resultSet.getInt("annual_leave"));
            leaveValues.put("personal_leave", resultSet.getInt("personal_leave"));
        }

        preparedStatement.close();

        return leaveValues;
    }
    private void submitLeaveRequest() throws SQLException {
    	b=false;
       String employeeReason1 = employeeReason.getText();
        String leaveType = (String) leaveTypeComboBox.getSelectedItem();
        Date startDate = (Date) startDatePicker.getValue();
        Date endDate = (Date) endDatePicker.getValue();
        
        LocalDate startDateOnly = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDateOnly = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
	        
	        if (startDateOnly.equals(endDateOnly)) {
	            JOptionPane.showMessageDialog(this, "Start date and end date cannot be the same.");
	            return; 
	        }
	        if (startDateOnly.equals(endDateOnly)) {
	            JOptionPane.showMessageDialog(this, "Start date and end date cannot be the same.");
	            return; 
	        }
	        if (startDateOnly.isAfter(endDateOnly)) {
	            JOptionPane.showMessageDialog(this, "Start date cannot be after end date.");
	            return; 
	        }
	
	        if (employeeReason1.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please enter a leave reason.");
	            return;
	        }
	        int num=empleave(startDate,endDate);
	        Map<String, Integer> leaveValues = retrieveLeaveValues(id);
	        int casualLeave = leaveValues.get("casual_leave");
	        int annualLeave = leaveValues.get("annual_leave");
	        int personalLeave = leaveValues.get("personal_leave");

	        if (leaveType.equals("Casual Leave") && num > casualLeave) {
	            JOptionPane.showMessageDialog(this, "Not enough casual leave days.");
	            return;
	        } else if (leaveType.equals("Annual Leave") && num > annualLeave) {
	            JOptionPane.showMessageDialog(this, "Not enough annual leave days.");
	            return;
	        } else if (leaveType.equals("Personal Leave") && num > personalLeave) {
	            JOptionPane.showMessageDialog(this, "Not enough personal leave days.");
	            return;
	        }
	        
	        LeaveRequestUI leaveRequest = new LeaveRequestUI(employeeReason1, startDate, endDate, leaveType);
	        try {
	        	String insertQuery = "INSERT INTO leave_requests (eid,ename,reason, leave_type, start_date, end_date) VALUES (?,?,?, ?, ?, ?)";
	            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
	            preparedStatement.setString(1, id);
	            preparedStatement.setString(2,name);
	            preparedStatement.setString(3, employeeReason1);
	            preparedStatement.setString(4, leaveType);
	            preparedStatement.setDate(5, new java.sql.Date(startDate.getTime()));
	            preparedStatement.setDate(6, new java.sql.Date(endDate.getTime()));
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error while saving leave request: " + e.getMessage());
	        }
	        JOptionPane.showMessageDialog(this, "Leave request submitted!");
	        b=true;
	    }
	private Map<String, Integer> getLeaveValuesFromEmployeeTable(String id2) {
		// TODO Auto-generated method stub
		return null;
	}
 }

