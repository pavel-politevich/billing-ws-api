package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ACCOUNT_EX_TBL", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class AccountExTbl {

    @Id
    @Column(name = "account_ex_id")
    private Long accountExId;

    @OneToOne
    @JoinColumn(name = "account_fk", nullable = false)
    private AccountTbl account;

    @Column(name = "service_type", length = 512)
    private String serviceType;

    @Column(name = "tariff_plan", length = 512)
    private String tariffPlan;

    @Column(name = "deposit", precision = 38, scale = 6)
    private BigDecimal deposit;

    @Column(name = "voucher_bar")
    private Integer voucherBar;

    @Column(name = "voucher_tries", precision = 38, scale = 6)
    private BigDecimal voucherTries;

    @Column(name = "voucher_bar_date")
    private LocalDateTime voucherBarDate;

    @Column(name = "voucher_bar_count", precision = 38, scale = 6)
    private BigDecimal voucherBarCount;

    @Column(name = "category", length = 512)
    private String category;

    @Column(name = "last_mod_date")
    private LocalDateTime lastModDate;

    @Column(name = "line_lvl", length = 512)
    private String lineLvl;

    @Column(name = "tariff_name", length = 512)
    private String tariffName;

    @Column(name = "access_group", length = 512)
    private String accessGroup;

    @Column(name = "address_code", length = 512)
    private String addressCode;

    @Column(name = "agent", length = 80)
    private String agent;

    @Column(name = "bank_mfo", length = 512)
    private String bankMfo;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "company_name", length = 512)
    private String companyName;

    @Column(name = "cont_app", length = 80)
    private String contApp;

    @Column(name = "cont_area", length = 80)
    private String contArea;

    @Column(name = "cont_city", length = 80)
    private String contCity;

    @Column(name = "cont_country", length = 80)
    private String contCountry;

    @Column(name = "cont_district", length = 80)
    private String contDistrict;

    @Column(name = "cont_house", length = 80)
    private String contHouse;

    @Column(name = "cont_house_corp", length = 80)
    private String contHouseCorp;

    @Column(name = "cont_index", length = 80)
    private String contIndex;

    @Column(name = "cont_personal_num", length = 80)
    private String contPersonalNum;

    @Column(name = "cont_street", length = 80)
    private String contStreet;

    @Column(name = "contract_date_from")
    private LocalDateTime contractDateFrom;

    @Column(name = "contract_id", length = 512)
    private String contractId;

    @Column(name = "contract_type", length = 80)
    private String contractType;

    @Column(name = "customer_type", length = 512)
    private String customerType;

    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "first_name", length = 512)
    private String firstName;

    @Column(name = "iccid", length = 50)
    private String iccid;

    @Column(name = "identification_code", length = 80)
    private String identificationCode;

    @Column(name = "invoice_delivery_type", length = 512)
    private String invoiceDeliveryType;

    @Column(name = "last_name", length = 512)
    private String lastName;

    @Column(name = "life_comanda", length = 80)
    private String lifeComanda;

    @Column(name = "mid_name", length = 512)
    private String midName;

    @Column(name = "nationality", length = 80)
    private String nationality;

    @Column(name = "never_bar")
    private Integer neverBar;

    @Column(name = "okpo_code", length = 512)
    private String okpoCode;

    @Column(name = "passport_authority", length = 512)
    private String passportAuthority;

    @Column(name = "passport_issue")
    private LocalDateTime passportIssue;

    @Column(name = "passport_no", length = 512)
    private String passportNo;

    @Column(name = "passport_series", length = 512)
    private String passportSeries;

    @Column(name = "passport_type", length = 80)
    private String passportType;

    @Column(name = "passport_valid_to")
    private LocalDateTime passportValidTo;

    @Column(name = "pin", length = 8)
    private String pin;

    @Column(name = "pin2", length = 8)
    private String pin2;

    @Column(name = "place_registration", length = 120)
    private String placeRegistration;

    @Column(name = "print_phone_number", length = 512)
    private String printPhoneNumber;

    @Column(name = "procuratory_issued", length = 120)
    private String procuratoryIssued;

    @Column(name = "procuratory_number", length = 120)
    private String procuratoryNumber;

    @Column(name = "puk", length = 16)
    private String puk;

    @Column(name = "puk2", length = 16)
    private String puk2;

    @Column(name = "representative_operator", length = 120)
    private String representativeOperator;

    @Column(name = "sale_point", length = 80)
    private String salePoint;

    @Column(name = "unp_code", length = 512)
    private String unpCode;

    @Column(name = "vat_code", length = 512)
    private String vatCode;

    @Column(name = "mobile_no", length = 15)
    private String mobileNo;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "corp_exec")
    private Integer corpExec;

    @Column(name = "region", length = 80)
    private String region;
}