package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Info about iPay transaction")
public class IPayFinancialTransactionDto {

    @Schema(description = "iPay online transactions code", example = "302")
    private String code;

    @Schema(example = "740725407348")
    private String financialHistoryCode;

    @Schema(example = "700108236182")
    private String financialLinkedCode;

    @Schema(example = "IOT_DEBIT")
    private String operationTypeCode;

    @Schema(example = "2")
    private String operationResponseCode;
}
