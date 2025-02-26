package by.com.lifetech.billingapi.models.dto.service_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBusinessError implements Serializable {
    @Serial
    private static final long serialVersionUID = 6150961040959340830L;

    private String errorName;
    private String errorMessage;
}
