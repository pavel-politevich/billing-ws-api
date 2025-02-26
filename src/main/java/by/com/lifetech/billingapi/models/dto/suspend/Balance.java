package by.com.lifetech.billingapi.models.dto.suspend;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Balance {
    private String balanceName;
    private String balanceType;
    private List<Pocket> pocketList;
}
