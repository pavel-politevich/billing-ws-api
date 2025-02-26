package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CheckByFioRequest {
    @Schema(description = "givenNames", example = "Николай")
    @NotEmpty
    private String givenNames;

    @Schema(description = "familyNames", example = "Иванов")
    @NotEmpty
    private String familyNames;

    @Schema(description = "middleNames", example = "Петрович")
    private String middleNames;

    @Schema(description = "Date of Birth", example = "2000-01-23T03:00:00.000+03:00")
    @NotNull
    private LocalDate aliveDuring;
}
