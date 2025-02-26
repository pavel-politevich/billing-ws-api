package by.com.lifetech.billingapi.models.dto;

import by.com.lifetech.billingapi.models.entity.dictionary.DictChargingEventGroup;
import by.com.lifetech.billingapi.models.enums.Lang;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargingEventGroupDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1145227342633872697L;

    @Schema(description = "group code", example = "GPRS_FACEBOOK")
    private String id;
    @Schema(description = "group name", example = "Трафик (Facebook)")
    private String name;
    @Schema(description = "parent group code", example = "DIGITAL")
    private String parentId;
    @Schema(description = "children")
    private List<ChargingEventGroupDto> children;

    public ChargingEventGroupDto(DictChargingEventGroup dict, Lang lang) {
        this.id = dict.getCode();
        this.parentId = dict.getParentCode();
        this.name = dict.getNameByLang(lang);
        this.children = new ArrayList<>();
    }
}
