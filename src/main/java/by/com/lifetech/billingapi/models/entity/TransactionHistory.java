package by.com.lifetech.billingapi.models.entity;

import by.com.lifetech.billingapi.models.dto.TransactionTypeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION_HISTORY", schema = "tm_cim")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
	
	@Id
	@GeneratedValue(generator = "InvSeq")
	@SequenceGenerator(name = "InvSeq", sequenceName = "SEQ_TRANSACTION_HIST_CODE", allocationSize = 1)
	@Column(name = "CODE")
	private Long Id;
	
	@Column(name = "ENTRY_DATE")
	private LocalDateTime entryDate;
	
	@Column(name = "A_CONTRACT_CODE")
	@JsonProperty("aContractCode")
	private String aContractCode;
	
	@Column(name = "A_MOBILE_NO")
	@JsonProperty("aMobileNo")
	private String aMobileNo;
	
	@Column(name = "A_SUBSCRIBER_LOCATION")
	private String aSubscriberLocation;
	
	@Column(name = "B_CONTRACT_CODE")
	private String bContractCode;
	
	@Column(name = "B_MOBILE_NO")
	private String bMobileNo;
	
	@Column(name = "B_SUBSCRIBER_LOCATION")
	private String bSubscriberLocation;
	
	@Column(name = "PARENT_CODE")
	private Long parentCode;
	
	@Column(name = "TRANSACTION_TYPE_CODE")
	private Long transactionTypeCode;
	
	@Transient
	private String transactionTypeName;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "AGENT_NAME")
	private String agentName;
	
	@Column(name = "NGS_CODE")
	private String ngsCode;
	
	@Column(name = "LOAD_TO_CH")
	private String loadToCh = "Y";
	
	@Transient
	@JsonIgnore
	private TransactionTypeDto transactionType;
}
