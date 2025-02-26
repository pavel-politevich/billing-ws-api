package by.com.lifetech.billingapi.models.responses;

import by.com.lifetech.billingapi.models.dto.BalanceShortDto;
import by.com.lifetech.billingapi.models.dto.SearchSubscriberDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SearchForTerminationResponse extends SearchSubscriberDto {
    @Schema(description = "termination fee")
    double terminationFee;
    private List<BalanceShortDto> mainBalances = new ArrayList<>();

    public SearchForTerminationResponse(SearchSubscriberDto p) {
        super(p.getMsisdn(),
                p.getContractId(),
                p.getState(),
                p.getActivationDate(),
                p.getFirstName(),
                p.getLastName(),
                p.getMiddleName(),
                p.getCompanyName(),
                p.getContractType());
    }
}
