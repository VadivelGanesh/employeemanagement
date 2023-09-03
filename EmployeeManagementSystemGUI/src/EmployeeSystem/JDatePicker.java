package EmployeeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import com.toedter.calendar.JCalendar;

public class JDatePicker extends JPanel {
    private JSpinner spinner;
    private JButton calendarButton;
    private JPopupMenu calendarPopup;
    private JCalendar jCalendar;

    public JDatePicker() {
        spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(dateEditor);
        spinner.setValue(new Date());

        // No need to create calendarButton since we're using jCalendar directly

        calendarPopup = new JPopupMenu();
        jCalendar = new JCalendar();
        calendarPopup.add(jCalendar);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(spinner);

        spinner.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calendarPopup.show(spinner, 0, spinner.getHeight());
            }
        });

        // Set selected date from the calendar to the spinner
        jCalendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("day")) {
                    Date selectedDate = jCalendar.getDate();
                    spinner.setValue(selectedDate);
                    calendarPopup.setVisible(false);
                }
            }
        });
    }

    public Date getValue() {
        return (Date) spinner.getValue();
    }
    
    public void database()
    {
    	
    }

    
}


