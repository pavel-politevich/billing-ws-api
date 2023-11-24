package by.com.lifetech.billingapi.models.enums;

public enum Lang {
	RU("RU"), EN("EN"), BE("BE");

	private String langCode;

	Lang(String langCode) {
		this.langCode = langCode;
	}

	public String toString() {
		return this.langCode;
	}

	public static Lang getByValue(String v) throws IllegalArgumentException {
		for (Lang s : values()) {
			if (s.toString().equals(v))
				return s;
		}
		throw new IllegalArgumentException("Billing Lang for " + v + " not found");
	}
}
