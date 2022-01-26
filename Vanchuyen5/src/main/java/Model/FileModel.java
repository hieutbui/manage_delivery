package Model;


public class FileModel extends GoodsModel implements Document,Package{

    double trongluong;
    double cuocphi;

    public FileModel(int id)
    {
        super(id);
    }

    public FileModel(int id, String tenNguoigui, String tenNguoinhan,
              String thoigianGui, String thoigianNhan, double khoangcach, String diachiGui, String diachiNhan,double trongluong, String loaihang) {
        super(id,tenNguoigui,tenNguoinhan,thoigianGui,thoigianNhan,khoangcach,diachiGui,diachiNhan, loaihang);
        this.trongluong = trongluong;
    }

    public FileModel(int id, String tenNguoigui, String tenNguoinhan,
                     String thoigianGui, String thoigianNhan, double khoangcach, String diachiGui, String diachiNhan,double trongluong, String loaihang, double cuocphi) {
        super(id,tenNguoigui,tenNguoinhan,thoigianGui,thoigianNhan,khoangcach,diachiGui,diachiNhan, loaihang);
        this.trongluong = trongluong;
        this.cuocphi = cuocphi;
    }

    public FileModel() {
        super();
    }

    // phuong thuc get va set


    public double getTrongluong() {
        return trongluong;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public double getKhoangcach() {
        return super.getKhoangcach();
    }

    @Override
    public String getDiachiGui() {
        return super.getDiachiGui();
    }

    @Override
    public String getDiachiNhan() {
        return super.getDiachiNhan();
    }

    @Override
    public String getTenNguoigui() {
        return super.getTenNguoigui();
    }

    @Override
    public String getTenNguoinhan() {
        return super.getTenNguoinhan();
    }

    @Override
    public String getThoigianGui() {
        return super.getThoigianGui();
    }

    @Override
    public String getThoigianNhan() {
        return super.getThoigianNhan();
    }

    public double getCuocphi() {
        return cuocphi;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setDiachiGui(String diachiGui) {
        super.setDiachiGui(diachiGui);
    }

    @Override
    public void setDiachiNhan(String diachiNhan) {
        super.setDiachiNhan(diachiNhan);
    }

    @Override
    public void setKhoangcach(double khoangcach) {
        super.setKhoangcach(khoangcach);
    }

    @Override
    public void setTenNguoigui(String tenNguoigui) {
        super.setTenNguoigui(tenNguoigui);
    }

    @Override
    public void setTenNguoinhan(String tenNguoinhan) {
        super.setTenNguoinhan(tenNguoinhan);
    }

    @Override
    public void setThoigianGui(String thoigianGui) {
        super.setThoigianGui(thoigianGui);
    }

    @Override
    public void setThoigianNhan(String thoigianNhan) {
        super.setThoigianNhan(thoigianNhan);
    }

    public void setTrongluong(double trongluong) {
        this.trongluong = trongluong;
    }

    public void setCuocphi(double cuocphi) {
        this.cuocphi = cuocphi;
    }

    @Override
    public double tinhcuocDocument() {
        return cuocphi = 2000*getKhoangcach() + 12000;
    }

    @Override
    public double tinhcuocPackage() {
        return cuocphi = getKhoangcach()*2000 + trongluong*10000;
    }

}
