import java.sql.Connection;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = MySQLConnection.getConnection();
                if (connection == null) {
                    System.out.println("Database connection unavailable. Starting the app in offline mode.");
                }
                new Login(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
