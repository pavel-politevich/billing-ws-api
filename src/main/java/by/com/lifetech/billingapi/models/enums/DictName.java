package by.com.lifetech.billingapi.models.enums;

import by.com.lifetech.billingapi.exceptions.BusinessException;

public enum DictName {
	
	DICT_STATE("DICT_STATE"), 
	DICT_SEGMENT("DICT_SEGMENT"), 
	DICT_RISK_LEVEL("DICT_RISK_LEVEL"), 
	DICT_CONTRACT_TYPE("DICT_CONTRACT_TYPE"), 
	DICT_LINE_LEVEL("DICT_LINE_LEVEL"), 
	DICT_TH_TYPE("DICT_TH_TYPE"), 
	DICT_TARIFF("DICT_TARIFF"), 
	DICT_CATEGORY("DICT_CATEGORY"), 
	DICT_REG_TYPE("DICT_REG_TYPE");

	private String dictCode;

	DictName(String dictCode) {
		this.dictCode = dictCode;
	}

	public String toString() {
		return this.dictCode;
	}

	public static DictName getByValue(String v) throws BusinessException {
		for (DictName s : values()) {
			if (s.toString().equals(v.toUpperCase()))
				return s;
		}
		throw new BusinessException("Dictionary type " + v + " not found");
	}
}
