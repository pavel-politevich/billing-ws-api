package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class OwnGroupInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2931743188341552664L;

    @Id
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "OWNER_MSISDN")
    private String ownerMsisdn;
    @Column(name = "NAME_RU")
    private String userTypeName;
    @Column(name = "DISCOUNT")
    private String discount;
    @Column(name = "SUSPEND_DATE")
    private LocalDateTime suspendDate;
    @Column(name = "LEAVE_DATE")
    private LocalDateTime leaveDate;
}