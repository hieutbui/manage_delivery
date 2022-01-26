public class BuuKien extends DonHang{
    private int trongLuong;

    public BuuKien(String tenNguoiGui, String tenNguoiNhan, String diaChiGui, String diaChiNhan, int id, String tgianGui, String tgianNhan, int khoangCach, int trongLuong) {
        super(tenNguoiGui, tenNguoiNhan, diaChiGui, diaChiNhan, id, tgianGui, tgianNhan, khoangCach);
        this.trongLuong = trongLuong;
    }

    public void inThongTin(){
        System.out.println("1 buu kien co: " + "Thoi gian nhan: " + this.tgianNhan + ", Ma don hang: " + this.id + ", Trong luong: " + this.trongLuong);
    }

    @Override
    public int tinhCuoc() {
        return (super.khoangCach * 2000) + (trongLuong * 10000);
    }
}
