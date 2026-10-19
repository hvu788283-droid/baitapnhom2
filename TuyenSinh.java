import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class TuyenSinh {
    private final ArrayList<ThiSinh> danhSach = new ArrayList<>();

    // Chuc nang 1: Them thi sinh
    public void themThiSinh(ThiSinh thiSinh) {
    if (timTheoSoBaoDanh(thiSinh.getSoBaoDanh()) != null) {
        System.out.println("So bao danh da ton tai!");
        return;
    }

    if (thiSinh.soBaoDanh.contains("|")
            || thiSinh.hoTen.contains("|")
            || thiSinh.diaChi.contains("|")) {
        System.out.println("Thong tin khong duoc chua ky tu |");
        return;
    }

    danhSach.add(thiSinh);

    try {
        luuDanhSach();
        System.out.println("Da them va luu thi sinh vao file!");
    } catch (IOException e) {
        danhSach.remove(danhSach.size() - 1);
        System.out.println("Khong them duoc vi loi luu file: " + e.getMessage());
    }
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
    import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
Trần
private static final Path FILE_DANH_SACH = Paths.get("danhsach.txt");

// Ghi danh sach vao file
public void luuDanhSach() throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(
            FILE_DANH_SACH, StandardCharsets.UTF_8)) {

        for (ThiSinh ts : danhSach) {
            writer.write(
                ts.getKhoiThi() + "|" +
                ts.soBaoDanh + "|" +
                ts.hoTen + "|" +
                ts.diaChi + "|" +
                ts.mucUuTien
            );
            writer.newLine();
        }
    }
}

// Doc danh sach tu file
public void docDanhSach() throws IOException {
    if (!Files.exists(FILE_DANH_SACH)) {
        return;
    }

    ArrayList<ThiSinh> duLieu = new ArrayList<>();

    try (BufferedReader reader = Files.newBufferedReader(
            FILE_DANH_SACH, StandardCharsets.UTF_8)) {

        String dong;
        int soDong = 0;

        while ((dong = reader.readLine()) != null) {
            soDong++;

            if (dong.trim().isEmpty()) {
                continue;
            }

            String[] p = dong.split("\\|", -1);

            if (p.length != 5) {
                throw new IOException("Sai dinh dang tai dong " + soDong);
            }

            int uuTien;
            try {
                uuTien = Integer.parseInt(p[4]);
            } catch (NumberFormatException e) {
                throw new IOException("Sai muc uu tien tai dong " + soDong);
            }

            if (uuTien < 0 || p[1].trim().isEmpty()
                    || p[2].trim().isEmpty() || p[3].trim().isEmpty()) {
                throw new IOException("Du lieu khong hop le tai dong " + soDong);
            }

            for (ThiSinh ts : duLieu) {
                if (ts.getSoBaoDanh().equals(p[1])) {
                    throw new IOException("Trung so bao danh tai dong " + soDong);
                }
            }

            ThiSinh ts;
            switch (p[0]) {
                case "A":
                    ts = new ThiSinhKhoiA(p[1], p[2], p[3], uuTien);
                    break;
                case "B":
                    ts = new ThiSinhKhoiB(p[1], p[2], p[3], uuTien);
                    break;
                case "C":
                    ts = new ThiSinhKhoiC(p[1], p[2], p[3], uuTien);
                    break;
                default:
                    throw new IOException("Sai khoi thi tai dong " + soDong);
            }

            duLieu.add(ts);
        }
    }

    danhSach.clear();
    danhSach.addAll(duLieu);
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