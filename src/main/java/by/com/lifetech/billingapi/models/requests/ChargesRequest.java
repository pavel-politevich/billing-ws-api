package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.enums.ChargeSearchType;
import by.com.lifetech.billingapi.models.enums.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChargesRequest {
    @Schema(description = "accountId for LINE or CONTRACT", example = "20699")
    @NotNull
    Long accountId;

    @Schema(description = "date format yyyy-mm-ddThh:mm:ss")
    @NotNull
    private LocalDateTime startDate;

    @Schema(description = "date format yyyy-mm-ddThh:mm:ss")
    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Schema(description = "type of Search", example = "LINE")
    private ChargeSearchType searchType;

    @Schema(description = "eventGroupCode from dict", example = "VAS")
    private String eventGroupCode;

    public Boolean getIncludeAllLines() {
        return includeAllLines != null && includeAllLines;
    }

    public Boolean getOnlyPaidEvents() {
        return onlyPaidEvents != null && onlyPaidEvents;
    }

    @Schema(description = "for CORPORATE subscribers. Get charges by all lines", example = "false")
    private Boolean includeAllLines;

    @Schema(description = "get only events with charge (paid)")
    private Boolean onlyPaidEvents;

    @NotNull
    private Lang lang;
}
