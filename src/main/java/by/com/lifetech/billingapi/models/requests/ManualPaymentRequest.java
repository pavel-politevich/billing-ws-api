package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.dto.ManualPayment;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManualPaymentRequest extends ManualPayment {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    private String msisdn;

    @Schema(description = "Contract code for CORPORATE payments", example = "3566980")
    private String agreementNo;

    @Schema(description = "Invoice No for CORPORATE payments", example = "123456789")
    private String invoiceNo;

    @Schema(description = "Bank branch code", example = "963")
    private String bankBranchCode;

    @Schema(description = "Bank code", example = "964")
    @NotBlank
    private String paymentAgentCode;

    @Schema(description = "FIO", example = "Иванов Иван")
    private String customerName;

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
