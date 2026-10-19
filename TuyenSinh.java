import java.util.ArrayList;
import java.util.Scanner;

public class TuyenSinh {
    private final ArrayList<ThiSinh> danhSach = new ArrayList<>();

    // Chuc nang 1: Them thi sinh
    public void themThiSinh(ThiSinh thiSinh) {
        if (timTheoSoBaoDanh(thiSinh.getSoBaoDanh()) != null) {
            System.out.println("So bao danh da ton tai!");
            return;
        }

        danhSach.add(thiSinh);
        System.out.println("Them thi sinh thanh cong!");
    }

    // Chuc nang 2: Hien thi danh sach
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }

        for (ThiSinh thiSinh : danhSach) {
            thiSinh.hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    // Chuc nang 3: Tim theo so bao danh
    public ThiSinh timTheoSoBaoDanh(String soBaoDanh) {
        for (ThiSinh thiSinh : danhSach) {
            if (thiSinh.getSoBaoDanh().equals(soBaoDanh)) {
                return thiSinh;
            }
        }

        return null;
    }

    // Nhap thong tin va tao doi tuong theo khoi thi
    public void nhapThiSinh(Scanner scanner) {
        System.out.print("Nhap khoi thi (A/B/C): ");
        String khoi = scanner.nextLine().trim().toUpperCase();

        if (!khoi.equals("A") && !khoi.equals("B") && !khoi.equals("C")) {
            System.out.println("Khoi thi khong hop le!");
            return;
        }

        System.out.print("Nhap so bao danh: ");
        String sbd = scanner.nextLine().trim();

        System.out.print("Nhap ho ten: ");
        String hoTen = scanner.nextLine().trim();

        System.out.print("Nhap dia chi: ");
        String diaChi = scanner.nextLine().trim();

        System.out.print("Nhap muc uu tien (so nguyen khong am): ");
        int uuTien;

        try {
            uuTien = Integer.parseInt(scanner.nextLine().trim());
            if (uuTien < 0) {
                System.out.println("Muc uu tien khong duoc am!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Muc uu tien phai la so nguyen!");
            return;
        }

        if (sbd.isEmpty() || hoTen.isEmpty() || diaChi.isEmpty()) {
            System.out.println("Khong duoc de trong thong tin!");
            return;
        }

        ThiSinh thiSinh;

        switch (khoi) {
            case "A":
                thiSinh = new ThiSinhKhoiA(sbd, hoTen, diaChi, uuTien);
                break;
            case "B":
                thiSinh = new ThiSinhKhoiB(sbd, hoTen, diaChi, uuTien);
                break;
            default:
                thiSinh = new ThiSinhKhoiC(sbd, hoTen, diaChi, uuTien);
        }

        themThiSinh(thiSinh);
    }

    // Chay chuong trinh
    public static void main(String[] args) {
        TuyenSinh quanLy = new TuyenSinh();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n===== QUAN LY THI SINH =====");
                System.out.println("1. Them moi thi sinh");
                System.out.println("2. Hien thi danh sach thi sinh");
                System.out.println("3. Tim theo so bao danh");
                System.out.println("0. Thoat");
                System.out.print("Chon chuc nang: ");

                String luaChon = scanner.nextLine().trim();

                switch (luaChon) {
                    case "1":
                        quanLy.nhapThiSinh(scanner);
                        break;

                    case "2":
                        quanLy.hienThiDanhSach();
                        break;

                    case "3":
                        System.out.print("Nhap so bao danh can tim: ");
                        String sbd = scanner.nextLine().trim();

                        ThiSinh ketQua = quanLy.timTheoSoBaoDanh(sbd);

                        if (ketQua == null) {
                            System.out.println("Khong tim thay thi sinh!");
                        } else {
                            ketQua.hienThiThongTin();
                        }
                        break;

                    case "0":
                        System.out.println("Da thoat chuong trinh.");
                        return;

                    default:
                        System.out.println("Lua chon khong hop le!");
                }
            }
        }
    }
}