package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.enums.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDetailsRequest {
    @NotNull
    private LocalDateTime entryDate;

    @Schema(description = "financial history code", example = "740628405395")
    @NotNull
    private String finCode;

    @NotNull
    private Lang lang;
}
