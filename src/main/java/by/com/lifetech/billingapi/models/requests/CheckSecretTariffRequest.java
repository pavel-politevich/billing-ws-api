package by.com.lifetech.billingapi.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CheckSecretTariffRequest {

    @Schema(description = "identification number", example = "1478305C042PC8")
    @JsonProperty("idnumber")
    private String identificationCode;

    @Schema(description = "Marketing id (category)", example = "Start new COMM")
    private String mid;

    @Schema(description = "tariff code code", example = "CRTBL")
    @JsonProperty("tariff_plan")
    private String tariffCode;

    @Schema(description = "givenNames", example = "Николай")
    @NotEmpty
    @JsonProperty("firstname")
    private String firstName;

    @Schema(description = "familyNames", example = "Иванов")
    @NotEmpty
    @JsonProperty("lastname")
    private String lastName;

    @Schema(description = "middleNames", example = "Петрович")
    @JsonProperty("middlename")
    private String middleName;

    @Schema(description = "Date of Birth")
    private LocalDate birthday;

    @Schema(description = "Point of sale", example = "8464381")
    @JsonProperty("sales_point")
    private String pointOfSaleCode;
}
