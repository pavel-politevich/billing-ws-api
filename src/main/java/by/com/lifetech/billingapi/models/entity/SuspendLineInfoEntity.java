package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SUSPEND_LINE_INFO", schema = "TM_CIM")
@NoArgsConstructor
@AllArgsConstructor
public class SuspendLineInfoEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "suspendLineInfoSeq")
    @SequenceGenerator(name = "suspendLineInfoSeq", sequenceName = "SEQ_SUSPEND_LI_ID", allocationSize = 1)
    private long id;

    @Column(name = "entry_date", columnDefinition = "DATE")
    private LocalDateTime entryDate;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "snapshot", columnDefinition = "BLOB")
    private byte[] snapshot;

    public SuspendLineInfoEntity setId(long id) {
        this.id = id;
        return this;
    }

    public SuspendLineInfoEntity setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public SuspendLineInfoEntity setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public SuspendLineInfoEntity setSnapshot(byte[] snapshot) {
        this.snapshot = snapshot;
        return this;
    }
}