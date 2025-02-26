package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicTransactionRequest {
    @Schema(description = "Contract code of first subscriber", example = "3000125")
    @NotBlank
    @JsonProperty("aContractCode")
    private String aContractCode;

    @Schema(description = "Mobile number of first subscriber", example = "375256257001", requiredMode = Schema.RequiredMode.REQUIRED)
    @MsisdnDefaultCheck
    @JsonProperty("aMobileNo")
    private String aMobileNo;

    @Schema(description = "Contract code of second subscriber", example = "3000126")
    @JsonProperty("bContractCode")
    private String bContractCode;

    @Schema(description = "Mobile number of second subscriber", example = "375256257002")
    @JsonProperty("bMobileNo")
    private String bMobileNo;

    @Schema(description = "Transaction type code", example = "100001")
    @NotNull
    private Long transactionTypeCode;

    @Schema(description = "Transaction comment", example = "some comment")
    @NotNull
    private String comments;

    @Schema(description = "Agent name", example = "MOB")
    @NotBlank
    private String agentName;
}