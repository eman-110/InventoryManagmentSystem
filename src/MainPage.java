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

public class MainPage extends JFrame {
    private Connection connection;  // MySQL connection object


    public MainPage(Connection connection) {
        this.connection = connection;

        ImageIcon logo = new ImageIcon(getClass().getResource("/mandarin-transformed.png"));
        Image img = logo.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon scaledLogo = new ImageIcon(scaledImg);


        JFrame frame = new JFrame();
        frame.setTitle("Mandarin Inventory");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);  // Center the window on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logo.getImage());  // Set the icon image
        frame.getContentPane().setBackground(new Color(41,64,58));
        frame.setLayout(new GridLayout(2, 1));

        JPanel row1panel = new JPanel();
        row1panel.setBackground(new Color(41,64,58));
        row1panel.setLayout(new GridLayout(1,4));
        frame.add(row1panel);

        //Furniture

        ImageIcon furlogo = new ImageIcon(getClass().getResource("/Furniture_image-removebg-preview.png"));
        Image furimg = furlogo.getImage();
        Image furscaledImg = furimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon furscaledLogo = new ImageIcon(furscaledImg);

        JPanel container1 = new JPanel(new GridBagLayout());
        container1.setBackground(new Color(41,64,58));

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension( 150, 150));
        panel1.setBackground(new Color(41,64,58));

        RoundedButton btn1 = new RoundedButton("", 20);
        btn1.setIcon(furscaledLogo);
        btn1.setFocusable(false);
        btn1.setBorder(BorderFactory.createEtchedBorder());
        btn1.setBackground(new Color(144, 238, 144));
        btn1.setEnabled(true);


        //Electronics

        ImageIcon elelogo = new ImageIcon(getClass().getResource("/Electronics_image-removebg-preview.png"));
        Image eleimg = elelogo.getImage();
        Image elescaledImg = eleimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon elescaledLogo = new ImageIcon(elescaledImg);

        JPanel container2 = new JPanel(new GridBagLayout()); // GridBagLayout centers its component
        container2.setBackground(new Color(41,64,58));

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(150, 150));
        panel2.setBackground(new Color(41,64,58));

        RoundedButton btn2 = new RoundedButton("", 20);
        btn2.setIcon(elescaledLogo);
        btn2.setFocusable(false);
        btn2.setBorder(BorderFactory.createEtchedBorder());
        btn2.setBackground(new Color(144, 238, 144));
        btn2.setEnabled(true);

        //Miscellaneous
        ImageIcon mislogo = new ImageIcon(getClass().getResource("/Miscellaneous_image-removebg-preview.png"));
        Image misimg = mislogo.getImage();
        Image misscaledImg = misimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon misscaledLogo = new ImageIcon(misscaledImg);

        JPanel container3 = new JPanel(new GridBagLayout());
        container3.setBackground(new Color(41,64,58));
        container3.setBackground(new Color(41,64,58));

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(150, 150));
        panel3.setBackground(new Color(41,64,58));

        RoundedButton btn3 = new RoundedButton("", 20);
        btn3.setIcon(misscaledLogo);
        btn3.setFocusable(false);
        btn3.setBorder(BorderFactory.createEtchedBorder());
        btn3.setBackground(new Color(144, 238, 144));
        btn3.setEnabled(true);

        //clean
        ImageIcon cllogo = new ImageIcon(getClass().getResource("/Clean_image-removebg-preview.png"));
        Image climg = cllogo.getImage();
        Image clscaledImg = climg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon clscaledLogo = new ImageIcon(clscaledImg);

        JPanel container4 = new JPanel(new GridBagLayout());
        container4.setBackground(new Color(41,64,58));

        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(150, 150));
        panel4.setBackground(new Color(41,64,58));

        RoundedButton btn4 = new RoundedButton("", 20);
        btn4.setIcon(clscaledLogo);
        btn4.setFocusable(false);
        btn4.setBorder(BorderFactory.createEtchedBorder());
        btn4.setBackground(new Color(144, 238, 144));
        btn4.setEnabled(true);

        //Row 2 Panel
        JPanel row2panel = new JPanel();
        row2panel.setBackground(new Color(41,64,58));
        row2panel.setLayout(new GridLayout(1,4));
        frame.add(row2panel);

        //Kitchen
        ImageIcon klogo = new ImageIcon(getClass().getResource("/Kitchen_image-removebg-preview.png"));
        Image kimg = klogo.getImage();
        Image kscaledImg = kimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon kscaledLogo = new ImageIcon(kscaledImg);

        JPanel container6 = new JPanel(new GridBagLayout());
        container6.setBackground(new Color(41,64,58));

        JPanel panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(150, 150));
        panel6.setBackground(new Color(41,64,58));

        RoundedButton btn6 = new RoundedButton("", 20);
        btn6.setIcon(kscaledLogo);
        btn6.setFocusable(false);
        btn6.setBorder(BorderFactory.createEtchedBorder());
        btn6.setBackground(new Color(144, 238, 144));
        btn6.setEnabled(true);


        //Laundry
        ImageIcon lalogo = new ImageIcon(getClass().getResource("/Laundry_image-removebg-preview.png"));
        Image laimg = lalogo.getImage();
        Image lascaledImg = laimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon lascaledLogo = new ImageIcon(lascaledImg);

        JPanel container7 = new JPanel(new GridBagLayout());
        container7.setBackground(new Color(41,64,58));

        JPanel panel7 = new JPanel();
        panel7.setPreferredSize(new Dimension( 150, 150));
        panel7.setBackground(new Color(41,64,58));

        RoundedButton btn7 = new RoundedButton("", 20);
        btn7.setIcon(lascaledLogo);
        btn7.setFocusable(false);
        btn7.setBorder(BorderFactory.createEtchedBorder());
        btn7.setBackground(new Color(144, 238, 144));
        btn7.setEnabled(true);

        //Room Inventory
        ImageIcon rlogo = new ImageIcon(getClass().getResource("/Room.png"));
        Image rimg = rlogo.getImage();
        Image rscaledImg = rimg.getScaledInstance(140, 140, Image.SCALE_SMOOTH); // Set your desired width and height
        ImageIcon rscaledLogo = new ImageIcon(rscaledImg);

        JPanel container8 = new JPanel(new GridBagLayout());
        container8.setBackground(new Color(41,64,58));

        JPanel panel8 = new JPanel();
        panel8.setPreferredSize(new Dimension( 150, 150));
        panel8.setBackground(new Color(41,64,58));

        RoundedButton btn8 = new RoundedButton("", 20);
        btn8.setIcon(rscaledLogo);
        btn8.setFocusable(false);
        btn8.setBorder(BorderFactory.createEtchedBorder());
        btn8.setBackground(new Color(144, 238, 144));
        btn8.setEnabled(true);



        row1panel.add(container1, BorderLayout.CENTER);
        row1panel.add(container2, BorderLayout.CENTER);
        row1panel.add(container3, BorderLayout.CENTER);
        row1panel.add(container4, BorderLayout.CENTER);

        row2panel.add(container6, BorderLayout.CENTER);
        row2panel.add(container7, BorderLayout.CENTER);
        row2panel.add(container8, BorderLayout.CENTER);

        container1.add(panel1);
        container2.add(panel2);
        container3.add(panel3);
        container4.add(panel4);

        container6.add(panel6);
        container7.add(panel7);
        container8.add(panel8);


        panel1.add(btn1);
        panel2.add(btn2);
        panel3.add(btn3);
        panel4.add(btn4);

        panel6.add(btn6);
        panel7.add(btn7);
        panel8.add(btn8);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Furniture (connection);
            }
        });

       btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Electronics (connection);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Miscellaneous(connection);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Clean (connection);
            }
        });



        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KitchenItems(connection);
            }
        });

        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Laundry(connection);
            }
        });

        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Room(connection);
            }
        });



        frame.setVisible(true);
    }

}
