package by.com.lifetech.billingapi.models.entity.security;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SERVICES", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class ServicesEntity {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "service_path")
    private String servicePath;

    @Column(name = "definition")
    private String definition;

    @Column(name = "change_desc")
    private String changeDesc;

    @Column(name = "change_date")
    private LocalDateTime changeDate;
}