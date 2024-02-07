package by.com.lifetech.billingapi.models.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.com.lifetech.billingapi.models.dto.TransactionTypeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_HISTORY")
@Data
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
	private String aContractCode;
	
	@Column(name = "A_MOBILE_NO")
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
	
	@JsonIgnore
	@Column(name = "TRANSACTION_TYPE_CODE")
	private Long transactionTypeCode;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "AGENT_NAME")
	private String agentName;
	
	@Column(name = "NGS_CODE")
	private String ngsCode;
	
	@Column(name = "LOAD_TO_CH")
	private String loadToCh;
	
	@Transient
	private TransactionTypeDto transactionType;

}
