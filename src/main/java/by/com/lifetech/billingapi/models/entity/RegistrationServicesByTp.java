package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistrationServicesByTp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OM_CODE")
    private String code;
    @Column(name = "NAME_RU")
    private String name;
    @Column(name = "SUB_GROUP")
    private String subGroup;
    @Column(name = "RADIO_GROUP")
    private String radioGroup;
    @Column(name = "COST")
    private String cost;
    @Column(name = "FREE_ACTIVATION")
    private String freeActivation;
}
