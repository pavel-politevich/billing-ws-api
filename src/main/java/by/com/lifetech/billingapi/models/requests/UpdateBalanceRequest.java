package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalanceRequest {
    @Schema(description = "Balance code", example = "Bundle_GPRS_Bonus")
    @NotBlank
    private String balanceCode;

    @Schema(description = "Pocket label code", example = "TL_INFPRO")
    private String pocketCode;

    @NotNull
    private BigDecimal amount;

    @Schema(description = "User agent code", example = "user.test@life.com.by")
    @NotBlank
    private String agent;

    @Schema(description = "Millisecond not allowed")
    private LocalDateTime startDate;
    @Schema(description = "Millisecond not allowed")
    private LocalDateTime endDate;
    private String comment;
}
