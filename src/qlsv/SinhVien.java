package qlsv;

import javax.swing.*;
// import java.text.SimpleDateFormat;

import util.IO_Interface;
import util.NgayThangNam;
import java.util.HashMap;
import java.util.Map;

public abstract class SinhVien implements IO_Interface {
    protected int maSV;
    protected String hoTen;
    protected NgayThangNam ngaySinh;
    protected int namHoc;
    protected double diemDV;
    protected HashMap<Integer, Double> kqHt;

    public SinhVien() {
        this.kqHt = new HashMap<Integer, Double>();
    }

    public SinhVien(int maSV, String hoTen, NgayThangNam ngaySinh, int namHoc,
            double diemDV, HashMap<Integer, Double> kqHt) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.namHoc = namHoc;
        this.diemDV = diemDV;
        this.kqHt = (HashMap<Integer, Double>) kqHt.clone();
    }

    public SinhVien(SinhVien sinhvien) {
        this.maSV = sinhvien.maSV;
        this.hoTen = sinhvien.hoTen;
        this.ngaySinh = sinhvien.ngaySinh;
        this.namHoc = sinhvien.namHoc;
        this.diemDV = sinhvien.diemDV;
        this.kqHt = (HashMap<Integer, Double>) sinhvien.kqHt.clone();
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public NgayThangNam getNgaySinh() {
        return ngaySinh;
    }

    public String getNgaySinhString() {
        return ngaySinh.output();
    }

    public void setNgaySinh(NgayThangNam ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public double getDiemDV() {
        return diemDV;
    }

    public void setDiemDV(double diemDV) {
        this.diemDV = diemDV;
    }

    public HashMap<Integer, Double> getKqHt() {
        return kqHt;
    }

    public void setKqHt(HashMap<Integer, Double> kqHt) {
        this.kqHt = (HashMap<Integer, Double>) kqHt.clone();
    }

    public int input() {
        // TODO Auto-generated method stub
        boolean check = false;
        String str;
        int maSVTemp = 0;
        String hoTenTemp = null;
        NgayThangNam ngaySinhTemp = new NgayThangNam();
        int namHocTemp = 0;
        double diemDVTemp = 0;
        do {
            check = false;
            try {
                str = JOptionPane.showInputDialog(null, "Nhap Ma so Sinh Vien: ", "0");
                if (str == null)
                    return -1;
                maSVTemp = Integer.parseInt(str);
                check = true;
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Nhap Khong Dung So ");
            }
        } while (maSVTemp < 0 || check == false);

        do {
            str = JOptionPane.showInputDialog("Nhap Ho ten Sinh Vien: ");
            if (str == null)
                return -1;
            hoTenTemp = str;
        } while (hoTenTemp.length() == 0 || hoTenTemp.trim().isEmpty());

        if (ngaySinhTemp.input() == -1)
            return -1;

        do {
            check = false;
            try {
                str = JOptionPane.showInputDialog(null, "Nhap Nam vao hoc: ", "0");
                if (str == null)
                    return -1;
                namHocTemp = Integer.parseInt(str);
                check = true;
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Nhap Khong Dung So");
            }
        } while (namHocTemp < 0 || check == false);

        do {
            try {
                check = false;
                str = JOptionPane.showInputDialog(null, "Nhap Diem dau vao: ", "0");
                if (str == null)
                    return -1;
                diemDVTemp = Double.parseDouble(str);
                check = true;
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Nhap Khong Dung So");
            }
        } while (diemDVTemp < 0 || check == false);
        JOptionPane.showMessageDialog(null, "Nhap Danh sach ket qua hoc tap");

        if (kqHtInput(hoTenTemp) == -1)
            return -1;

        this.maSV = maSVTemp;
        this.hoTen = hoTenTemp;
        this.ngaySinh = new NgayThangNam(ngaySinhTemp);
        this.diemDV = diemDVTemp;
        this.namHoc = namHocTemp;

        Khoa.increaseSL(this.namHoc);

        return 0;
    }

    public int kqHtInput(String ht) {
        int cont = JOptionPane.DEFAULT_OPTION;
        String str;
        do {
            boolean error = false;
            int key = 0;
            double value = 0;
            do {
                error = false;
                try {
                    str = JOptionPane.showInputDialog(null, kqHtOuput() + "\nNhap hoc ki: ", key);
                    if (str == null)
                        return -1;
                    key = Integer.parseInt(str);
                    error = false;
                } catch (NumberFormatException e) {
                    error = true;
                }

                if (key <= 0)
                    error = true;

                if (error == true)
                    JOptionPane.showMessageDialog(null, "Nhap khong hop le ");
            } while (error == true);

            do {
                error = false;
                try {
                    str = JOptionPane.showInputDialog(null,
                            String.format("%s\nNhap diem trung binh hoc ky %d: ", kqHtOuput(), key),
                            "0");
                    if (str == null)
                        return -1;
                    value = Double.parseDouble(str);
                    error = false;
                } catch (NumberFormatException e) {
                    error = true;
                }

                if (value < 0 || value > 10)
                    error = true;

                if (error == true)
                    JOptionPane.showMessageDialog(null, "Nhap khong hop le ");
            } while (error == true);
            this.kqHt.put(key, value);

            cont = JOptionPane.showConfirmDialog(null,
                    String.format("Ban co muon tiep tuc nhap diem cho %s?\n----------\n%s", ht, kqHtOuput()),
                    "Xac nhan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } while (cont == JOptionPane.YES_OPTION);
        return 0;
    }

    public String kqHtOuput() {
        String res = "Hoc Ky | Diem\n-------------\n";
        for (Map.Entry d : kqHt.entrySet()) {
            res += String.format("%6d | %4.2f", d.getKey(), d.getValue());
            res += "\n";
        }
        return res + "-------------";
    }

    // abstract public String row();

    public double getDTB_SVTaiChuc(int tenHocKy) {
        if (isSVTaiChuc()) {
            return getKqHt().get(tenHocKy) == null ? 0 : getKqHt().get(tenHocKy);
        }
        return -1;
    }

    public String output() {
        // TODO Auto-generated method stub
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String res = "";
        res += "Ma so Sinh vien: " + getMaSV() + "\n";
        res += "Ho ten Sinh vien: " + getHoTen() + " Tai Chuc: " + isSVTaiChuc() + "\n";
        res += "Ngay sinh: " + getNgaySinh().output() + "\n";
        res += "Nam vao hoc: " + getNamHoc() + "\n";
        res += "Diem dau vao: " + getDiemDV() + "\n";
        res += "[Danh sach ket qua]\n" + kqHtOuput() + "\n";
        return res;
    }

    public boolean isSVTaiChuc() {
        if (this instanceof SinhVienTaiChuc)
            return true;
        return false;
    }
}