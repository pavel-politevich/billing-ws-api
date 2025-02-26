package by.com.lifetech.billingapi.models.entity;

import by.com.lifetech.billingapi.models.repository.AdvancedSearchInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "ADVANCED_SEARCH_V", schema = "BWSAPI")
public class AdvancedSearch implements AdvancedSearchInfo {
    @Id
    @NotNull
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Size(max = 15)
    @Column(name = "MOBILE_NO", length = 15)
    private String msisdn;

    @Size(max = 512)
    @Column(name = "CONTRACT_ID", length = 512)
    private String contractId;

    @Column(name = "FIRST_ACT_DATE")
    private LocalDateTime activationDate;

    @Size(max = 25)
    @Column(name = "LC_STATE", length = 25)
    private String stateCode;

    @Size(max = 80)
    @Column(name = "CONTRACT_TYPE", length = 80)
    private String contractTypeCode;

    @Size(max = 512)
    @Column(name = "FIRST_NAME", length = 512)
    private String firstName;

    @Size(max = 512)
    @Column(name = "LAST_NAME", length = 512)
    private String lastName;

    @Size(max = 512)
    @Column(name = "MID_NAME", length = 512)
    private String middleName;

    @Size(max = 512)
    @Column(name = "COMPANY_NAME", length = 512)
    private String companyName;

    @Size(max = 80)
    @Column(name = "PASSPORT_TYPE", length = 80)
    private String passportType;

    @Size(max = 512)
    @Column(name = "PASSPORT_SERIES", length = 512)
    private String passportSeries;

    @Size(max = 512)
    @Column(name = "PASSPORT_NO", length = 512)
    private String passportNo;

    @Size(max = 512)
    @Column(name = "PASSPORT_AUTHORITY", length = 512)
    private String passportAuthority;

    @Column(name = "PASSPORT_ISSUE")
    private LocalDate passportIssue;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Size(max = 80)
    @Column(name = "IDENTIFICATION_CODE", length = 80)
    private String identificationCode;

    @Size(max = 256)
    @Column(name = "CITY_NAME", length = 256)
    private String cityName;

    @Size(max = 256)
    @Column(name = "REGION_NAME", length = 256)
    private String regionName;

    @Size(max = 256)
    @Column(name = "DISTRICT_NAME", length = 256)
    private String districtName;

    @Size(max = 128)
    @Column(name = "ADR_STREET_NAME", length = 128)
    private String adrStreetName;

    @Size(max = 128)
    @Column(name = "ADR_BUILDING_NO", length = 128)
    private String adrBuildingNo;

    @Size(max = 50)
    @Column(name = "ICCID", length = 50)
    private String iccid;

    @Column(name = "IMSI")
    private String imsi;

    @Size(max = 512)
    @Column(name = "TARIFF_NAME", length = 512)
    private String tariffCode;

    @Size(max = 512)
    @Column(name = "UNP_CODE", length = 512)
    private String unpCode;

    @Size(max = 80)
    @Column(name = "EMAIL", length = 80)
    private String email;

}