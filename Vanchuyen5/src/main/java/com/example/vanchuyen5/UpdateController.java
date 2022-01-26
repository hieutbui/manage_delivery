package com.example.vanchuyen5;

import Model.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.SQLException;

public class UpdateController implements Initializable {

    @FXML
    private ComboBox<String> cbLoaihang;
    ObservableList<String> listCB = FXCollections.observableArrayList("Bưu kiện","Tài liệu");
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfKg;
    @FXML
    private TextField tfTennguoigui;
    @FXML
    private TextField tfTennguoinhan;
    @FXML
    private TextField tfKhoangcach;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfDiachigui;
    @FXML
    private TextField tfDiachinhan;
    @FXML
    private DatePicker tfThoigiangui;
    @FXML
    private DatePicker tfThoigiannhan;

    private FileModel file;

    @FXML
    private Stage btnHome;
    private Parent root;
    private Scene scene;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbLoaihang.setItems(listCB);
    }

    public void switchToHomePageScene(ActionEvent event) throws IOException // BacktoHomePage
    {
        Parent root = FXMLLoader.load(getClass().getResource("homePageView.fxml"));
        btnHome = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        btnHome.setScene(scene);
        btnHome.show();
    }


    public FileModel getNhanKhauModel() {
            return file;
        }


        public void setFileModel(FileModel file) throws ClassNotFoundException, SQLException {
            this.file = file;
            tfID.setText(Integer.toString(file.getId()));
            tfTennguoigui.setText(file.getTenNguoigui());
            tfTennguoinhan.setText(file.getTenNguoinhan());
            tfDiachigui.setText(file.getDiachiGui());
            tfDiachinhan.setText(file.getDiachiNhan());
            tfKhoangcach.setText(Double.toString(file.getKhoangcach()));
            tfKg.setText(Double.toString(file.getTrongluong()));
        }

    @FXML
    public void UpdateDonhang(ActionEvent event) throws ClassNotFoundException, SQLException {
        // khai báo một mẫu để so sánh
        Pattern pattern;


        // kiểm tra địa chỉ nhận và địa địa chỉ gửi nhập vào
        // địa chỉ nhập vào là một chuỗi từ 1 đến 30 ký tự
        if (tfDiachigui.getText().length() >= 30 || tfDiachigui.getText().length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào 1 địa chỉ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (tfDiachinhan.getText().length() >= 30 || tfDiachinhan.getText().length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào 1 địa chỉ hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        // kiểm tra tên người gửi và người nhận nhập vào
        // tên người gửi và người nhận là một chuỗi từ 1 tới 30 ký tự
        if (tfTennguoigui.getText().length() >= 30 || tfDiachigui.getText().length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào tên người gửi hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (tfTennguoinhan.getText().length() >= 30 || tfTennguoinhan.getText().length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào tên người nhận hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        // kiểm tra khoảng cách nhập vào
        if (tfKhoangcach.getText().length() >= 30 || tfKhoangcach.getText().length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào khoảng cách hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }


        // get the date picker value
        LocalDate thoigianGui = tfThoigiangui.getValue();
        LocalDate thoigianNhan = tfThoigiannhan.getValue();
        String ngayGui = String.valueOf(thoigianGui);
        String ngayNhan = String.valueOf(thoigianNhan);

        // ghi nhận tất cả các giá trị nếu chúng hợp lệ
        int id = Integer.parseInt(tfID.getText());
        String tenNguoigui = tfTennguoigui.getText();
        String tenNguoinhan = tfTennguoinhan.getText();
        String diachiGui = tfDiachigui.getText();
        String diachiNhan = tfDiachinhan.getText();
        double khoangcach = Double.parseDouble(tfKhoangcach.getText());
        double trongluong = Double.parseDouble(tfKg.getText());

        if (cbLoaihang.getValue() == "Bưu kiện") {

            new AddFileService().update(id,tenNguoigui,tenNguoinhan,ngayGui,ngayNhan,khoangcach,diachiGui,diachiNhan,trongluong,"Bưu kiện");

        }
        else if(cbLoaihang.getValue() == "Tài liệu") {
            trongluong = 0;
            new AddFileService().update(id,tenNguoigui,tenNguoinhan,ngayGui,ngayNhan,khoangcach,diachiGui,diachiNhan,trongluong,"Tài liệu");
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}



