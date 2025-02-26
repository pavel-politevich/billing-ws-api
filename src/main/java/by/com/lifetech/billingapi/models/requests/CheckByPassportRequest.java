package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CheckByPassportRequest {
    @Schema(description = "passport series", example = "MP")
    @NotEmpty
    private String passportSeries;
    @Schema(description = "passport number", example = "1234567")
    @NotEmpty
    private String passportNumber;
    @Schema(description = "identification number", example = "1478305C042PC8")
    private String identificationCode;
}
