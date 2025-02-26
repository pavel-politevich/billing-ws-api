package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Active service")
public class ActiveServiceDto {
    @Schema(description = "Service om-code", example = "S_SMS_100")
    String code;
    @Schema(description = "Service name for channel", example = "life:) СМС 100")
    String name;
    @Schema(description = "Service cost with discount", example = "4.99")
    double cost;
    @Schema(description = "Service cost in billing", example = "5.90")
    double fullCost;
    @Schema(description = "state code: ACTIVE/GRACE", example = "ACTIVE")
    String state;
    Boolean reactivation;
    LocalDateTime dateFrom;
    LocalDateTime dateTo;

    public void setDateFrom(String dateFrom) {
        if (dateFrom != null && !dateFrom.isBlank() && dateFrom.contains("+")) {
            this.dateFrom = LocalDateTime.parse(dateFrom.substring(0, dateFrom.indexOf("+")));
        }
    }
    public void setDateTo(String dateTo) {
        if (dateTo != null && !dateTo.isBlank() && dateTo.contains("+")) {
            this.dateTo = LocalDateTime.parse(dateTo.substring(0, dateTo.indexOf("+")));
        }
    }
}
