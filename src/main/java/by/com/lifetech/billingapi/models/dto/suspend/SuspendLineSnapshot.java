package by.com.lifetech.billingapi.models.dto.suspend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuspendLineSnapshot {
    private List<LifeCycle> accountLCList;
    private List<Balance> balanceList;
    private List<Product> productList;
}
