package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MultiGroupData {
    @Id
    @Column(name = "KEY")
    @JsonIgnore
    private String key;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "STATUS")
    private String stateCode;
    @Column(name = "BALANCE_NAME")
    private String balanceCode;
    @Column(name = "BALANCE_TYPE")
    private String balanceType;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "DATE_FROM")
    private LocalDateTime dateFrom;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "POCKET_LABEL")
    private String pocketCode;
    @Column(name = "BALANCE_NAME_RU")
    private String balanceName;
    @Column(name = "STATE_NAME_RU")
    private String stateName;
    @Column(name = "PL_NAME_RU")
    private String pocketName;
    @Transient
    private LocalDateTime balanceDateFrom;
    @Transient
    private LocalDateTime balanceDateTo;
    @Transient
    private Double balanceAmount;
}