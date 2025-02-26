package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.enums.RefundMoneyReason;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubscriberTerminationRequest {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    @Hidden
    private String msisdn;
    @Schema(description = "termination code from DICT_SUBS_TERM_TYPE", example = "TERMINATION_BY_WISH")
    @NotBlank
    private String terminationType;
    @Schema(description = "Agent code", example = "62962618")
    private String agentCode;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Agent login", example = "test@life.com.by")
    private String agent;
    @NotBlank
    private RefundMoneyReason refundReason;
    @Schema(description = "msisdn for money refund", example = "375256257415")
    private String msisdnForMoneyRefund;
    @Schema(description = "Termination Fee", example = "15")
    private Double terminationFee;
}
