package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckMvdNtecRequest extends CheckByPassportRequest {
    @Schema(description = "passport type", example = "PASSPORT_RB")
    @NotEmpty
    private String passportType;

    @Schema(description = "givenNames", example = "Николай")
    @NotEmpty
    private String givenNames;

    @Schema(description = "familyNames", example = "Иванов")
    @NotEmpty
    private String familyNames;

    @Schema(description = "middleNames", example = "Петрович")
    private String middleNames;

    @Schema(description = "Agent code", example = "62962618")
    @NotEmpty
    private String representativeOfOperator;

    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;

    @Schema(description = "Date of Birth", example = "2000-01-23T03:00:00.000+03:00")
    @NotNull
    private LocalDate aliveDuring;
}
