package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.dto.ManualPayment;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManualCorrectionContractRequest extends ManualPayment {
    @Schema(description = "Contract code for CORPORATE payments", example = "3566980")
    @NotBlank
    private String agreementNo;

    @Schema(description = "reference code")
    private String reference;

    @Schema(description = "typeCode from DICT_FINANCIAL_OPERATION", example = "MP")
    @NotBlank
    @Hidden
    private String typeCode;

    @Schema(description = "reasonCode from DICT_FINANCIAL_OPERATION", example = "99")
    @NotBlank
    @Hidden
    private String reasonCode;

    @Schema(description = "code from DICT_FINANCIAL_OPERATION", example = "MP62")
    @NotBlank
    private String finOpCode;

    public void setFinOpCode(@NotBlank String finOpCode) {
        this.finOpCode = finOpCode;
        this.typeCode = finOpCode.substring(0,2);
        this.reasonCode = finOpCode.substring(2);
    }
}
