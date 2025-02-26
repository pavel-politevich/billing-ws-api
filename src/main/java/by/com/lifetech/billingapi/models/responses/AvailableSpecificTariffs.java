package by.com.lifetech.billingapi.models.responses;

import by.com.lifetech.billingapi.models.dto.TariffChangeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSpecificTariffs {
    private List<TariffChangeDto> specificTariffs;
}
