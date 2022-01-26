abstract public class DonHang {
    protected String tenNguoiGui;
    protected String tenNguoiNhan;
    protected String diaChiGui;
    protected String diaChiNhan;
    protected int id;
    protected String tgianGui;
    protected String tgianNhan;
    protected int khoangCach;

    public DonHang(){
        this.tenNguoiGui = "";
        this.tenNguoiNhan = "";
        this.diaChiGui = "";
        this.diaChiNhan = "";
        this.id = 0;
        this.tgianGui = "";
        this.tgianNhan = "";
        this.khoangCach = 0;
    }

    public DonHang(String tenNguoiGui, String tenNguoiNhan, String diaChiGui, String diaChiNhan, int id,
                   String tgianGui, String tgianNhan, int khoangCach){
        this.tenNguoiGui = tenNguoiGui;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChiGui = diaChiGui;
        this.diaChiNhan = diaChiNhan;
        this.id = id;
        this.tgianGui = tgianGui;
        this.tgianNhan = tgianNhan;
        this.khoangCach = khoangCach;
    }
    abstract public void inThongTin();
    abstract public int tinhCuoc();

}
