import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room extends JFrame {
    private Connection connection;
    private JComboBox<String> roomTypeDropdown;
    private JPanel rightPanel;

    public class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);  // Makes the panel transparent
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }

    public Room(Connection connection) {
        this.connection = connection;

        // ComboBox for room types
        roomTypeDropdown = new JComboBox<>();
        populateRoomTypes(roomTypeDropdown);

        // Set frame properties
        setTitle("Mandarin Inventory");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Frame icon
        ImageIcon logo = new ImageIcon(getClass().getResource("/mandarin-transformed.png"));
        Image img = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        setIconImage(new ImageIcon(img).getImage());

        // Container for rounded panel
        RoundedPanel container = new RoundedPanel(30);
        container.setLayout(new GridBagLayout());
        container.setBackground(new Color(41, 64, 58));
        container.setPreferredSize(new Dimension(870, 550));

        setLayout(new GridBagLayout());
        add(container);

        // Main panel with a dark green background
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(41, 64, 58));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Left panel with GridLayout
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Add logo to the left panel
        ImageIcon logo1 = new ImageIcon(getClass().getResource("/R1.png"));
        Image img1 = logo1.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img1));
        leftPanel.add(label);

        // Form section within the left panel
        RoundedPanel formPanel = new RoundedPanel(30);
        formPanel.setBackground(new Color(232, 229, 210));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setOpaque(false);
        leftPanel.add(formPanel);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.setForeground(new Color(232, 229, 210));
        searchButton.setBackground(new Color(41, 64, 58));

        // GridBagConstraints setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);

        // Title label in formPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Room Inventory");
        titleLabel.setFont(new Font("Aptos Narrow", Font.PLAIN, 18));
        formPanel.add(titleLabel, gbc);

        // Room dropdown label
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Room:"), gbc);

        // Room dropdown
        gbc.gridx = 1;
        formPanel.add(roomTypeDropdown, gbc);

        // Search button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(searchButton, gbc);

        // Right panel as placeholder
        rightPanel = new RoundedPanel(30);
        rightPanel.setBackground(new Color(232, 229, 210));
        rightPanel.setPreferredSize(new Dimension(500, 400));
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Add mainPanel to container
        container.add(mainPanel);

        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(232,229,210));
        backButton.setBackground(new Color(11,45,64));


// Add the button to the container (you can adjust the position as necessary)
        GridBagConstraints gbcBack = new GridBagConstraints();
        gbcBack.gridx = 0;
        gbcBack.gridy = 1;
        gbcBack.anchor = GridBagConstraints.SOUTHEAST;
        container.add(backButton, gbcBack);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoomByType();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Example: Go back to the previous window or close the current one
                new MainPage(connection);
                dispose();// Close the current window
                // Optionally, you can navigate to another screen here
            }
        });

        // Finalize and display the frame
        pack();
        setVisible(true);
    }

    private void populateRoomTypes(JComboBox<String> comboBox) {
        String query = "SELECT room_name FROM Rooms";  // Update with your table and column names
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String typeName = resultSet.getString("room_name");
                comboBox.addItem(typeName);  // Add each type name to the dropdown list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load room types.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchRoomByType() {
        String selectedRoom = (String) roomTypeDropdown.getSelectedItem();

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Please select a room.");
            return;
        }

        try {
            String query = "SELECT Furniture.furniture_name AS Item, FurnitureInRooms.quantity AS Quantity " +
                    "FROM FurnitureInRooms " +
                    "JOIN Rooms ON Rooms.room_id = FurnitureInRooms.room_id " +
                    "JOIN Furniture ON Furniture.furniture_id = FurnitureInRooms.furniture_id " +
                    "WHERE Rooms.room_name = ? " +
                    "UNION ALL " +
                    "SELECT Electronics.electronics_name AS Item, ElectronicsInRooms.quantity AS Quantity " +
                    "FROM ElectronicsInRooms " +
                    "JOIN Rooms ON Rooms.room_id = ElectronicsInRooms.room_id " +
                    "JOIN Electronics ON Electronics.electronics_id = ElectronicsInRooms.electronics_id " +
                    "WHERE Rooms.room_name = ? " +
                    "UNION ALL " +
                    "SELECT Miscellaneous.Miscellaneous_name AS Item, MiscellaneousInRooms.quantity AS Quantity " +
                    "FROM MiscellaneousInRooms " +
                    "JOIN Rooms ON Rooms.room_id = MiscellaneousInRooms.room_id " +
                    "JOIN Miscellaneous ON Miscellaneous.Miscellaneous_id = MiscellaneousInRooms.Miscellaneous_id " +
                    "WHERE Rooms.room_name = ?";  // Remove the last UNION query

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedRoom);
            statement.setString(2, selectedRoom);
            statement.setString(3, selectedRoom);

            ResultSet resultSet = statement.executeQuery();

            // Table to display data
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Item", "Quantity"}, 0);
            JTable table = new JTable(tableModel);

            // Populate table model with data from ResultSet
            while (resultSet.next()) {
                String item = resultSet.getString("Item");
                int quantity = resultSet.getInt("Quantity");
                tableModel.addRow(new Object[]{item, quantity});
            }

            // Clear the right panel and add the table inside a JScrollPane
            rightPanel.removeAll();
            JScrollPane scrollPane = new JScrollPane(table);
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data for the selected room.");
        }
    }

}
