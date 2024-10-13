import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        // Create a MySQL connection (replace with your own connection details)
        try {
            Connection connection = MySQLConnection.getConnection();

            // Pass the connection to the Front1 constructor
            new Login(connection);
        }
        catch (Exception e){
            System.out.println(e);
            try {
                // Handle InterruptedException
                Thread.sleep(100000);
            } catch (InterruptedException ie) {
                System.out.println("Sleep interrupted: " + ie);
            }
        }
        // This will now work since connection is passed
    }
}