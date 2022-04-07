package qlsv;

import javax.swing.*;
import java.util.HashMap;
import util.NgayThangNam;

public class SinhVienTaiChuc extends SinhVien {
	private String noiLKDaoTao;

	public SinhVienTaiChuc() {
		super();
	}

	public SinhVienTaiChuc(int maSV, String hoTen, NgayThangNam ngaySinh, int namHoc, double diemDV,
			HashMap<Integer, Double> kqHt, String noiLKDaoTao) {
		super(maSV, hoTen, ngaySinh, namHoc, diemDV, kqHt);
		this.noiLKDaoTao = noiLKDaoTao;
	}

	public SinhVienTaiChuc(SinhVienTaiChuc sinhvientaichuc) {
		this.maSV = sinhvientaichuc.maSV;
		this.hoTen = sinhvientaichuc.hoTen;
		this.ngaySinh = sinhvientaichuc.ngaySinh;
		this.namHoc = sinhvientaichuc.namHoc;
		this.diemDV = sinhvientaichuc.diemDV;
		this.kqHt = (HashMap<Integer, Double>) sinhvientaichuc.kqHt.clone();
		this.noiLKDaoTao = sinhvientaichuc.noiLKDaoTao;
	}

	public String getNoiLKDaoTao() {
		return noiLKDaoTao;
	}

	public void setNoiLKDaoTao(String noiLKDaoTao) {
		this.noiLKDaoTao = noiLKDaoTao;
	}

	@Override
	public String row() {
		return String.format("%10s | %-30s | %10s | ");
	}

	@Override
	public int input() {
		if (super.input() == -1) return -1;
		String str = JOptionPane.showInputDialog(null, "Nhap Noi lien ket Dao tao: ", "0");
		if (str == null) return -1;
		this.noiLKDaoTao = str;
		return 0;
	}

	@Override
	public String output() {
		String out = "Noi lien ket Dao tao: " + noiLKDaoTao + "\n";
		return super.output() + out;
	}
}
