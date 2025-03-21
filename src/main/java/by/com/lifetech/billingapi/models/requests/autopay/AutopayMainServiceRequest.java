package by.com.lifetech.billingapi.models.requests.autopay;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutopayMainServiceRequest extends AutopayServiceRequest{
    @Schema(description = "recipient mobile number", example = "375256257416")
    @NotBlank
    private String recipientMsisdn;
}