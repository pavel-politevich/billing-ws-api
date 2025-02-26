package by.com.lifetech.billingapi.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ChargesId.class)
public class Charges {

	@Id
    @Column(name = "TID")
    private String tid;
	
	@Column(name = "SERVED_PARTY")
    private String servedParty;
	
	@Column(name = "OTHER_PARTY")
    private String otherParty;
	
	@Column(name = "SERVED_PARTY_MASKED")
    private String servedPartyMasked;

    @Id
	@Column(name = "EVENTSTART")
    private LocalDateTime eventStart;
	
	@Column(name = "STARTTIME")
    private LocalDateTime startTime;

    @Id
	@Column(name = "RDM")
    private String servicePath;
	
	@Column(name = "LM_BEFORE")
    private Double lineMainBefore;
	
	@Column(name = "LM_AFTER")
    private Double lineMainAfter;
	
	@Column(name = "LM")
    private Double lineMain;
	
	@Column(name = "LD_BEFORE")
    private Double lineDebtBefore;
	
	@Column(name = "LD_AFTER")
    private Double lineDebtAfter;
	
	@Column(name = "LD")
    private Double lineDebt;
	
	@Column(name = "LB_BEFORE")
    private Double lineBonusBefore;
	
	@Column(name = "LB_AFTER")
    private Double lineBonusAfter;
	
	@Column(name = "LB")
    private Double lineBonus;
	
	@Column(name = "LE_BEFORE")
    private Double lineExtraBefore;
	
	@Column(name = "LE_AFTER")
    private Double lineExtraAfter;
	
	@Column(name = "LE")
    private Double lineExtra;
	
	@Column(name = "CM_BEFORE")
    private Double commonMainBefore;
	
	@Column(name = "CM_AFTER")
    private Double commonMainAfter;
	
	@Column(name = "CM")
    private Double commonMain;
	
	@Column(name = "CB_BEFORE")
    private Double commonBonusBefore;
	
	@Column(name = "CB_AFTER")
    private Double commonBonusAfter;
	
	@Column(name = "CB")
    private Double commonBonus;
	
	@Column(name = "CD_BEFORE")
    private Double commonDebtBefore;
	
	@Column(name = "CD_AFTER")
    private Double commonDebtAfter;
	
	@Column(name = "CD")
    private Double commonDebt;
	
	@Column(name = "OB_COUNT")
    private Double obCount;
	
	@Column(name = "DURATION")
    private Double duration;
	
	@Column(name = "TEXT")
    private String text;
	
	@Column(name = "MEASURE_UNIT_CODE")
    private String measureUnitCode;
	
	@Column(name = "SEARCH_ACK")
    private Long searchAccId;
	
	@Column(name = "SEARCH_OACK")
    private Long searchOAccId;
	
	@Column(name = "SEARCH_STARTTIME")
    private LocalDateTime searchStartTime;
	
	@Column(name = "CLASS_NAME_SHORT")
    private String classNameShort;
	
	@Column(name = "CLASS_NAME")
    private String className;
	
	@Column(name = "CATEGORY_NAME_SHORT")
    private String categoryNameShort;
	
	@Column(name = "CATEGORY_NAME")
    private String categoryName;
	
	@Column(name = "SUBJECT_MSISDN")
    private String subjectMsisdn;
	
	@Column(name = "SUBJECT_MSISDN_CONTRACT")
    private String subjectMsisdnContract;
	
	@Column(name = "LOCATION")
    private String location;
	
	@Column(name = "PNL_CODE")
    private String pnlCode;
}
