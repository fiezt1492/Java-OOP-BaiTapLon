import qlsv.*;
import util.*;
import java.util.*;
import javax.swing.*;

public class App {
	public static String menu() {
		List<String> options = new ArrayList<String>();
		options.add("Them 1 Khoa");
		options.add("Them 1 sinh vien");
		options.add("Xac dinh sinh vien co phai la sinh vien tai chuc?");

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
		res += "\n(0). Thoat.";

		return getLine(maxLength) + res;

		// Notes:
		// - Xac dinh sinh vien co phai la sinh vien tai chuc?
		// - Lay diem trung binh cac mon hoc cua sinh vien tai chuc dua vao 1 hoc ki cho
		// truoc.
		// - Xac dinh tong so sinh vien tai chuc cua khoa.
		// - Ở mỗi khoa, tìm ra sinh viên có điểm trung bình học kỳ cao nhất (ở bất kỳ
		// học kỳ nào).
		// - Ở mỗi khoa, sắp xếp danh sách sinh viên tăng dần theo loại và giảm dần theo
		// năm vào học.
		// - Ở mỗi khoa, thống kê số lượng sinh viên theo năm vào học.

		// Thoat
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

		team.put("2080600803", "Trương Thục Vân");
		team.put("2080600246", "Hoàng Tiến Đạt");
		team.put("2011063152", "Bùi Thành Đạt");
		team.put("2080600759", "Huỳnh Nhật Trường");
		team.put("2080600763", "Phạm Huỳnh Nhật Trường");
		team.put("2011253052", "Nguyễn Quốc Kha");
		team.put("2080600919", "Lê Ngọc Thuận");
		team.put("2080600914", "Nguyễn Hồng Thái");
		team.put("2011060485", "Trần Đăng Khoa");

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

	public static void main(String[] args) throws Exception {
		System.out.println(ShowMyInfor());
		System.out.println(menu());

		Khoa khoa = new Khoa();
		NgayThangNam ngaysinh = new NgayThangNam();
		ngaysinh.input();
		HashMap<Integer, Double> kqHt = (HashMap<Integer, Double>) genKqHt().clone();
		SinhVienTaiChuc tc1 = new SinhVienTaiChuc(1, "Truong Thuc Thuc", ngaysinh,2001, 5, kqHt, "noiLKDaoTao");
		SinhVienTaiChuc tc2 = new SinhVienTaiChuc(2, "Schjr", ngaysinh, 2002, 6, kqHt, noiLKDaoTao);
		SinhVienChinhQuy cq3 = new SinhVienChinhQuy(3, "Truong Van Van", ngaysinh, 2003, 10, kqHt, "noiLKDaoTao");

		khoa.getDsSinhVien().add(tc1);
		khoa.getDsSinhVien().add(tc2);
		khoa.getDsSinhVien().add(cq3);
		// khoa.input();
		System.out.println(khoa.output());
		khoa.sortTangLoaiGiamNam();
		System.out.println(khoa.output());

		// Khoa k1 = new Khoa();
		// k1.input();
		// System.out.println(k1.output());

		// for (;;) {
		// System.out.println(menu());

		// do {
		// // System.out.println("Chon: ");
		// // option = sc.nextInt();
		// // sc.nextLine();
		// // if ((option < 0) || (option > 3)) {
		// // // clearScreen();
		// // System.out.println("Invalid input!");
		// // System.out.println(menu());
		// // }
		// } while ((0 < 0) || (0 > 3));

		// switch (0) {
		// case 1:

		// break;

		// case 2:

		// break;

		// case 3:

		// break;

		// default:
		// // clearScreen();
		// // sc.close();
		// System.out.println("Thoat!");
		// System.exit(0);
		// break;
		// }
		// }

	}
}
