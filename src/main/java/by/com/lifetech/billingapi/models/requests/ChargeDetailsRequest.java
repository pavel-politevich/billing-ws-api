package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.enums.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChargeDetailsRequest {
    @Schema(description = "accountId for LINE or CONTRACT", example = "20699")
    @NotNull
    Long accountId;

    @Schema(description = "date format yyyy-mm-ddThh:mm:ss")
    @NotNull
    private LocalDateTime startTime;

    @Schema(description = "transaction id", example = "qEEVf056w")
    @NotNull
    private String tid;

    @NotNull
    private Lang lang;
}
