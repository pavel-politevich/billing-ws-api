package by.com.lifetech.billingapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoInstanceId {
    private String deviceCode;
    private String productCode;
    private String tariffCode;
    private String attrCode;
}
