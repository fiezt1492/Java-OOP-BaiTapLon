import qlsv.*;
import util.*;
import java.util.*;
import javax.swing.*;

public class App {
	public static List<String> menu() {
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
		options.add("Sinh ngau nhien danh sach sinh vien");

		return options;
	}

	public static String menuToString(List<String> options) {
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

		team.put("2080600803", "Truong Thuc Van");
		team.put("2080600246", "Hoang Tien Dat");
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

	public static ArrayList<SinhVien> genDssv(int amount) {
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		for (int i = 0; i < amount; i++) {
			SinhVien sv = null;
			NgayThangNam ntn = new NgayThangNam(getRandomInt(1, 30), getRandomInt(1, 12), getRandomInt(0, 2000));
			if (getRandomBoolean() == true)
				sv = new SinhVienTaiChuc(i, getRandomString(10), ntn, getRandomInt(0, 2022), getRandomDouble(1, 30),
						(HashMap<Integer, Double>) genKqHt().clone(), getRandomString(10));
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

	public static void AppInterface(Khoa khoa) {
		for (;;) {
			// System.out.println(menu());
			int option = 0;
			boolean error = false;
			do {
				error = false;
				try {
					String str = JOptionPane.showInputDialog(menuToString(menu()), "0");
					if (str == null)
						break;
					option = Integer.parseInt(str);
					if ((option < 0) || (option > menu().size())) {
						error = true;
					}
				} catch (Exception e) {
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
				case 3: // Xuat danh sach Sinh Vien cua Khoa
					System.out.println(khoa.output());
					break;
				case 4: // Xac dinh Sinh Vien do co phai la Sinh Vien Tai Chuc
					for (SinhVien sVien : khoa.getDsSinhVien().getDssv()) {
						if (sVien.isSVTaiChuc())
							System.out.println(sVien.output());
					}
					break;
				case 5: // Lay diem trung binh cac mon hoc cua Sinh Vien Tai Chuc dua vao 1 hoc ki cho
						// truoc
					String str;
					boolean error1 = false;
					int key = 0;
					do {
						error1 = false;
						try {
							str = JOptionPane.showInputDialog(null, "Nhap hoc ki can tim: ", key);
							key = Integer.parseInt(str);
							error1 = false;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Nhap khong hop le ");
							error1 = true;
						}
					} while (error1 == true);
					System.out.println("> Danh sach diem trung binh hoc ky " + key + " cua sinh vien tai chuc <");
					for (SinhVien sVien : khoa.getDsSinhVien().getDssv()) {
						if (sVien.getDTB_SVTaiChuc(key) != -1)
							System.out.println("Ten: " + sVien.getHoTen() + " | Diem: " + sVien.getDTB_SVTaiChuc(key));
					}
					break;
				case 6: // Xac dinh tong so Sinh Vien Tai Chuc cua Khoa
					System.out.println("> So Luong SV Tai Chuc: " + khoa.tong_SVTaiChuc());
					break;

				case 7: // Trong moi Khoa, tim ra Sinh Vien co diem trung binh hoc ky cao nhat (o bat ky
						// hoc ky nao)
					String str1;
					boolean error2 = false;
					int key1 = 0;
					do {
						error2 = false;
						try {
							str1 = JOptionPane.showInputDialog(null, "Nhap hoc ki can tim: ", key1);
							key1 = Integer.parseInt(str1);
							error2 = false;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Nhap khong hop le ");
							error2 = true;
						}
					} while (error2 == true);
					khoa.Max_diemTBHocKy(key1);
					break;
				case 8: // Trong moi Khoa, sap xep danh sach Sinh Vien tang dan theo loai va giam dan
						// theo nam vao hoc
					khoa.sortTangLoaiGiamNam();
					break;
				case 9: // Trong moi Khoa, thong ke so luong Sinh Vien theo nam vao hoc
					khoa.thongKeSLTheoNamHoc();
					break;
				case 10: // Sinh ngau nhien danh sach sinh vien
					DSSinhVien dssv = new DSSinhVien(genDssv(8));
					khoa.setTenKhoa(getRandomString(15));
					khoa.setDsSinhVien(dssv);
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

	public static void main(String[] args) throws Exception {
		System.out.println(ShowMyInfor());
		// System.out.println(menu());

		Khoa khoa = new Khoa();
		AppInterface(khoa);
	}
}
