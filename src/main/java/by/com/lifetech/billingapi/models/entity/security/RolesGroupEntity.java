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
@Table(name = "ROLES_GROUP", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class RolesGroupEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8759325928938887714L;
    @Id
    @Column(name = "code")
    private String code;
}