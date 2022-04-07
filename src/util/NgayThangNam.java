package util;

import java.util.*;
import java.text.*;
import javax.swing.*;

public class NgayThangNam implements IO_Interface {
	protected Calendar date;

	public NgayThangNam() {
	}

	public NgayThangNam(Calendar c) {
		this.date = (Calendar) c.clone();
	}

	public NgayThangNam(NgayThangNam d) {
		this.date = (Calendar) d.date.clone();
	}

	@Override
	public int input() {
		Calendar temp = null;
		int d = 0, m = 0, y = 0;
		int age = 0;
		boolean error = false;
		do {
			error = false;
			try {
				JTextField day = new JTextField();
				JTextField month = new JTextField();
				JTextField year = new JTextField();

				Object[] fields = {
						"Ngay", day,
						"Thang", month,
						"Nam", year
				};

				int value = JOptionPane.showConfirmDialog(null, fields, "Nhap ngay thang nam sinh", JOptionPane.OK_OPTION);

				if (value == JOptionPane.DEFAULT_OPTION || value == JOptionPane.NO_OPTION) return -1;

				d = Integer.parseInt(year.getText());
				m = Integer.parseInt(month.getText()) - 1;
				y = Integer.parseInt(day.getText());

				temp = new GregorianCalendar(d, m, y);
				age = getAge(temp);
				if (isValidDate(String.format("%d/%d/%d", m, d, y)) || age < 18) {
					JOptionPane.showMessageDialog(null, "Ngay thang nam khong hop le hoac khong du tuoi!", "Loi",
							JOptionPane.ERROR_MESSAGE);
					error = true;
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Nhap khong dung so");
				error = true;
			}
		} while (error == true);
		this.date = temp;
		return 0;
	}
	// public boolean isHopLe(ngay, thang, nam) {
	// if (ngay < 1 || ngay > )
	// }

	private boolean isValidDate(String input) {
		String formatString = "MM/dd/yyyy";
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatString);
			format.setLenient(false);
			format.parse(input);
		} catch (ParseException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	private int getAge(Calendar x) {
		Calendar currentDate = new GregorianCalendar();
		int age = currentDate.get(Calendar.YEAR) - x.get(Calendar.YEAR);
		// decrements age by 1
		if ((x.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH))
				|| (x.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
						&& x.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}
		return age;
	}

	@Override
	public String output() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String res = "";
		res += sdf.format(date.getTime()) + "\n";
		return res;
	}
}
