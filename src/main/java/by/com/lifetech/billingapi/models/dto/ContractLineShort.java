package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractLineShort {
    private String msisdn;
    private String iccid;
    private SubscriberStateDto state;
    private TariffDto tariff;
    private LineLevelDto lineLevel;
}
