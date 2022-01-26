module com.example.vanchuyen5 {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;
    requires mssql.jdbc;
    opens Model to javafx.base ;
    opens com.example.vanchuyen5 to javafx.fxml;
    exports com.example.vanchuyen5;
}