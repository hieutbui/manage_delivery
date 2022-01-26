import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        DonHang don1 = new TaiLieu("abc", "def", "hanoi",
                "laocai", 123, "2015-05-14", "2015-05-24", 20 );

        DonHang don2 = new BuuKien("abc", "def", "hanoi",
                "laocai", 456, "2015-05-18", "2015-05-28", 20 , 50);

        DonHang don3 = new TaiLieu("abc", "def", "hanoi",
                "laocai", 789, "2015-05-15", "2015-05-25", 20 );

        DonHang don4 = new TaiLieu("abc", "def", "hanoi",
                "laocai", 102, "2015-05-16", "2015-05-26", 20 );

        DonHang don5 = new BuuKien("abc", "def", "hanoi",
                "laocai", 345, "2015-05-10", "2015-05-20", 20 ,100);

        DonHang don6 = new TaiLieu("abc", "def", "hanoi",
                "laocai", 369, "2015-05-20", "2015-05-30", 20 );

        List<DonHang> danhSachDonHang = new ArrayList<>();
        danhSachDonHang.add(don1);
        danhSachDonHang.add(don2);
        danhSachDonHang.add(don3);
        danhSachDonHang.add(don4);
        danhSachDonHang.add(don5);
        danhSachDonHang.add(don6);
        LocalDate startDate = LocalDate.parse("2015-05-22");
        LocalDate endDate = LocalDate.parse("2015-05-29");
        List<DonHang> searchList = new ArrayList<>();
        int doanhThu = 0;
        //duyet tu ngay bat dau den ngay ket thuc ma nguoi dung nhap
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)){
            String convert = String.valueOf(date);
            //duyet trong danh sach don hang
            for (DonHang check : danhSachDonHang){
                if (check.tgianNhan.equals(convert)){
                    searchList.add(check);
                    doanhThu += check.tinhCuoc();
                }
            }
        }
        System.out.println("Thong ke tư ngay: " + startDate + " den ngay: " + endDate);
        for (DonHang thongTin : searchList){
            thongTin.inThongTin();
        }
        System.out.println();
        System.out.println("Doanh thu tu ngay: " + startDate + " den ngay: " + endDate);
        System.out.println("\t" + doanhThu);
    }
}

