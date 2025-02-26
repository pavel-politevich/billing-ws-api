package by.com.lifetech.billingapi.models.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SERVICES", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class ServicesEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5870729930273819402L;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "service_path")
    private String servicePath;
}