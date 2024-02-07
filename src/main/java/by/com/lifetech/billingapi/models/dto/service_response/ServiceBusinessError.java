package by.com.lifetech.billingapi.models.dto.service_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBusinessError {
    private String errorName;
    private String errorMessage;
}
