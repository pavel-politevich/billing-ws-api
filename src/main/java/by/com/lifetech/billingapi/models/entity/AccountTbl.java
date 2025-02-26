package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ACCOUNT_TBL", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class AccountTbl {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "customer_fk")
    private Long customerFk;

    @Column(name = "account_hierarchy_fk")
    private Long accountHierarchyFk;

    @Column(name = "billing_address_ref")
    private Long billingAddressRef;

    @ManyToOne
    @JoinColumn(name = "top_level_account_id")
    private AccountTbl topLevelAccount;

    @Column(name = "account_type", length = 3)
    private String accountType;

    @Column(name = "account_number", length = 64)
    private String accountNumber;

    @Column(name = "account_name", length = 64)
    private String accountName;

    @Column(length = 512)
    private String description;

    @Column(name = "last_reload_date")
    private LocalDateTime lastReloadDate;

    @Column(name = "payment_responsibility")
    private Integer paymentResponsibility;

    @Column(name = "last_mod_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "lc_state", length = 3)
    private String lcState;

    @Column(name = "lc_sub_state_code_id", length = 10)
    private String lcSubStateCodeId;

    @Column(name = "lc_date")
    private LocalDateTime lcDate;

    @Column(name = "cre_threshold_seq_no")
    private Integer creThresholdSeqNo;

    @Column(name = "cre_threshold_date")
    private LocalDateTime creThresholdDate;

    @Column(name = "dealer_code_id", length = 10)
    private String dealerCodeId;

    @Column(name = "language_code_id", length = 10)
    private String languageCodeId;

    @Column(name = "operator_code_id", length = 10)
    private String operatorCodeId;

    @Column(name = "lc_reason_code_id", length = 10)
    private String lcReasonCodeId;

    @Column(name = "first_act_date")
    private LocalDateTime firstActDate;

    @Column(name = "last_act_date")
    private LocalDateTime lastActDate;

    @Column(name = "last_deact_date")
    private LocalDateTime lastDeactDate;

    @Column(name = "rop_component_count")
    private Integer ropComponentCount;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "expiry_type", length = 64)
    private String expiryType;

    @Column(name = "last_reload_source", length = 64)
    private String lastReloadSource;

    @Column(name = "ol_version")
    private Long olVersion = 0L;

    @Column(name = "credit_limit", precision = 38, scale = 6)
    private BigDecimal creditLimit;

    @Column(name = "parent_account_fk")
    private Long parentAccountFk;

    @Column(name = "parent_account_type", length = 3)
    private String parentAccountType;

    @Column(name = "cre_profile_version")
    private Long creProfileVersion = 0L;

    @Column(name = "rte_instance")
    private Integer rteInstance = 1;

    @Column(name = "dealer_code_ind")
    private Integer dealerCodeInd;

    @Column(name = "account_category_code_id", length = 10)
    private String accountCategoryCodeId;

    @Column(name = "account_category_ind")
    private Integer accountCategoryInd;

    @Column(name = "segment_code_id", length = 10)
    private String segmentCodeId;

    @Column(name = "segment_code_ind")
    private Integer segmentCodeInd;

    @Column(name = "market_code_id", length = 10)
    private String marketCodeId;

    @Column(name = "market_code_ind")
    private Integer marketCodeInd;

    @Column(name = "channel_code_id", length = 10)
    private String channelCodeId;

    @Column(name = "channel_code_ind")
    private Integer channelCodeInd;

    @Column(name = "vip_code_id", length = 10)
    private String vipCodeId;

    @Column(name = "vip_code_ind")
    private Integer vipCodeInd;

    @Column(name = "sc_profile_code_id", length = 10)
    private String scProfileCodeId;

    @Column(name = "sc_profile_code_ind")
    private Integer scProfileCodeInd;

    @Column(name = "com_profile_code_id", length = 10)
    private String comProfileCodeId;

    @Column(name = "com_profile_code_ind")
    private Integer comProfileCodeInd;

    @Column(name = "currency_code_id", length = 10)
    private String currencyCodeId;

    @Column(name = "credit_rating_code_id", length = 10)
    private String creditRatingCodeId;

    @Column(name = "credit_rating_code_ind")
    private Integer creditRatingCodeInd;

    @Column(name = "ext_collection_status", length = 240)
    private String extCollectionStatus;

    @Column(name = "ext_collection_date")
    private LocalDateTime extCollectionDate;

    @Column(name = "ext_collection_annotation", length = 240)
    private String extCollectionAnnotation;

    @Column(name = "owning_cc_code_id", length = 10)
    private String owningCcCodeId;

    @Column(name = "receiving_cc_code_id", length = 10)
    private String receivingCcCodeId;

    @Column(name = "hist_ind")
    private Integer histInd = 1;

    @Column(name = "deploy_date")
    private LocalDateTime deployDate;
}
