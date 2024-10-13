import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.*;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private Connection connection;  // MySQL connection object
    private JTextField textField1;   // Username field
    private JPasswordField textField2;   // Password field

    public Login(Connection connection) {
        this.connection = connection;
        // Load image from resources
        ImageIcon logo = new ImageIcon(getClass().getResource("/mandarin-transformed.png"));

        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo = new ImageIcon(scaledImg);

        JFrame frame = new JFrame();
        // Set JFrame properties
        frame.setTitle("Mandarin Inventory");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);  // Center the window on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logo.getImage());  // Set the icon image
        frame.getContentPane().setBackground(new Color(232,229,210));
        frame.setLayout(new GridLayout(1, 2));


        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(new Color(232,229,210));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel();
        label.setIcon(scaledLogo);
        panel.add(label, gbc);

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(450, 400));
        panel1.setLayout(new GridLayout(3, 1));
        panel1.setBackground(new Color(232,229,210));

        JPanel panel11 = new JPanel();
        panel11.setPreferredSize(new Dimension(450, 100));
        panel11.setBackground(new Color(232,229,210));

        JLabel title = new JLabel("The Mandarin Inn");
        title.setFont(new Font("Aptos Narrow", Font.BOLD, 30));
        title.setForeground(new Color(41,64,58));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title1 = new JLabel("Inventory");
        title1.setFont(new Font("Aptos Narrow", Font.BOLD, 30));
        title1.setForeground(new Color(41,64,58));
        title1.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel12 = new JPanel();
        panel12.setPreferredSize(new Dimension(450, 200));
        panel12.setLayout(new GridLayout(4, 3));
        panel12.setBackground(new Color(232,229,210));

        JLabel label1 = new JLabel("Username:");
        label1.setForeground(new Color(41,64,58));
        textField1 = new JTextField();  // Assigning to instance variable

        JLabel label2 = new JLabel("Password:");
        label2.setForeground(new Color(41,64,58));
        textField2 = new JPasswordField();  // Assigning to instance variable

        JPanel panel13 = new JPanel();
        panel13.setPreferredSize(new Dimension(450, 100));
        panel13.setBackground(new Color(232,229,210));

        JButton btn = new JButton("Login");
        btn.setForeground(new Color(232,229,210));
        btn.setBackground(new Color(11,45,64));
        btn.setEnabled(true);

        panel1.add(panel11);
        panel1.add(panel12);
        panel1.add(panel13);
        panel11.add(title);
        panel11.add(title1);
        panel12.add(new JLabel(""));
        panel12.add(new JLabel(""));
        panel12.add(new JLabel(""));
        panel12.add(label1);
        panel12.add(textField1);  // Correct usage of instance variable
        panel12.add(new JLabel(""));
        panel12.add(label2);
        panel12.add(textField2);  // Correct usage of instance variable
        panel12.add(new JLabel(""));
        panel12.add(new JLabel(""));
        panel12.add(new JLabel(""));
        panel13.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });

        setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.WEST); // Add logo panel to the left
        frame.add(panel1, BorderLayout.CENTER); // Add login form panel to the center

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to check the login credentials
    private void checkLogin() {
        String username = textField1.getText();  // Correct usage of instance variable
        String password = new String(textField2.getPassword());  // Correct usage of instance variable

        // SQL query to check if the username and password are correct
        String query = "SELECT * FROM login WHERE username = ? AND password = ?"; // Assuming you have a 'users' table

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Credentials are correct, close the login window and proceed
                new MainPage (connection);

            } else {
                // Credentials are incorrect, show an error message
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}





