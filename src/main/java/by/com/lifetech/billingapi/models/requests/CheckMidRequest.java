package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckMidRequest extends CheckMvdNtecRequest {
    @Schema(description = "Marketing id (category)", example = "Start new COMM")
    @NotEmpty
    private String mid;

    @Schema(description = "MTariff code", example = "CRTBL")
    @NotEmpty
    private String tariffCode;
}
