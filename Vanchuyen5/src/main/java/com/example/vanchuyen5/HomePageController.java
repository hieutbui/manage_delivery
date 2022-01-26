// Lê Duy Anh Dũng code chức năng thêm, sửa, xóa, tìm kiếm theo ngày nhận
// Lê Duy Anh Dũng code chức năng hiển thị thông tin lên bảng, tính cước phí
// Bùi Trung Hiếu code hiển thị đã vân chuyển trong khoảng thời gian nhận vào
// Bùi Trung Hiếu code tính doanh thu trong khoảng thời gian nhập vào
package com.example.vanchuyen5;

import Model.FileModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class HomePageController implements Initializable{

    @FXML
    private DatePicker tfthoigian3;
    @FXML
    private Label txtDoanhthu;
    @FXML
    private DatePicker tfthoigian4;
    @FXML
    private DatePicker tfThoigian2;
    @FXML
    private DatePicker tfThoigian1;
    @FXML
    private Button tfSearch1;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<FileModel> tvHome;
    @FXML
    private TableColumn colKhoangcach;
    @FXML
    private TableColumn colTennguoinhan;
    @FXML
    private TableColumn colLoaihang;
    @FXML
    private TableColumn colDiachigui;
    @FXML
    private TableColumn colDiachinhan;
    @FXML
    private TableColumn colThoigiangui;
    @FXML
    private TableColumn colThoigiannhan;
    @FXML
    private TableColumn colTrongluong;
    @FXML
    private TableColumn colPhicuoc;
    @FXML
    private TableColumn colTennguoiGui;
    @FXML
    private TableColumn colID;

    @FXML
    private ObservableList<FileModel> listValueTableView;
    private List<FileModel> listFile;

    // Hiển thị thông tin tài liệu
    public void showFile() throws ClassNotFoundException, SQLException {
        listFile = new AddFileService().getListFile();
        listValueTableView = FXCollections.observableArrayList(listFile);

        // Thiết lập các cột trong tableview - truyền dữ liệu từ db lên trên bảng
        colID.setCellValueFactory(new PropertyValueFactory<FileModel, Integer>("id"));
        colTennguoiGui.setCellValueFactory(new PropertyValueFactory<FileModel, String>("tenNguoigui"));
        colTennguoinhan.setCellValueFactory(new PropertyValueFactory<FileModel, String>("tenNguoinhan"));
        colThoigiangui.setCellValueFactory(new PropertyValueFactory<FileModel, String>("thoigianGui"));
        colThoigiannhan.setCellValueFactory(new PropertyValueFactory<FileModel, String>("thoigianNhan"));
        colKhoangcach.setCellValueFactory(new PropertyValueFactory<FileModel, String>("khoangcach"));
        colDiachigui.setCellValueFactory(new PropertyValueFactory<FileModel, String>("diachiGui"));
        colDiachinhan.setCellValueFactory(new PropertyValueFactory<FileModel, String>("diachiNhan"));
        colTrongluong.setCellValueFactory(new PropertyValueFactory<FileModel, String>("trongluong"));
        colLoaihang.setCellValueFactory(new PropertyValueFactory<FileModel, String>("loaihang"));
        colPhicuoc.setCellValueFactory(new PropertyValueFactory<FileModel, String>("cuocphi"));

        tvHome.setItems(listValueTableView);


    }



    // Bấm vào ADD thì hiển thị giao diện thêm dữ liệu
    public void addFile() throws ClassNotFoundException, SQLException, IOException {
        Parent home = FXMLLoader.load(getClass().getResource("AddView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(home, 550, 550));
        stage.setResizable(false);
        stage.showAndWait();
        showFile();
    }

    //Chọn ID cần xóa rồi bấm Del thì xóa dữ liệu trên bảng
    public void delFile() throws ClassNotFoundException, SQLException {
        FileModel file = tvHome.getSelectionModel().getSelectedItem();

        if (file == null) {
            Alert alert = new Alert(AlertType.WARNING, "Chọn đơn hàng bạn muốn xóa!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Bạn có chắc chắn muốn xóa đơn hàng này?", ButtonType.YES,
                    ButtonType.NO);
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.NO) {
                return;
            } else {
                new AddFileService().del(file.getId());
            }
        }

        showFile();
    }


    //Bấm vào thì hiện giao diện Update
    public void updateFile() throws IOException, ClassNotFoundException, SQLException {
        // lấy ra đơn hàng cần update
        FileModel file = tvHome.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateView.fxml"));
        Parent home = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(home,600,600));
        UpdateController updateFile = loader.getController();

        // bắt lỗi trường hợp không hợp lệ
        if(updateFile == null) return;
        if(file == null) {
            Alert alert = new Alert(AlertType.WARNING, "Chọn đơn hàng cần update !", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        updateFile.setFileModel(file);

        stage.setResizable(false);
        stage.showAndWait();
        showFile();
    }


    // Tìm kiếm theo địa chỉ người nhận
    public void searchFile() {
        ObservableList<FileModel> listValueTableView_tmp = null;
        String keySearch = tfSearch.getText();

        // nếu không nhập gì -> thông báo lỗi;
        if (keySearch.length() == 0) {
            tvHome.setItems(listValueTableView);
            Alert alert = new Alert(AlertType.WARNING, "Hãy nhập vào thông tin cần tìm kiếm!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        int index = 0;
        List<FileModel> listFilesSearch = new ArrayList<>();
        for (FileModel file : listFile) {
            if (file.getDiachiNhan().contains(keySearch)) {
                listFilesSearch.add(file);
                index++;
            }
        }
        listValueTableView_tmp = FXCollections.observableArrayList(listFilesSearch);
        tvHome.setItems(listValueTableView_tmp);

        //nếu không tìm thấy thông tin tìm kiếm -> thông báo tới người dùng không tìm thấy
        if (index == 0) {
            tvHome.setItems(listValueTableView); // hiển thị toàn bộ thông tin
            Alert alert = new Alert(AlertType.INFORMATION, "Không tìm thấy thông tin!", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

// hiển thị đơn hàng đã giao trong khoảng thời gian ( Bùi Trung Hiếu làm )
    public void SearchByTime() throws SQLException, ClassNotFoundException {
        ObservableList<FileModel> listValueTableView_tmp = null;
        LocalDate thoigian1 = tfThoigian1.getValue();
        LocalDate thoigian2 = tfThoigian2.getValue();
        List<FileModel> ListAll = new ArrayList<>();
        for (LocalDate date = thoigian1; date.isBefore(thoigian2.plusDays(1)); date = date.plusDays(1)){
            String datePicker = String.valueOf(date);
            List<FileModel> list = new AddFileService().getListID(datePicker);
            ListAll.addAll(list);
            /*
            for (FileModel filemodel : list){
                ListAll.add(filemodel);
            }
            */
        }
        listValueTableView_tmp = FXCollections.observableArrayList(ListAll);
        tvHome.setItems(listValueTableView_tmp);
    }

// Tính toán doanh thu thu được trong khoảng thời gian nhập vào ( Bùi Trung Hiếu làm )
public void TinhDoanhThu() throws SQLException, ClassNotFoundException {
    LocalDate thoigian3 = tfthoigian3.getValue();
    LocalDate thoigian4 = tfthoigian4.getValue();
    List<FileModel> ListAll = new ArrayList<>();
    double doanhThu = 0;
    for (LocalDate date = thoigian3; date.isBefore(thoigian4.plusDays(1)); date = date.plusDays(1)){
        String datePicker = String.valueOf(date);
        List<FileModel> list = new AddFileService().getListID(datePicker);
        ListAll.addAll(list);
        /*
        for (FileModel filemodel : list){
            ListAll.add(filemodel);
        }
         */
    }
    for (FileModel fileModel : ListAll){
        doanhThu += fileModel.getCuocphi(); //Lay cuoc cua filemodel
    }
    // tra ve doanhThu
    txtDoanhthu.setText(String.valueOf(doanhThu));
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {
        try {
            showFile();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
