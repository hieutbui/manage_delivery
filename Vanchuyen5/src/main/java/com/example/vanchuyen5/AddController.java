package com.example.vanchuyen5;

import Model.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddController implements Initializable {

    @FXML
    private ComboBox<String> cbLoaihang;
    ObservableList<String> listCB = FXCollections.observableArrayList("Bưu kiện","Tài liệu");
    @FXML
    private Button btnAdd;
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
    private TextField tfDiachigui;
    @FXML
    private TextField tfDiachinhan;
    @FXML
    private DatePicker tfThoigiangui;
    @FXML
    private DatePicker tfThoigiannhan;

    @FXML
    private Stage btnHome;
    private Parent root;
    private Scene scene;


    public void switchToHomePageScene(ActionEvent event) throws IOException // BacktoHomePage
    {
        Parent root = FXMLLoader.load(getClass().getResource("homePageView.fxml"));
        btnHome = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        btnHome.setScene(scene);
        btnHome.show();
    }

    @FXML
    public void addDonhang(ActionEvent event) throws ClassNotFoundException, SQLException {
        // khai báo một mẫu để so sánh
        Pattern pattern;

        // kiểm tra ID nhập vào
        // ID là một dãy từ 1 đến 11 chữ số
        pattern = Pattern.compile("\\d{1,11}");
        if (!pattern.matcher(tfID.getText()).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Hãy nhập vào mã đơn hàng hợp lệ!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        // kiểm tra File có tồn tại với file trước đó hay không
        List<FileModel> listFileModels = new AddFileService().getListFile();
        for (FileModel file : listFileModels) {
            if (file.getId() == Integer.parseInt(tfID.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Mã đơn hàng bị trùng với một đơn hàng khác!", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
                return;
            }
        }

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

        // kiểm tra khoảng cách nhập vào phải từ 1 đến 30 ký tự
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

        if (cbLoaihang.getValue().equals("Bưu kiện")) {
            FileModel file = new FileModel(id, tenNguoigui, tenNguoinhan, ngayGui, ngayNhan, khoangcach, diachiGui, diachiNhan, trongluong,"Bưu kiện");
            file.setCuocphi(file.tinhcuocPackage());
            new AddFileService().add(file);
        }
        else if(cbLoaihang.getValue().equals("Tài liệu")) {
            trongluong = 0;
            FileModel document = new FileModel(id, tenNguoigui, tenNguoinhan, ngayGui, ngayNhan, khoangcach, diachiGui, diachiNhan, trongluong,"Tài liệu");
            document.setCuocphi(document.tinhcuocDocument());
            new AddFileService().add(document);

        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbLoaihang.setItems(listCB);
    }
}
