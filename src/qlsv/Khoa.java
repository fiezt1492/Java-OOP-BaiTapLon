package qlsv;

import javax.swing.*;

import util.IO_Interface;

import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Khoa implements IO_Interface {
	protected String tenKhoa;
	protected DSSinhVien dsSinhVien;
	public static Hashtable<Integer, Integer> DS_SLSVTheoNamHoc = new Hashtable<Integer, Integer>();

	public Khoa() {
		this.dsSinhVien = new DSSinhVien();
	}

	public Khoa(String tenKhoa, DSSinhVien dsSinhVien) {
		this.tenKhoa = tenKhoa;
		this.dsSinhVien = dsSinhVien;
	}

	public Khoa(Khoa khoa) {
		this.tenKhoa = khoa.tenKhoa;
		this.dsSinhVien = khoa.dsSinhVien;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public DSSinhVien getDsSinhVien() {
		return dsSinhVien;
	}

	public void setDsSinhVien(DSSinhVien dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
		for (SinhVien s : dsSinhVien.getDssv()) {
			increaseSL(s.getNamHoc());
		}
	}

	public static Hashtable<Integer, Integer> getDS_SLSVTheoNamHoc() {
		return DS_SLSVTheoNamHoc;
	}

	public static void increaseSL(int key) {
		Integer sl = DS_SLSVTheoNamHoc.get(key);
        if (sl == null) {
            DS_SLSVTheoNamHoc.put(key, 1);
        } else if (sl > 0) {
            DS_SLSVTheoNamHoc.put(key, sl + 1);
        }
	}

	@Override
	public int input() {
		// TODO Auto-generated method stub
		String str = null;

		do {
			str = JOptionPane.showInputDialog(null, "Nhap Ten Khoa: ", "0");
			if (str == null)
				return -1;
		} while (str.trim().isEmpty());

		String tenKhoaTemp = str;
		JOptionPane.showMessageDialog(null, "Nhap Danh Sach Sinh Vien");
		DSSinhVien dssvTemp = new DSSinhVien();
		if (dssvTemp.input() != 0)
			return -1;
		this.tenKhoa = tenKhoaTemp;
		this.dsSinhVien = new DSSinhVien(dssvTemp);
		return 0;
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		String res = "";
		res += "Ten Khoa: " + getTenKhoa() + "\n";
		res += "[Danh sach sinh vien]\n" + getDsSinhVien().list() + "\n";
		return res;
	}

	public int tong_SVTaiChuc() {
		int count = 0;
		for (SinhVien s : dsSinhVien.getDssv()) {
			if (!s.isSVTaiChuc())
				continue;
			count++;
		}
		return count;
	}

	public void Max_diemTBHocKy(int hocKy) {
		// nhap ten hoc ky can tim
		// int tenHocKyCanTim;
		// String str = JOptionPane.showInputDialog(null, "Nhap Ten Hoc Ky can tim: ",
		// "0");
		// this.tenHocKyCanTim = Integer.parseInt(str);

		// tim max
		// getDiemTBTheoHocKy(int tenHocKy)
		// Arraylist
		if (dsSinhVien.getDssv().isEmpty()) {
			System.out.println("Danh sach rong");
			return;
		}
		DSSinhVien dsSVMaxDTB = new DSSinhVien();
		SinhVien svMax = dsSinhVien.getDssv().get(0);
		double diem, diemMax;
		for (SinhVien s : dsSinhVien.getDssv()) {
			diem = (s.getKqHt().get(hocKy) == null) ? 0 : s.getKqHt().get(hocKy);
			diemMax = (svMax.getKqHt().get(hocKy) == null) ? 0 : svMax.getKqHt().get(hocKy);

			if (diem == diemMax)
				dsSVMaxDTB.add(s);
			if (diem > diemMax) {
				if (dsSVMaxDTB.getDssv().size() > 0) {
					dsSVMaxDTB = new DSSinhVien();
					System.gc();
				}
				svMax = s;
			}
		}
		dsSVMaxDTB.add(svMax);
		System.out.println("Danh sach sinh vien co diem trung binh hoc ki cao nhat cua hoc ky " + hocKy);
		System.out.println(dsSVMaxDTB.output());
	}

	public void sortTangLoaiGiamNam() {
		Collections.sort(dsSinhVien.getDssv(), new Comparator<SinhVien>() {
			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				return o2.getNamHoc() - o1.getNamHoc();
			}
		});
		Collections.sort(dsSinhVien.getDssv(), new Comparator<SinhVien>() {
			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				return (o1.isSVTaiChuc() ? 1 : 0) - (o2.isSVTaiChuc() ? 1 : 0);
			}
		});
	}

	public void thongKeSLTheoNamHoc() {
		if (dsSinhVien.getDssv().isEmpty()) {
			System.out.println("Danh sach rong");
			return;
		}
		
		System.out.println("Danh sach So luong Sinh Vien theo nam hoc");

		for (int key : getDS_SLSVTheoNamHoc().keySet()) {
			System.out.println(key + ": " + DS_SLSVTheoNamHoc.get(key));
		}
	}
}
