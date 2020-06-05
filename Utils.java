package dataio;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	// Kiểm tra xem hôm nay có phải chủ nhật không ?
	public static boolean checkWeekend() {
		// Sunday = 1
		if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
			return true;
		}
		return false;
	}
	public static boolean checkEndOfMonth() {
		Calendar calendar = Calendar.getInstance();
		
		switch(calendar.get(Calendar.MONTH) + 1) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if(calendar.get(Calendar.DAY_OF_MONTH) == 31) {
					return true;
				}
				return false;
			case 4:
			case 6:
			case 9:
			case 11:
				if(calendar.get(Calendar.DAY_OF_MONTH) == 30) {
					return true;
				}
				return false;
			default:
				if(calendar.get(Calendar.YEAR) % 4 == 0 || calendar.get(Calendar.YEAR) % 400 == 0)  {
					if(Calendar.DAY_OF_MONTH == 29) return true;
					else return false;
				} else {
					if(Calendar.DAY_OF_MONTH == 28) return true;
					else return false;
				}
		}
	}
	public static boolean isNumberContain(String items) { // Check umber %.
		if(Character.isDigit(items.charAt(0)) || Character.isDigit(items.charAt(1))) {
			return true;
		}
		return false;
	}
	public static double roundDouble(double x) {
		return (double) Math.round(x*100)/100;
	}
}
