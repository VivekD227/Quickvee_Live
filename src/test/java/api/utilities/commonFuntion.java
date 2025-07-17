package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class commonFuntion {

	public static String newEmail() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dates = sdf.format(date);
		System.out.println(dates);
		String lastDateFormat = dates.replace("/", "").replace(":", "").replace(" ", "");
		return lastDateFormat;

	}
}
