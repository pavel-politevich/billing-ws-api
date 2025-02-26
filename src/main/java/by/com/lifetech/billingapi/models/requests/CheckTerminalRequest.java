package by.com.lifetech.billingapi.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckTerminalRequest extends CheckByPassportRequest{
    @Schema(description = "Marketing id terminal", example = "12_HonorChoiceX5_19.09.2023_1mdisc_40.00_1")
    @NotEmpty
    @JsonProperty(value = "category")
    private String mid;
}
