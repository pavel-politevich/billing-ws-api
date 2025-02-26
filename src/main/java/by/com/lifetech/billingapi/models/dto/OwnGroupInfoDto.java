package by.com.lifetech.billingapi.models.dto;

import by.com.lifetech.billingapi.models.entity.OwnGroupHistory;
import by.com.lifetech.billingapi.models.entity.OwnGroupInfo;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Value
public class OwnGroupInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2980282401722362447L;

    List<OwnGroupInfo> groupInfo;
    List<OwnGroupHistory> groupHistory;
}