package qlsv;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import util.IO_Interface;

public class DSSinhVien implements IO_Interface {
	protected ArrayList<SinhVien> ds;

	public DSSinhVien() {
		ds = new ArrayList<SinhVien>();
	}

	public DSSinhVien(ArrayList<SinhVien> ds) {
		this.ds = (ArrayList<SinhVien>) ds.clone();
	}

	public DSSinhVien(DSSinhVien dssv) {
		this.ds = (ArrayList<SinhVien>) dssv.ds.clone();
	}

	public ArrayList<SinhVien> getDssv() {
		return ds;
	}

	public SinhVien get(int index) {
		return ds.get(index);
	}

	public void add(SinhVien sv) {
		ds.add(sv);
	}

	public int input() {
		int isTiepTuc;
		SinhVien sv;
		do {
			Object[] options = { "Sinh Vien Chinh Quy",
					"Sinh Vien Tai Chuc" };
			int n = JOptionPane.showOptionDialog(null, null, "Lua chon", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == JOptionPane.DEFAULT_OPTION)
				return -1;
			else if (n == JOptionPane.YES_OPTION)
				sv = new SinhVienChinhQuy();
			else
				sv = new SinhVienTaiChuc();
			if (sv.input() == 0)
				add(sv);
			else
				return -1;
			isTiepTuc = JOptionPane.showConfirmDialog(null, String.format("Ban co muon them sinh vien ?"), "Xac nhan",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while (isTiepTuc == JOptionPane.YES_OPTION);
		return 0;
	}

	public String output() {
		String s = "";
		for (SinhVien e : ds) {
			s += e.output();
		}

		return s;
	}

	public void list() {

	}
}