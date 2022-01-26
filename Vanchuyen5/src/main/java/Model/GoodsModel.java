package Model;

import java.util.Date;

abstract public class GoodsModel {
    private int id;
    private String tenNguoigui;
    private String tenNguoinhan;
    private String diachiGui;
    private String diachiNhan;
    private String thoigianGui;
    private String thoigianNhan;
    private double khoangcach;
    private String loaihang;

    GoodsModel(int id, String tenNguoigui, String tenNguoinhan, String thoigianGui,
               String thoigianNhan, double khoangcach, String diachiGui, String diachiNhan, String loaihang)
    {
        this.id = id;
        this.tenNguoigui = tenNguoigui;
        this.tenNguoinhan = tenNguoinhan;
        this.diachiGui = diachiGui;
        this.diachiNhan = diachiNhan;
        this.thoigianGui = thoigianGui;
        this.thoigianNhan = thoigianNhan;
        this.khoangcach = khoangcach;
        this.loaihang = loaihang;
    }

    protected GoodsModel() {
    }

    public GoodsModel(int id) {
        this.id = id;
    }

    // Các phương thức get và set


    public int getId() {
        return id;
    }

    public String getTenNguoigui() {
        return tenNguoigui;
    }

    public double getKhoangcach() {
        return khoangcach;
    }

    public String getDiachiGui() {
        return diachiGui;
    }

    public String getDiachiNhan() {
        return diachiNhan;
    }

    public String getTenNguoinhan() {
        return tenNguoinhan;
    }

    public String getThoigianGui() {
        return thoigianGui;
    }

    public String getThoigianNhan() {
        return thoigianNhan;
    }

    public String getLoaihang() {
        return loaihang;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDiachiGui(String diachiGui) {
        this.diachiGui = diachiGui;
    }

    public void setDiachiNhan(String diachiNhan) {
        this.diachiNhan = diachiNhan;
    }

    public void setKhoangcach(double khoangcach) {
        this.khoangcach = khoangcach;
    }

    public void setTenNguoigui(String tenNguoigui) {
        this.tenNguoigui = tenNguoigui;
    }

    public void setTenNguoinhan(String tenNguoinhan) {
        this.tenNguoinhan = tenNguoinhan;
    }

    public void setThoigianGui(String thoigianGui) {
        this.thoigianGui = thoigianGui;
    }

    public void setThoigianNhan(String thoigianNhan) {
        this.thoigianNhan = thoigianNhan;
    }

    public void setLoaihang(String loaihang) {
        this.loaihang = loaihang;
    }




}
