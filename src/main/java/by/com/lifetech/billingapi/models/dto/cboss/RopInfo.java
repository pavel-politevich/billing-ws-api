package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RopInfo {

	@JsonProperty("RopID")
	public long ropID;
	@JsonProperty("RuleID")
	public long ruleID;
	@JsonProperty("BundleID")
	public long bundleID;
	@JsonProperty("StartDate")
	public String startDate;
	@JsonProperty("ChargedToDate")
	public String chargedToDate;
	@JsonProperty("NextTriggerDate")
	public String nextTriggerDate;
	@JsonProperty("EndDate")
	public String endDate;
	@JsonProperty("SeqNo")
	public int seqNo;
	@JsonProperty("LastExecDate")
	public double lastExecDate;
	@JsonProperty("Status")
	public int status;
	@JsonProperty("LastExecResult")
	public int lastExecResult;
	@JsonProperty("LastExecShare")
	public double lastExecShare;
	@JsonProperty("LastPeriodStart")
	public String lastPeriodStart;
}
