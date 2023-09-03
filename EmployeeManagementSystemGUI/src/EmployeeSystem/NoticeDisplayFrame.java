package EmployeeSystem;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NoticeDisplayFrame extends JFrame {

    public NoticeDisplayFrame(Connection connection, String employeeId) {
        setTitle("Messages");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BackgroundPanel backgroundPanel = new BackgroundPanel((NoticeDisplayFrame.class.getResource("/bg4.jpg")));
        add(backgroundPanel);

        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);    

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(250, 100)); // Set the preferred size
       

        // Center-align the scrollPane
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(scrollPane);

        backgroundPanel.add(centerPanel);
        boolean messagesExist = displayNoticesForId(connection, employeeId, messageArea);

        if (!messagesExist) {
            JOptionPane.showMessageDialog(
                    this,
                    "No messages found for the given ID.",
                    "No Messages",
                    JOptionPane.INFORMATION_MESSAGE
                    
            );
        }
        else
        {

        setVisible(true);
    }}

    private boolean displayNoticesForId(Connection connection, String employeeId, JTextArea messageArea) {
        String query = "SELECT msg, timestamp FROM empmsgs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employeeId);
            ResultSet resultSet = statement.executeQuery();

            boolean messagesExist = false;

            while (resultSet.next()) {
                messagesExist = true;

                String msg = resultSet.getString("msg");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                String messageText = "Date And Time: " + timestamp + "\nMessage: " + msg + "\n\n";
                messageArea.append(messageText);
            }

            return messagesExist;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(URL url) {
        try {
            backgroundImage = new ImageIcon(url).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
}