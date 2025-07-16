package studentwellnessdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:derby:Database/StudentWellnessDB;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(DB_URL);
    }
}
