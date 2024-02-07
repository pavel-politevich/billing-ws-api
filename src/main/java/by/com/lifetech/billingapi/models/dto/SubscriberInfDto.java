package by.com.lifetech.billingapi.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import by.com.lifetech.billingapi.wsdl.ChainResultElement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SubscriberInfDto {

	Long accountId;
	@Schema(description = "Mobile No", example = "375255556677")
	String msisdn;
	@Schema(description = "Contract Id", example = "300134745")
	String contractId;
	@Schema(description = "ICCID 19 digits", example = "893750306180538449")
	String iccid;
	SubscriberStateDto state;
	SegmentDto segment;
	TariffDto tariff;
	RiskLevelDto riskLevel;
	LocalDateTime activationDate;
	ContractTypeDto contractType;
	LineLevelDto lineLevel;
	String firstName;
	String lastName;
	String middleName;
	@Schema(description = "Credit limit for corporate subscribers", example = "500")
	double lineSpendingLimit;
	double lineMain;
	double recAmount;
	double linePenalty;
	double reservedLineMain;
	double lineBonus;
	int paidObligations;
	int remainObligations;

	public SubscriberInfDto(List<ChainResultElement> chainResultList) {
		super();
		for (ChainResultElement el : chainResultList) {
			switch (el.getName()) {
			case "state":
				this.state = new SubscriberStateDto(el.getValue().toString(), "");
				break;
			case "segment":
				this.segment = new SegmentDto(el.getValue().toString(), "");
				break;
			case "iccid":
				this.iccid = el.getValue().toString();
				break;
			case "lastName":
				this.lastName = el.getValue().toString();
				break;
			case "lineLevel":
				this.lineLevel = new LineLevelDto(el.getValue().toString(), "");
				break;
			case "accountId":
				this.accountId = Long.parseLong(el.getValue().toString());
				break;
			case "tariffCode":
				this.tariff = new TariffDto(el.getValue().toString(), "");
				break;
			case "activationDate":
				this.activationDate = LocalDateTime.parse(el.getValue().toString());
				break;
			case "contractId":
				this.contractId = el.getValue().toString();
				break;
			case "riskLevel":
				this.riskLevel = new RiskLevelDto(el.getValue().toString(), "");
				break;
			case "middleName":
				this.middleName = el.getValue().toString();
				break;
			case "firstName":
				this.firstName = el.getValue().toString();
				break;
			case "contractType":
				this.contractType = new ContractTypeDto(el.getValue().toString(), "");
				break;
			case "lineMain":
				this.lineMain = Double.parseDouble(el.getValue().toString());
				break;
			case "remainObligations":
				this.remainObligations = Double.valueOf(el.getValue().toString()).intValue();
				break;
			case "lineBonus":
				this.lineBonus = Double.parseDouble(el.getValue().toString());
				break;
			case "linePenalty":
				this.linePenalty = Double.parseDouble(el.getValue().toString());
				break;
			case "reservedLineMain":
				this.reservedLineMain = Double.parseDouble(el.getValue().toString());
				break;
			case "paidObligations":
				this.paidObligations = Double.valueOf(el.getValue().toString()).intValue();
				break;
			case "lineSpendingLimit":
				this.lineSpendingLimit = Double.parseDouble(el.getValue().toString());
				break;
			}
		}
	}

	public SubscriberInfDto() {
	}

}
