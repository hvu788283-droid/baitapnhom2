public class ThiSinhKhoiC extends ThiSinh {
    public ThiSinhKhoiC(String soBaoDanh, String hoTen,
                        String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public String getKhoiThi() {
        return "C";
    }

    @Override
    public String getMonThi() {
        return "Van, Su, Dia";
    }
}