package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceShortDto {
    @Schema(description = "Balance code", example = "Counter_Remain_Obligation")
    private String code;
    @Schema(description = "Balance name", example = "Количество оставшихся обязательных подписок")
    private String name;
    private double amount;
    private double reserved;
}
