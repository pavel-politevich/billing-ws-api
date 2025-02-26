package by.com.lifetech.billingapi.models.dto.service_response;

import by.com.lifetech.billingapi.configurations.properties.LogInfoConfig;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseDto<T> {
    @JsonIgnore
    Logger logger = LoggerFactory.getLogger(ServiceResponseDto.class);

    private String resultCode;
    private String transactionId;
    @Schema(description = "The object may be missing")
    private String resultDescription;
    @Schema(description = "The object may be missing")
    private ServiceBusinessError businessError;
    @Schema(description = "The object may be missing")
    private T resultMap;

    public ServiceResponseDto() {
        setTransactionIdFromMDC();
    }

    public ServiceResponseDto<T> setTransactionIdFromMDC() {
        try {
            transactionId = LogInfoConfig.getTransactionId();
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

    public ServiceResponseDto<T> getDefaultErrorResponseWithDescription(String description) {
        resultDescription = description;
        return setDefaultErrorResponse();
    }

    @Override
    public String toString() {
        return "ServiceResponseDto(" +
                "resultCode=" + resultCode +
                ", transactionId=" + transactionId +
                (resultDescription == null ? "" : ", resultDescription=" + resultDescription) +
                (businessError == null ? "" : ", businessError=" + businessError) +
                (resultMap == null ? "" : ", resultMap=" + resultMap) +
                ')';
    }
}
