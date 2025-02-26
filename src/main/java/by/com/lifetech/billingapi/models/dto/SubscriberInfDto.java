package by.com.lifetech.billingapi.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import by.com.lifetech.billingapi.models.dto.cboss.CreEvent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriberInfDto {

	Long accountId;
	@Schema(description = "Mobile No", example = "375255556677")
	String msisdn;
	@Schema(description = "Contract Id", example = "300134745")
	String contractId;
	@Schema(description = "ICCID 19 digits", example = "893750306180538449")
	String iccid;
	@Schema(description = "IMSI 15 digits", example = "257040111236666")
	String imsi;
	SubscriberStateDto state;
	SegmentDto segment;
	TariffDto tariff;
	CategoryDto category;
	LocalDateTime activationDate;
	LocalDateTime lcDateFrom;
	LocalDateTime lcDateTo;
	LocalDateTime lastReloadDate;
	LocalDateTime lastDeactDate;
	ContractTypeDto contractType;
	LineLevelDto lineLevel;
	UniversalDictionary riskLevel;
	UniversalDictionary riskFraud;
	UniversalDictionary subProfileRisk;
	UniversalDictionary geoRisk;
	UniversalDictionary finRisk;
	UniversalDictionary community;
	String firstName;
	String lastName;
	String middleName;
	String companyName;
	@Schema(description = "Credit limit for corporate subscribers", example = "500")
	double lineSpendingLimit;
	double lineMain;
	double recAmount;
	double linePenalty;
	double reservedLineMain;
	double lineBonus;
	double lineDebt;
	double linePPDebt;
	int paidObligations;
	int remainObligations;
	List<CreEvent> reservations;
}
