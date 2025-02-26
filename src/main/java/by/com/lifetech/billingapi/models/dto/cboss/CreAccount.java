package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreAccount {

	@JsonProperty("AccountID")
	public Long accountID;
	@JsonProperty("ParentID")
	public Long parentID;
	@JsonProperty("LastEventDate")
	public String lastEventDate;
	@JsonProperty("ActivationDate")
	public String activationDate;
	@JsonProperty("LastThrCheck")
	public String lastThrCheck;
	@JsonProperty("MoveTime")
	public String moveTime;
	@JsonProperty("ThrSeqNo")
	public Long thrSeqNo;
	@JsonProperty("EvtSeqNo")
	public Long evtSeqNo;
	@JsonProperty("ParentValidFrom")
	public String parentValidFrom;
	@JsonProperty("LanguageID")
	public String languageID;
	@JsonProperty("LineLvl")
	public String lineLvl;
	@JsonProperty("TariffPlan")
	public String tariffPlan;
	@JsonProperty("State")
	public String state;
	@JsonProperty("Status")
	public Long status;
	@JsonProperty("UseCommonMain")
	public boolean useCommonMain;
	
	public String getTariffPlan() {
		if (tariffPlan != null) {
			return tariffPlan.substring(tariffPlan.indexOf("_") + 1);
		} else {
			return tariffPlan;
		}
	}
}
