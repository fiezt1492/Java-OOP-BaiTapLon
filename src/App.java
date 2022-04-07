import qlsv.*;
import util.*;
import java.util.*;
import javax.swing.*;

public class App {
	public static String menu() {
		List<String> options = new ArrayList<String>();
		options.add("Nhap du lieu cho Khoa");
		options.add("Them 1 Sinh Vien");
		options.add("Xuat danh sach Sinh Vien cua Khoa");
		options.add("Xac dinh Sinh Vien do co phai la Sinh Vien Tai Chuc");
		options.add("Lay diem trung binh cac mon hoc cua Sinh Vien Tai Chuc dua vao 1 hoc ki cho truoc");
		options.add("Xac dinh tong so Sinh Vien Tai Chuc cua Khoa");
		options.add("Trong moi Khoa, tim ra Sinh Vien co diem trung binh hoc ky cao nhat (o bat ky hoc ky nao)");
		options.add("Trong moi Khoa, sap xep danh sach Sinh Vien tang dan theo loai va giam dan theo nam vao hoc");
		options.add("Trong moi Khoa, thong ke so luong Sinh Vien theo nam vao hoc");

		String res = "\n";
		int stt = 1;
		int maxLength = 0;

		for (String s : options) {
			String str = String.format("(%d). %s\n", stt++, s);
			if (maxLength < str.length())
				maxLength = str.length();
			res += str;
		}
		;

		res += getLine(maxLength);
		res += "\n(0). Thoat chuong trinh";

		return getLine(maxLength) + res;
	}

	public static String getLine(int number) {
		String res = "";
		for (int i = 0; i < number; i++) {
			res += "-";
		}
		return res;
	}

	public static String ShowMyInfor() {
		HashMap<String, String> team = new HashMap<String, String>();

		team.put("2080600803", "Truoc Thuc Van");
		team.put("2080600246", "Hoan Tien Dat");
		team.put("2011063152", "Bui Thanh Dat");
		team.put("2080600759", "Huynh Nhat Truong");
		team.put("2080600763", "Pham Huynh Nhat Truong");
		team.put("2011253052", "Nguyen Quoc Kha");
		team.put("2080600919", "Le Ngoc Thuan");
		team.put("2080600914", "Nguyen Hong Thai");
		team.put("2011060485", "Tran Dang Khoa");

		String teamName = String.format("%20s", "       > [NHÓM 2 - HUTECH] <");
		// header
		String header = String.format("%10s | %s", "MSSV", "HO VA TEN");
		// list
		String body = "";
		int maxLength = header.length();
		int i = 1;
		for (String key : team.keySet()) {
			String str = String.format("%10s | %s", key, team.get(key));
			if (maxLength < str.length())
				maxLength = str.length();
			body += str;
			if (i++ < team.size())
				body += "\n";
		}

		String res = getLine(maxLength);
		res += "\n";
		res += teamName;
		res += "\n";
		res += getLine(maxLength);
		res += "\n";
		res += header;
		res += "\n";
		res += getLine(maxLength);
		res += "\n";
		res += body;
		res += "\n";
		res += getLine(maxLength);
		return res;
	}

	public static HashMap<Integer, Double> genKqHt() {
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		int soHocKy = (int) (Math.random() * 7 + 1);
		for (int i = 1; i <= soHocKy; i++) {
			res.put(i, (double) (Math.random() * 9 + 1));
		}
		return res;
	}

	public static ArrayList<SinhVien> genDssv() {
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		for (int i = 0; i < getRandomInt(1, 8); i++) {
			SinhVien sv = null;
			NgayThangNam ntn = new NgayThangNam(getRandomInt(1, 30), getRandomInt(1, 12), getRandomInt(0, 2000));
			if (getRandomBoolean() == true)
				sv = new SinhVienTaiChuc(i, getRandomString(10), ntn, getRandomInt(0, 2022), getRandomDouble(1, 30),
						(HashMap<Integer, Double>) genKqHt().clone(), getLine(10));
			else
				sv = new SinhVienChinhQuy(i, getRandomString(10), ntn, getRandomInt(0, 2022), getRandomDouble(1, 30),
						(HashMap<Integer, Double>) genKqHt().clone());
			dssv.add(sv);
		}
		return dssv;
	}

	public static int getRandomInt(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	public static double getRandomDouble(int min, int max) {
		return (Math.random() * (max - min) + min);
	}

	public static String getRandomString(int length) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public static boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(ShowMyInfor());
		System.out.println(menu());

		Khoa khoa = new Khoa();
		// khoa.setTenKhoa("Tran Dang Khoa");
		// NgayThangNam ngaysinh = new NgayThangNam(1, 2, 3);
		// // ngaysinh.input();
		// HashMap<Integer, Double> kqHt = (HashMap<Integer, Double>) genKqHt().clone();
		// SinhVienTaiChuc tc1 = new SinhVienTaiChuc(1, "Truong Thuc Thuc", ngaysinh,
		// 2001, 5, kqHt, "noiLKDaoTao");
		// SinhVienChinhQuy cq3 = new SinhVienChinhQuy(3, "Truong Van Van", ngaysinh,
		// 1999, 10, kqHt);
		// SinhVienTaiChuc tc2 = new SinhVienTaiChuc(2, "Schjr", ngaysinh, 2002, 6,
		// kqHt, "popo station");

		// khoa.getDsSinhVien().add(tc1);
		// khoa.getDsSinhVien().add(tc2);
		// khoa.getDsSinhVien().add(cq3);
		// // khoa.input();
		// System.out.println("---------------------------\nTRUOC KHI SORT\n" +
		// khoa.output());
		// khoa.sortTangLoaiGiamNam();
		// System.out.println("---------------------------\nDA SORT\n" + khoa.output());

		// Khoa k1 = new Khoa();
		// k1.input();
		// System.out.println(k1.output());

		for (;;) {
			// System.out.println(menu());
			int option = 0;
			boolean error = false;
			do {
				try {
					String str = JOptionPane.showInputDialog(menu(), "0");
					if (str == null || str.trim().isEmpty()) {
						option = 0;
						break;
					}
					option = Integer.parseInt(str);
					if ((option < 0) || (option > 8)) {
						error = true;
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					error = true;
				}
				if (error == true)
					JOptionPane.showMessageDialog(null, "Khong co lua chon phu hop", "Loi", JOptionPane.ERROR_MESSAGE);
			} while (error == true);

			switch (option) {
				case 1: // Them 1 Khoa
					khoa.input();
					break;
				case 2: // Them 1 Sinh Vien
					if (khoa.getTenKhoa() == null) {
						JOptionPane.showMessageDialog(null, "Ban chua nhap du lieu cho khoa! (1)", "Loi",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
					SinhVien sv;
					Object[] options = { "Sinh Vien Chinh Quy",
							"Sinh Vien Tai Chuc" };
					int n = JOptionPane.showOptionDialog(null, null, "Lua chon", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (n == JOptionPane.YES_OPTION)
						sv = new SinhVienChinhQuy();
					else
						sv = new SinhVienTaiChuc();
					if (sv.input() == 0)
						khoa.getDsSinhVien().add(sv);
					break;
				case 3: // Xac dinh Sinh Vien do co phai la Sinh Vien Tai Chuc
					
					break;
				case 3: // Xac dinh Sinh Vien do co phai la Sinh Vien Tai Chuc

					break;
				case 4: // Lay diem trung binh cac mon hoc cua Sinh Vien Tai Chuc dua vao 1 hoc ki cho
						// truoc

					break;
				case 5: // Xac dinh tong so Sinh Vien Tai Chuc cua Khoa

					break;
				case 6: // Trong moi Khoa, tim ra Sinh Vien co diem trung binh hoc ky cao nhat (o bat ky
						// hoc ky nao)

					break;
				case 7: // Trong moi Khoa, sap xep danh sach Sinh Vien tang dan theo loai va giam dan
						// theo nam vao hoc
					khoa.sortTangLoaiGiamNam();
					break;
				case 8: // Trong moi Khoa, thong ke so luong Sinh Vien theo nam vao hoc
					khoa.thongKeSLTheoNamHoc();
					break;
				default:
					// clearScreen();
					// sc.close();
					int isContinue = JOptionPane.showConfirmDialog(null, "Ban co muon ket thuc?", "Thoat",
							JOptionPane.YES_NO_OPTION);
					if (isContinue == JOptionPane.YES_OPTION)
						System.exit(0);
					break;
			}
		}

	}
}
