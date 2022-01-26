package Databases;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class databaseTest {
    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("12345");
        ds.setServerName("DESKTOP-D8D19OC");
        ds.setPortNumber(1433); //port tcp/ip
        ds.setDatabaseName("master");

        try (Connection connection = ds.getConnection()) {
            System.out.println(connection.getMetaData());
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
