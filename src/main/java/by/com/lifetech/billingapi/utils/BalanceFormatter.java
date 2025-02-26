package by.com.lifetech.billingapi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BalanceFormatter {

	private BalanceFormatter() {
	}

	public static String getFormattedValue(BigDecimal amount, String measureCode) {
		String res;
		switch (measureCode) {
			case "Seconds" -> res = amount.divide(new BigDecimal(60), 0, RoundingMode.HALF_DOWN) + " мин";
			case "Bytes" -> {
				BigDecimal divideRes = amount.divide(new BigDecimal(1024 * 1024 * 1024), 2, RoundingMode.HALF_DOWN);
				if (divideRes.compareTo(new BigDecimal(1)) > 0) {
					res = divideRes + " ГБ";
				} else {
					res = amount.divide(new BigDecimal(1024 * 1024), 0, RoundingMode.HALF_DOWN) + " МБ";
				}
			}
			case "BYR", "Point" -> res = amount.setScale(2, RoundingMode.FLOOR) + " руб";
			case "MMS", "SMS" -> res = amount.setScale(0, RoundingMode.HALF_UP) + " шт";
			default -> res = amount.setScale(0, RoundingMode.HALF_UP) + " ед";
		}
		return res;
	}

}
