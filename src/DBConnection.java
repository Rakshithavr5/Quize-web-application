import java.sql.*;

public class DBConnection {

    // ✅ Update these according to your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/quizdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";      // Your MySQL username
    private static final String PASS = "root";          // Your MySQL password (empty if none)

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver (once)
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("⚠️ MySQL Driver not found: " + e.getMessage());
        }
        return conn;
    }

    // Optional: safely close connection
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Test connection standalone
    public static void main(String[] args) {
        Connection testConn = DBConnection.getConnection();
        if (testConn != null) {
            System.out.println("✅ Connection test successful!");
            closeConnection();
        } else {
            System.out.println("❌ Connection failed. Check username/password.");
        }
    }
}
