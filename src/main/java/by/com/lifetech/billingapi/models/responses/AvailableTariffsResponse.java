package by.com.lifetech.billingapi.models.responses;

import java.util.List;
import by.com.lifetech.billingapi.models.dto.TariffChangeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTariffsResponse {

	private List<TariffChangeDto> basicTariffs;
	private List<TariffChangeDto> specialTariffs;
}
