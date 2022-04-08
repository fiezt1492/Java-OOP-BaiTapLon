package qlsv;

import util.NgayThangNam;
import java.util.HashMap;

public class SinhVienChinhQuy extends SinhVien {
	public SinhVienChinhQuy() {
		super();
	}

	public SinhVienChinhQuy(int maSV, String hoTen, NgayThangNam ngaySinh, int namHoc, double diemDV,
			HashMap<Integer, Double> kqHt) {
		super(maSV, hoTen, ngaySinh, namHoc, diemDV, kqHt);
	}

	public SinhVienChinhQuy(SinhVienChinhQuy sinhvienchinhquy) {
		this.maSV = sinhvienchinhquy.maSV;
		this.hoTen = sinhvienchinhquy.hoTen;
		this.ngaySinh = sinhvienchinhquy.ngaySinh;
		this.namHoc = sinhvienchinhquy.namHoc;
		this.diemDV = sinhvienchinhquy.diemDV;
		this.kqHt = (HashMap<Integer, Double>) sinhvienchinhquy.kqHt.clone();
	}

	// @Override
	// public String row() {
	// 	return String.format("%10d | %-30s | %15s | %7d | %4.2f | %20s", maSV, hoTen, ngaySinh, namHoc, diemDV, "null");
	// }
}
