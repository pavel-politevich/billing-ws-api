package by.com.lifetech.billingapi.models.dto.service_response;

import by.com.lifetech.billingapi.configurations.properties.TransactionIdConfig;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseDto<T> {
    @JsonIgnore
    Logger logger = LoggerFactory.getLogger(ServiceResponseDto.class);

    private String resultCode;
    private String transactionId;
    private String resultDescription;
    private ServiceBusinessError businessError;
    private T resultMap;

    public ServiceResponseDto() {
        setTransactionIdFromMDC();
    }

    public ServiceResponseDto<T> setTransactionIdFromMDC() {
        try {
            transactionId = TransactionIdConfig.getTransactionId();
        } catch (Exception e) {
            logger.error("Error when get TransactionId: " + e.getMessage());
        }
        return this;
    }

    public ServiceResponseDto<T> setDefaultSuccessResponse() {
        resultCode = ServiceResultCode.SUCCESS.name();
        return this;
    }

    public ServiceResponseDto<T> setDefaultErrorResponse() {
        resultCode = ServiceResultCode.INTERNAL_ERROR.name();
        return this;
    }
}
