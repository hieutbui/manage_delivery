package Databases;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection conn = testConnect.getDBConnect();
        if(conn != null)
        {
            System.out.println("Connect thanh cong");
        }
        else
        {
            System.out.println("Connect that bai");
        }
    }
}
