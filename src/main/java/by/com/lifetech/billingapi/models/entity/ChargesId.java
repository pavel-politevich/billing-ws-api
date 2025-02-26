package by.com.lifetech.billingapi.models.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargesId implements Serializable {
    private String tid;
    private LocalDateTime eventStart;
    private String servicePath;
}