// Lê Duy Anh Dũng làm
package com.example.vanchuyen5;

import Databases.testConnect;
import Model.FileModel;

import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddFileService {

    // checked
    public boolean add(FileModel file) throws ClassNotFoundException, SQLException {

        Connection connection = testConnect.getDBConnect();
        String query = "INSERT INTO fileTB(id,tenNguoigui,tenNguoinhan,thoigianGui,thoigianNhan,khoangcach,diachiGui" +
                ",diachiNhan,trongluong,loaihang,tinhcuoc) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, file.getId());
        preparedStatement.setString(2, file.getTenNguoigui());
        preparedStatement.setString(3, file.getTenNguoinhan());
        preparedStatement.setString(4,file.getThoigianGui());
        preparedStatement.setString(5,file.getThoigianNhan());
        preparedStatement.setDouble(6,file.getKhoangcach());
        preparedStatement.setString(7,file.getDiachiGui());
        preparedStatement.setString(8,file.getDiachiNhan());
        preparedStatement.setDouble(9,file.getTrongluong());
        preparedStatement.setString(10,file.getLoaihang());
        preparedStatement.setDouble(11,file.getCuocphi());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }

    // checked
    public boolean del(int id) throws ClassNotFoundException, SQLException {

        Connection connection = testConnect.getDBConnect();
        String query = "SELECT * FROM fileTB WHERE id='" + id + "';";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            query = "DELETE FROM fileTB WHERE id='" + id + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();
        return true;
    }

    public boolean update(int id ,String tenNguoigui, String tenNguoinhan, String thoigianGui, String thoigianNhan,
                          double khoangcach, String diachiGui, String diachiNhan, double trongluong, String loaihang)
            throws ClassNotFoundException, SQLException {

        Connection connection = testConnect.getDBConnect();
        PreparedStatement preparedStatement;

        String query = "UPDATE FileTB " + "set tenNguoigui = N'" + tenNguoigui + "',"
                + "tenNguoinhan = N'" + tenNguoinhan + "',"
                + "thoigianGui ='" + thoigianGui + "',"
                + "thoigianNhan ='" + thoigianNhan + "',"
                + "khoangcach ='" + khoangcach + "',"
                + "diachiGui =N'" + diachiGui + "',"
                + "diachiNhan =N'" + diachiNhan + "',"
                + "trongluong =" + trongluong + ","
                + "loaihang =N'" + loaihang + "' WHERE id =" + id;


        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }


    // checked
    public List<FileModel> getListFile() throws ClassNotFoundException, SQLException {
        List<FileModel> list = new ArrayList<>();

        Connection connection = testConnect.getDBConnect();
        String query = "SELECT * FROM FileTB";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            FileModel file = new FileModel(
                    rs.getInt("id"),
                    rs.getString("tenNguoigui"),
                    rs.getString("tenNguoinhan"),
                    rs.getString("thoigianGui"),
                    rs.getString("thoigianNhan"),
                    rs.getDouble("khoangcach"),
                    rs.getString("diachiGui"),
                    rs.getString("diachiNhan"),
                    rs.getDouble("trongluong"),
                    rs.getString("loaihang"),
                    rs.getDouble("tinhcuoc")
            );
            list.add(file);
        }
        preparedStatement.close();
        connection.close();
        return list;
    }

    // Search ID khi nhập vào thời gian Nhận ( Bùi Trung Hiếu làm )
    public List<FileModel> getListID(String thoigianNhan) throws ClassNotFoundException, SQLException {
        List<FileModel> listID = new ArrayList<>();

        Connection connection = testConnect.getDBConnect();
        String query = "SELECT * FROM FileTB WHERE thoigianNhan='" + thoigianNhan + "'";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            FileModel file = new FileModel(
                    rs.getInt("id"),
                    rs.getString("tenNguoigui"),
                    rs.getString("tenNguoinhan"),
                    rs.getString("thoigianGui"),
                    rs.getString("thoigianNhan"),
                    rs.getDouble("khoangcach"),
                    rs.getString("diachiGui"),
                    rs.getString("diachiNhan"),
                    rs.getDouble("trongluong"),
                    rs.getString("loaihang"),
                    rs.getDouble("tinhcuoc")
            );
            listID.add(file);
        }
        preparedStatement.close();
        connection.close();
        return listID;
    }

}
