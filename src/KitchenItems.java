import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class KitchenItems extends JFrame {
    private Connection connection;  // MySQL connection object
    private JComboBox<String> KitchenItemsTypeDropdown1;
    private JComboBox<String> KitchenItemsTypeDropdown2;
    private JTextField quantityField1;
    private JTextField KitchenItemsTypeField3;
    private JTextField quantityDisplayLabel;


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

    public KitchenItems(Connection connection) {
        this.connection = connection;

        // Initialize dropdowns
        KitchenItemsTypeDropdown1 = new JComboBox<>();
        KitchenItemsTypeDropdown2 = new JComboBox<>();
        quantityField1 = new JTextField(10);
        KitchenItemsTypeField3 = new JTextField(10);
        quantityDisplayLabel = new JTextField(5);
        quantityDisplayLabel.setEditable(false);



        // Populate dropdowns with items from the database
        populateKitchenItemsTypes(KitchenItemsTypeDropdown1);
        populateKitchenItemsTypes(KitchenItemsTypeDropdown2);




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
        ImageIcon logo1 = new ImageIcon(getClass().getResource("/K1.png"));
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
        panel11.add(new JLabel("Kitchen Items"), gbc1);
        gbc1.gridx = 1;
        panel11.add(KitchenItemsTypeDropdown1, gbc1);


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
        JLabel labelM = new JLabel("Manage Kitchen Items");
        labelM.setFont(new Font("Aptos Narrow", Font.PLAIN, 18));
        panel31.add(labelM, gbc3);
        gbc3.gridx = 1;

        gbc3.gridwidth = 1;
        gbc3.anchor = GridBagConstraints.WEST;
        gbc3.gridx = 0; gbc3.gridy = 1;
        panel31.add(new JLabel("Kitchen Items"), gbc3);
        gbc3.gridx = 1;
        panel31.add(KitchenItemsTypeField3, gbc3);


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
        panel22.add(new JLabel("Kitchen Items"), gbc22);
        gbc22.gridx = 1;
        panel22.add(KitchenItemsTypeDropdown2, gbc22);

        gbc2.gridx = 0; gbc2.gridy = 4;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        panel22.add(SearchButton1, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 5;
        gbc2.anchor = GridBagConstraints.WEST;
        panel22.add(new JLabel("Quantity:"), gbc2);
        gbc2.gridx = 1;
        panel22.add(quantityDisplayLabel, gbc2);


        gbc2.gridx = 0; gbc2.gridy = 7;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        panel22.add(TotalButton, gbc2);



        //Image
        ImageIcon logo3 = new ImageIcon(getClass().getResource("/K2.png"));
        Image img3 = logo3.getImage();
        Image scaledImg3 = img3.getScaledInstance(250, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo3 = new ImageIcon(scaledImg3);


        JLabel label1 = new JLabel();
        label1.setIcon(scaledLogo3);
        panel3.add(label1, gbc);


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
                searchKitchenItemsByType();
            }
        });


        TotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {displayTotalData();
            }
        });

        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {addKitchenItemsType();
            }
        });



        frame.setVisible(true);
    }

    // Method to add a KitchenItems name to the KitchenItems table
    private void addKitchenItemsType() {
        // Get the KitchenItems name from the text field
        String KitchenItemsName = KitchenItemsTypeField3.getText().trim();

        // Check if the KitchenItems name field is not empty
        if (KitchenItemsName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a KitchenItems name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL query to insert into the KitchenItems table
        String query = "INSERT INTO KitchenItems (KitchenItems_name) VALUES (?)";

        // Execute the insertion
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, KitchenItemsName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "KitchenItems name added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new KitchenItems (connection);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add KitchenItems name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred while adding KitchenItems name.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Method to populate JComboBox with items from database
    private void populateKitchenItemsTypes(JComboBox<String> comboBox) {
        String query = "SELECT KitchenItems_name FROM KitchenItems";  // Update with your table and column names
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String typeName = resultSet.getString("KitchenItems_name");
                comboBox.addItem(typeName);  // Add each type name to the dropdown list
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load KitchenItems types.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void additem() {
        String selectedItem = (String) KitchenItemsTypeDropdown1.getSelectedItem();
        String quantityText = quantityField1.getText();

        try {
            int quantityToAdd = Integer.parseInt(quantityText);  // Parse the quantity from input

            // SQL query to get the current quantity of the selected item
            String getQuantityQuery = "SELECT quantity FROM KitchenItems WHERE KitchenItems_name = ?";
            PreparedStatement getQuantityStmt = connection.prepareStatement(getQuantityQuery);
            getQuantityStmt.setString(1, selectedItem);

            ResultSet resultSet = getQuantityStmt.executeQuery();

            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("quantity");
                int newQuantity = currentQuantity + quantityToAdd;

                // Update the quantity in the database
                String updateQuery = "UPDATE KitchenItems SET quantity = ? WHERE KitchenItems_name = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setInt(1, newQuantity);
                updateStmt.setString(2, selectedItem);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Quantity updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update quantity.");
                }

                updateStmt.close();
            } else {
                JOptionPane.showMessageDialog(this, "Kitchen item not found.");
            }

            getQuantityStmt.close();
            resultSet.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating quantity.");
        }
    }

    private void removeitem() {
        String selectedItem = (String) KitchenItemsTypeDropdown1.getSelectedItem(); // Get the selected item from the dropdown
        String quantityText = quantityField1.getText(); // Get the quantity input as a String

        // Validate input
        if (selectedItem == null || selectedItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a kitchen item.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (quantityText == null || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText); // Convert quantity input to integer
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be greater than 0.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prepare SQL query to get the current quantity
        String getCurrentQtySql = "SELECT quantity FROM KitchenItems WHERE KitchenItem_name = ?";
        try (PreparedStatement getCurrentQtyStmt = connection.prepareStatement(getCurrentQtySql)) {
            getCurrentQtyStmt.setString(1, selectedItem); // Set the kitchen item name
            ResultSet resultSet = getCurrentQtyStmt.executeQuery();

            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("quantity"); // Fetch current quantity

                // Calculate new quantity
                int newQuantity = currentQuantity - quantity;

                if (newQuantity > 0) {
                    // Update the quantity in the database
                    String updateQtySql = "UPDATE KitchenItems SET quantity = ? WHERE KitchenItem_name = ?";
                    try (PreparedStatement updateQtyStmt = connection.prepareStatement(updateQtySql)) {
                        updateQtyStmt.setInt(1, newQuantity); // Set new quantity
                        updateQtyStmt.setString(2, selectedItem); // Set kitchen item name
                        updateQtyStmt.executeUpdate(); // Execute update
                        JOptionPane.showMessageDialog(this, "Quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // If the new quantity is zero or less, delete the row
                    String deleteItemSql = "DELETE FROM KitchenItems WHERE KitchenItem_name = ?";
                    try (PreparedStatement deleteItemStmt = connection.prepareStatement(deleteItemSql)) {
                        deleteItemStmt.setString(1, selectedItem); // Set kitchen item name
                        deleteItemStmt.executeUpdate(); // Execute delete
                        JOptionPane.showMessageDialog(this, "Item removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selected item does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error removing item: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Method to display KitchenItems data in a new JFrame with a JTable
    private void displayTotalData() {
        JFrame dataFrame = new JFrame("Kitchen Items Data");
        dataFrame.setSize(600, 400);
        dataFrame.setLocationRelativeTo(null);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        // Define table columns
        tableModel.addColumn("KitchenItem_id");
        tableModel.addColumn("KitchenItems_name");
        tableModel.addColumn("Quantity");

        // Retrieve data from database and populate the table
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM KitchenItems")) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // Get data from each column in the KitchenItems table
                String itemId = resultSet.getString("KitchenItems_id");
                String itemName = resultSet.getString("KitchenItems_name");
                String quantity = resultSet.getString("Quantity");

                // Add row to the table model
                tableModel.addRow(new Object[]{itemId, itemName, quantity});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add table to the dataFrame inside a scroll pane
        dataFrame.add(new JScrollPane(table));
        dataFrame.setVisible(true);
    }
    // Method to search for kitchen items
    private void searchKitchenItemsByType() {
        String selectedItem = (String) KitchenItemsTypeDropdown2.getSelectedItem();
        if (selectedItem != null) {
            String query = "SELECT quantity FROM KitchenItems WHERE Kitchenitems_name = ?"; // Modify according to your DB schema

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedItem);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int quantity = resultSet.getInt("quantity");
                    quantityDisplayLabel.setText(""+ quantity); // Update the label with the quantity
                } else {
                    quantityDisplayLabel.setText("Not Found"); // Handle case where item is not found
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle SQL exceptions
            }
        }
    }
}



