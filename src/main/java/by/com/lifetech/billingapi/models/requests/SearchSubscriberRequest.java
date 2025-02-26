package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.InputParametersCheckingConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SearchSubscriberRequest {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String msisdn;
    @Schema(description = "First name", example = "Иван")
    private String firstName;
    @Schema(description = "Patronymic ", example = "Иванович")
    private String middleName;
    @Schema(description = "Last name", example = "Иванов")
    private String lastName;
    @Schema(description = "Passport series", example = "MP")
    private String passportSeries;
    @Schema(description = "Passport number", example = "784939")
    private String passportNo;
}
