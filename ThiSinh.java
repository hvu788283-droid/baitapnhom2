public abstract class ThiSinh {
    protected String soBaoDanh;
    protected String hoTen;
    protected String diaChi;
    protected int mucUuTien;

    public ThiSinh(String soBaoDanh, String hoTen,
                   String diaChi, int mucUuTien) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.mucUuTien = mucUuTien;
    }

    public String getSoBaoDanh() {
        return soBaoDanh;
    }

    public abstract String getKhoiThi();

    public abstract String getMonThi();

    public void hienThiThongTin() {
        System.out.println("So bao danh: " + soBaoDanh);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Muc uu tien: " + mucUuTien);
        System.out.println("Khoi thi: " + getKhoiThi());
        System.out.println("Mon thi: " + getMonThi());
    }
}