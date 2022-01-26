package Databases;

import java.sql.Connection;
import java.sql.DriverManager;

public class testConnect {
    public static final String connectionUrl = "jdbc:sqlserver://localhost;"
            + "databaseName=Customer;user=sa;password=12345";

    public static Connection getDBConnect() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println("Chua co Driver! " + e.toString());
        }

        try {
            conn = DriverManager.getConnection(connectionUrl);
            return conn;
        } catch (Exception e) {
            System.out.println("Loi connect" + e.toString());
        }
        return null;
    }
}
