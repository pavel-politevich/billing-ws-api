package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriberFullInfo extends SubscriberInfDto {
    private boolean advAgreement;
    private boolean  personalDataAgreement;
    private String balanceString;
    @Schema(description = "SIM, USIM or eSIM", example = "USIM")
    String cardType;
    String pin;
    String pin2;
    String puk;
    String puk2;
    String lang;
    private List<BalanceShortDto> mainBalances = new ArrayList<>();
    private EquipmentInformation equipmentInformation;
    private WarrantyServiceInfo warrantyServiceInfo;
}
