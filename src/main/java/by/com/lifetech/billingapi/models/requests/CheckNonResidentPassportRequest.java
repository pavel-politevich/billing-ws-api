package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckNonResidentPassportRequest {
    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "givenNames", example = "Николай")
    @NotNull
    private String givenNames;

    @Schema(description = "familyNames", example = "Иванов")
    @NotNull
    private String familyNames;

    @Schema(description = "passport series", example = "MP")
    @NotNull
    private String passportSeries;

    @Schema(description = "passport number", example = "1234567")
    @NotNull
    private String passportNumber;
}
