import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;


public class Clean extends JFrame {
    private Connection connection;  // MySQL connection object
    private JComboBox<String> CleanTypeDropdown1;
    private JComboBox<String> CleanTypeDropdown2;
    private JComboBox<String> roomTypeDropdown1;
    private JComboBox<String> roomTypeDropdown2;
    private JTextField quantityField1;
    private JTextField CleanTypeField3;


    // Custom RoundedPanel class
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

    public Clean(Connection connection) {
        this.connection = connection;

        // Initialize dropdowns
        CleanTypeDropdown1 = new JComboBox<>();
        CleanTypeDropdown2 = new JComboBox<>();
        roomTypeDropdown1 = new JComboBox<>();
        roomTypeDropdown2 = new JComboBox<>();
        quantityField1 = new JTextField(10);
        CleanTypeField3 = new JTextField(10);



        // Populate dropdowns with items from the database
        populateCleanTypes(CleanTypeDropdown1);
        populateCleanTypes(CleanTypeDropdown2);

        populateRoomTypes(roomTypeDropdown1);
        populateRoomTypes(roomTypeDropdown2);



        //Frame
        ImageIcon logo = new ImageIcon(getClass().getResource("/mandarin-transformed.png"));
        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo = new ImageIcon(scaledImg);

        JFrame frame = new JFrame();
        frame.setTitle("Mandarin Inventory");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);  // Center the window on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logo.getImage());

        // Container
        RoundedPanel container1 = new RoundedPanel(30); // Adjust radius for rounded edges
        container1.setLayout(new GridBagLayout());
        container1.setBackground(new Color(41, 64, 58));  // Set background color
        container1.setPreferredSize(new Dimension(870, 550));  // Set smaller preferred size

        frame.setLayout(new GridBagLayout());  // Center container1 in the frame
        frame.add(container1);


        //Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3,20,0));
        panel.setBackground(new Color(41, 64, 58));
        container1.add(panel);

        //Left Panel
        RoundedPanel panel1 = new RoundedPanel(30);
        panel1.setLayout(new GridLayout(2,1));
        panel1.setBackground(new Color(41, 64, 58));
        panel.add(panel1);



        //image
        ImageIcon logo1 = new ImageIcon(getClass().getResource("/Cl1.png"));
        Image img1 = logo1.getImage();
        Image scaledImg1 = img1.getScaledInstance(250, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo1 = new ImageIcon(scaledImg1);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel();
        label.setIcon(scaledLogo1);
        panel1.add(label, gbc);

        //Form
        RoundedPanel panel11 = new RoundedPanel(30);
        panel11.setBackground(new Color(232,229,210));
        panel11.setLayout(new GridBagLayout());
        panel1.add(panel11);



        JButton addButton1 = new JButton("Add");
        addButton1.setForeground(new Color(232,229,210));
        addButton1.setBackground(new Color(11,45,64));
        JButton removeButton1 = new JButton("Remove");
        removeButton1.setForeground(new Color(232,229,210));
        removeButton1.setBackground(new Color(11,45,64));


        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 5, 5, 5);  // Padding
        gbc1.gridx = 0; gbc1.gridy = 0;
        panel11.add(new JLabel("Clean Type:"), gbc1);
        gbc1.gridx = 1;
        panel11.add(CleanTypeDropdown1, gbc1);

        gbc1.gridx = 0; gbc1.gridy = 1;
        panel11.add(new JLabel("Room:"), gbc1);
        gbc1.gridx = 1;
        panel11.add(roomTypeDropdown1, gbc1);

        gbc1.gridx = 0; gbc1.gridy = 2;
        panel11.add(new JLabel("Quantity:"), gbc1);
        gbc1.gridx = 1;
        panel11.add(quantityField1, gbc1);

        gbc1.gridx = 0; gbc1.gridy = 3;
        gbc1.gridwidth = 2;
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(new Color(232, 229, 210));
        buttonPanel1.add(addButton1);
        buttonPanel1.add(removeButton1);
        panel11.add(buttonPanel1, gbc1);


        //Center Panel
        RoundedPanel panel2 = new RoundedPanel(30);
        panel2.setLayout(new GridLayout(3,1));
        panel2.setBackground(new Color(41, 64, 58));
        panel.add(panel2);


        JPanel panel21 = new JPanel();
        panel21.setBackground(new Color(41, 64, 58));
        panel2.add(panel21);


        //Form
        RoundedPanel panel31 = new RoundedPanel(30);
        panel31.setBackground(new Color(232,229,210));
        panel31.setLayout(new GridBagLayout());
        panel2.add(panel31);


        JButton addButton3 = new JButton("Add");
        addButton3.setForeground(new Color(232,229,210));
        addButton3.setBackground(new Color(11,45,64));



        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(5, 5, 5, 5);  // Padding
        gbc3.gridx = 0; gbc3.gridy = 0;
        gbc3.gridwidth = 2;  // Span across two columns
        gbc3.anchor = GridBagConstraints.CENTER;
        JLabel labelM = new JLabel("Manage Clean");
        labelM.setFont(new Font("Aptos Narrow", Font.PLAIN, 18));
        panel31.add(labelM, gbc3);
        gbc3.gridx = 1;

        gbc3.gridwidth = 1;
        gbc3.anchor = GridBagConstraints.WEST;
        gbc3.gridx = 0; gbc3.gridy = 1;
        panel31.add(new JLabel("Clean Type:"), gbc3);
        gbc3.gridx = 1;
        panel31.add(CleanTypeField3, gbc3);


        gbc3.gridx = 0; gbc3.gridy = 3;
        gbc3.gridwidth = 2;
        gbc3.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel3 = new JPanel();
        buttonPanel3.setBackground(new Color(232, 229, 210));
        buttonPanel3.add(addButton3);
        panel31.add(buttonPanel3, gbc3);

        // Green Color
        JPanel panel23 = new JPanel();
        panel23.setBackground(new Color(41, 64, 58));
        panel2.add(panel23);


        //Right Panel
        RoundedPanel panel3 = new RoundedPanel(30);
        panel3.setLayout(new GridLayout(2,1));
        panel3.setBackground(new Color(41, 64, 58));
        panel.add(panel3);

        //Form
        RoundedPanel panel22 = new RoundedPanel(30);
        panel22.setBackground(new Color(232,229,210));
        panel22.setLayout(new GridBagLayout());
        panel3.add(panel22);


        JButton SearchButton1 = new JButton("Search");
        SearchButton1.setForeground(new Color(232,229,210));
        SearchButton1.setBackground(new Color(11,45,64));

        JButton SearchButton2= new JButton("Search");
        SearchButton2.setForeground(new Color(232,229,210));
        SearchButton2.setBackground(new Color(11,45,64));

        JButton TotalButton = new JButton("Total Data");
        TotalButton.setForeground(new Color(232,229,210));
        TotalButton.setBackground(new Color(11,45,64));


        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(7, 7, 7, 7);  // Padding
        gbc2.gridx = 0; gbc2.gridy = 0;
        gbc2.gridwidth = 2;  // Span across two columns
        gbc2.anchor = GridBagConstraints.CENTER;
        JLabel labelI = new JLabel("Inventory");
        labelI.setFont(new Font("Aptos Narrow", Font.PLAIN, 18));
        panel22.add(labelI, gbc2);
        gbc2.gridx = 1;

        GridBagConstraints gbc22 = new GridBagConstraints();
        gbc22.gridwidth = 1;
        gbc22.gridx = 0; gbc22.gridy = 3;
        panel22.add(new JLabel("Clean Type:"), gbc22);
        gbc22.gridx = 1;
        panel22.add(CleanTypeDropdown2, gbc22);

        gbc2.gridx = 0; gbc2.gridy = 4;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        panel22.add(SearchButton1, gbc2);

        gbc22.gridx = 0; gbc22.gridy = 5;
        panel22.add(new JLabel("Room:"), gbc22);
        gbc22.gridx = 1;
        panel22.add(roomTypeDropdown2, gbc22);


        gbc2.gridx = 0; gbc2.gridy = 6;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        panel22.add(SearchButton2, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 7;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        panel22.add(TotalButton, gbc2);



        //Image
        ImageIcon logo3 = new ImageIcon(getClass().getResource("/Cl2.png"));
        Image img3 = logo3.getImage();
        Image scaledImg3 = img3.getScaledInstance(250, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo3 = new ImageIcon(scaledImg3);


        JLabel label1 = new JLabel();
        label1.setIcon(scaledLogo3);
        panel3.add(label1, gbc);

        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                additem();
            }
        });

        removeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeitem();
            }
        });

        SearchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCleanByType();
            }
        });

        SearchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoomByType();
            }
        });

        TotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {displayTotalData();
            }
        });

        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {addCleanType();
            }
        });

        // Inside the Furniture constructor (at the end of your panel definitions)

        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(232,229,210));
        backButton.setBackground(new Color(11,45,64));


// Add the button to the container (you can adjust the position as necessary)
        GridBagConstraints gbcBack = new GridBagConstraints();
        gbcBack.gridx = 0;
        gbcBack.gridy = 1;
        gbcBack.anchor = GridBagConstraints.SOUTHEAST;
        container1.add(backButton, gbcBack);

// Add an action listener to handle what happens when the back button is pressed
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Example: Go back to the previous window or close the current one
                new MainPage(connection);
                dispose();// Close the current window
                // Optionally, you can navigate to another screen here
            }
        });




        frame.setVisible(true);
    }

    // Method to add a Clean name to the Clean table
    private void addCleanType() {
        // Get the Clean name from the text field
        String CleanName = CleanTypeField3.getText().trim();

        // Check if the Clean name field is not empty
        if (CleanName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Clean name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to insert into the Clean table
        String query = "INSERT INTO Clean (clean_name) VALUES (?)";

        // Execute the insertion
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, CleanName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Clean name added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Clean (connection);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Clean name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while adding Clean name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Method to populate JComboBox with items from database
    private void populateCleanTypes(JComboBox<String> comboBox) {
        String query = "SELECT clean_name FROM Clean";  // Update with your table and column names
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String typeName = resultSet.getString("clean_name");
                comboBox.addItem(typeName);  // Add each type name to the dropdown list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load Clean types.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    // Inside the Clean class

    // Method to add selected Clean to CleanInRooms table
    private void additem() {
        // Get selected values from dropdowns and quantity field
        String selectedClean = (String) CleanTypeDropdown1.getSelectedItem();
        String selectedRoom = (String) roomTypeDropdown1.getSelectedItem();
        int quantity;

        // Check if the quantity field is not empty and parse the input
        try {
            quantity = Integer.parseInt(quantityField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if both selections are valid
        if (selectedClean == null || selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Please select both Clean and room types.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to insert into CleanInRooms
        String query = "INSERT INTO CleanInRooms (clean_id, room_id, quantity) " +
                "VALUES ((SELECT clean_id FROM Clean WHERE clean_name = ?), " +
                "(SELECT room_id FROM Rooms WHERE room_name = ?), ?)";

        // Execute the insertion
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedClean);
            statement.setString(2, selectedRoom);
            statement.setInt(3, quantity);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Clean item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Clean item.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while adding item.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Inside the Clean class

    // Method to remove quantity of selected Clean from CleanInRooms table
    private void removeitem() {
        // Get selected values from dropdowns and quantity field
        String selectedClean = (String) CleanTypeDropdown1.getSelectedItem();
        String selectedRoom = (String) roomTypeDropdown1.getSelectedItem();
        int quantity;

        // Check if the quantity field is not empty and parse the input
        try {
            quantity = Integer.parseInt(quantityField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if both selections are valid
        if (selectedClean == null || selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Please select both Clean and room types.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to get the current quantity of the selected Clean in the selected room
        String getCurrentQuantityQuery = "SELECT quantity FROM CleanInRooms WHERE clean_id = " +
                "(SELECT clean_id FROM Clean WHERE clean_name = ?) " +
                "AND room_id = (SELECT room_id FROM Rooms WHERE room_name = ?)";

        try (PreparedStatement getCurrentQuantityStatement = connection.prepareStatement(getCurrentQuantityQuery)) {
            getCurrentQuantityStatement.setString(1, selectedClean);
            getCurrentQuantityStatement.setString(2, selectedRoom);

            ResultSet resultSet = getCurrentQuantityStatement.executeQuery();
            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity - quantity;

                // Determine whether to delete the row or update the quantity
                if (newQuantity > 0) {
                    // Update the quantity
                    String updateQuantityQuery = "UPDATE CleanInRooms SET quantity = ? WHERE clean_id = " +
                            "(SELECT clean_id FROM Clean WHERE clean_name = ?) " +
                            "AND room_id = (SELECT room_id FROM Rooms WHERE room_name = ?)";

                    try (PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantityQuery)) {
                        updateQuantityStatement.setInt(1, newQuantity);
                        updateQuantityStatement.setString(2, selectedClean);
                        updateQuantityStatement.setString(3, selectedRoom);

                        int rowsUpdated = updateQuantityStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(this, "Quantity updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to update quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Delete the row if the new quantity is zero or less
                    String deleteQuery = "DELETE FROM CleanInRooms WHERE clean_id = " +
                            "(SELECT clean_id FROM Clean WHERE clean_name = ?) " +
                            "AND room_id = (SELECT room_id FROM Rooms WHERE room_name = ?)";

                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                        deleteStatement.setString(1, selectedClean);
                        deleteStatement.setString(2, selectedRoom);

                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(this, "Clean item removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "No matching Clean item found to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No matching Clean item found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while removing item.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inside the Clean class

    // Method to search by CleanType and display results in a new window
    private void searchCleanByType() {
        // Get selected CleanType from the dropdown
        String selectedClean = (String) CleanTypeDropdown2.getSelectedItem();

        // Check if the selection is valid
        if (selectedClean == null) {
            JOptionPane.showMessageDialog(this, "Please select a Clean type.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to get room names and quantities for the selected Clean type
        String query = "SELECT Rooms.room_name, CleanInRooms.quantity FROM CleanInRooms " +
                "JOIN Clean ON CleanInRooms.clean_id = Clean.clean_id " +
                "JOIN Rooms ON CleanInRooms.room_id = Rooms.room_id " +
                "WHERE Clean.clean_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedClean);

            ResultSet resultSet = statement.executeQuery();

            // Create a new JFrame to display results
            JFrame resultFrame = new JFrame("Search Results for " + selectedClean);
            resultFrame.setSize(400, 300);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a table model and JTable to show results
            DefaultTableModel model = new DefaultTableModel(new String[]{"Room Name", "Quantity"}, 0);
            JTable resultTable = new JTable(model);

            // Populate the table with data from the ResultSet
            while (resultSet.next()) {
                String roomName = resultSet.getString("room_name");
                int quantity = resultSet.getInt("quantity");
                model.addRow(new Object[]{roomName, quantity});
            }

            // Check if any results were found
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No records found for the selected Clean type.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Add the table to a scroll pane and add to the frame
            JScrollPane scrollPane = new JScrollPane(resultTable);
            resultFrame.add(scrollPane);

            // Display the result window
            resultFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while searching.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to search by RoomType and display results in a new window
    private void searchRoomByType() {
        // Get selected RoomType from the dropdown
        String selectedRoom = (String) roomTypeDropdown2.getSelectedItem();

        // Check if the selection is valid
        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Please select a room type.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to get room names and quantities for the selected Clean type
        String query = "SELECT Clean.clean_name, CleanInRooms.quantity FROM CleanInRooms " +
                "JOIN Rooms ON CleanInRooms.room_id = Rooms.room_id " +
                "JOIN Clean ON CleanInRooms.clean_id = Clean.clean_id " +
                "WHERE Rooms.room_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedRoom);

            ResultSet resultSet = statement.executeQuery();

            // Create a new JFrame to display results
            JFrame resultFrame = new JFrame("Search Results for " + selectedRoom);
            resultFrame.setSize(400, 300);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a table model and JTable to show results
            DefaultTableModel model = new DefaultTableModel(new String[]{"Clean Name", "Quantity"}, 0);
            JTable resultTable = new JTable(model);

            // Populate the table with data from the ResultSet
            while (resultSet.next()) {
                String roomName = resultSet.getString("clean_name");
                int quantity = resultSet.getInt("quantity");
                model.addRow(new Object[]{roomName, quantity});
            }

            // Check if any results were found
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No records found for the selected room type.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Add the table to a scroll pane and add to the frame
            JScrollPane scrollPane = new JScrollPane(resultTable);
            resultFrame.add(scrollPane);

            // Display the result window
            resultFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while searching.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayTotalData() {
        // SQL query to get all records from CleanInRooms table
        String query = "SELECT Clean.clean_name, Rooms.room_name, CleanInRooms.quantity " +
                "FROM CleanInRooms " +
                "JOIN Clean ON CleanInRooms.clean_id = Clean.clean_id " +
                "JOIN Rooms ON CleanInRooms.room_id = Rooms.room_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Create a new JFrame to display results
            JFrame totalDataFrame = new JFrame("Total Data - Clean In Rooms");
            totalDataFrame.setSize(500, 400);
            totalDataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a table model and JTable to show results
            DefaultTableModel model = new DefaultTableModel(new String[]{"Clean Name", "Room Name", "Quantity"}, 0);
            JTable totalDataTable = new JTable(model);

            // Populate the table with data from the ResultSet
            while (resultSet.next()) {
                String CleanName = resultSet.getString("clean_name");
                String roomName = resultSet.getString("room_name");
                int quantity = resultSet.getInt("quantity");
                model.addRow(new Object[]{CleanName, roomName, quantity});
            }

            // Check if any results were found
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No records found in the CleanInRooms table.", "No Data", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Add the table to a scroll pane and add to the frame
            JScrollPane scrollPane = new JScrollPane(totalDataTable);
            totalDataFrame.add(scrollPane);

            // Display the result window
            totalDataFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while retrieving total data.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}


