package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class RegServicesActivationRequest {
    @Schema(description = "msisdn", example = "375256257415")
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "List of services")
    @NotEmpty
    private List<String> productList;
}
