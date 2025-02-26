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
public class OwnGroupHistory implements Serializable {
    @Serial
    private static final long serialVersionUID = -7663512785124007343L;

    @Id
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "OWNER_MSISDN")
    private String ownerMsisdn;
    @Column(name = "NAME_RU")
    private String userTypeName;
    @Column(name = "ENTER_DATE")
    private LocalDateTime enterDate;
    @Column(name = "LEAVE_DATE")
    private LocalDateTime leaveDate;
}