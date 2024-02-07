package by.com.lifetech.billingapi.models.enums;

import by.com.lifetech.billingapi.exceptions.BusinessException;

public enum Lang {
	RU("RU"), EN("EN"), BE("BE");

	private String langCode;

	Lang(String langCode) {
		this.langCode = langCode;
	}

	public String toString() {
		return this.langCode;
	}

	public static Lang getByValue(String v) throws BusinessException {
		for (Lang s : values()) {
			if (s.toString().equals(v.toUpperCase()))
				return s;
		}
		throw new BusinessException("Billing Lang for " + v + " not found");
	}
}
