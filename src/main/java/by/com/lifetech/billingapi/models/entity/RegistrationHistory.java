package by.com.lifetech.billingapi.models.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "REGISTRATIONS_HISTORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationHistory {

	@Id
	@GeneratedValue(generator = "InvSeq")
	@SequenceGenerator(name = "InvSeq", sequenceName = "SEQ_TRANSACTION_HIST_CODE", allocationSize = 1)
	@Column(name = "CODE")
	@JsonProperty(value = "id")
	private Long code;

	@Column(name = "ENTRY_DATE")
	private LocalDateTime entryDate;

	@Column(name = "REGISTRATION_DATE")
	private LocalDateTime registrationDate;

	@Column(name = "CONTRACT_CODE")
	private String contractCode;

	@Column(name = "MOBILE_NO")
	private String mobileNo;

	@Column(name = "ICCID")
	private String iccid;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "AGENT")
	private String agent;

	@Column(name = "SALES_POINT")
	private String salesPoint;

	@Column(name = "TARIFF")
	private String tariffCode;

	@Column(name = "CATEGORY")
	private String categoryCode;

	@Column(name = "IDENTIFICATION_CODE")
	private String identificationCode;

	@Column(name = "REGISTRATION_TYPE")
	private String registrationTypeCode;

	@Column(name = "URL_DOCUMENT")
	private String urlDocument;

	@Transient
	private String tariffName;

	@Transient
	private String categoryName;

	@Transient
	private String registrationTypeName;

}
