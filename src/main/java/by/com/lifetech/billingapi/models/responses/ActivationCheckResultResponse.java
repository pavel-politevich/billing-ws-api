package by.com.lifetech.billingapi.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivationCheckResultResponse {
    @Schema(description = "is check passed")
    boolean passed;
    List<String> errorList = new ArrayList<>();
}
