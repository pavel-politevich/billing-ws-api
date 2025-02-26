package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ManualPayment {
    @Schema(description = "payment amount", example = "1.25")
    @NotNull
    private BigDecimal amount;

    @Schema(description = "date of payment")
    @NotNull
    private LocalDateTime paymentDate;

    @Schema(description = "agent login", example = "test.test@life.com.by")
    private String channelUser;

    @Schema(description = "document number", example = "123")
    private String documentNo;

    @Schema(description = "comments")
    private String comments;
}
