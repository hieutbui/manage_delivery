public class TaiLieu extends DonHang{

    public TaiLieu(String tenNguoiGui, String tenNguoiNhan, String diaChiGui, String diaChiNhan, int id, String tgianGui, String tgianNhan, int khoangCach) {
        super(tenNguoiGui, tenNguoiNhan, diaChiGui, diaChiNhan, id, tgianGui, tgianNhan, khoangCach);
    }

    public void inThongTin(){
        System.out.println("1 tai lieu co: " + "Thoi gian nhan: " + this.tgianNhan + ", Ma don hang: " + this.id);
    }
    @Override
    public int tinhCuoc() {
        return (super.khoangCach * 2000) + 12000;
    }
}
