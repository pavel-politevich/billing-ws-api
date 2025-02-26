package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.BalanceInfDto;
import by.com.lifetech.billingapi.models.dto.OwnGroupInfoDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.MultiGroupData;
import by.com.lifetech.billingapi.models.entity.OwnGroupHistory;
import by.com.lifetech.billingapi.models.entity.OwnGroupInfo;
import by.com.lifetech.billingapi.utils.SqlConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@RequiredArgsConstructor
public class SubscriberGroupsService {
    @PersistenceContext
    private EntityManager entityManager;
    private final CBossService cBossService;
    Logger logger = LoggerFactory.getLogger(SubscriberGroupsService.class);

    public ServiceResponseDto<List<MultiGroupData>> getMultiGroupData(String msisdn) {
        List<MultiGroupData> groupList;
        groupList = entityManager.createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class)
                .setParameter(1, msisdn).getResultList();
        try {
            cBossService.getBalances(msisdn).forEach(b -> {
                BalanceInfDto balInf = new BalanceInfDto(b);
                groupList.forEach(l -> {
                    if (l.getType().equals("BALANCE") && l.getBalanceCode().equals(balInf.getCode())) {
                        l.setBalanceAmount(balInf.getAmount().doubleValue());
                        l.setBalanceDateFrom(b.getStart());
                        l.setBalanceDateTo(b.getEnd());
                    }
                });
            });
        } catch (BusinessException | InternalException e) {
            logger.warn("Can not retrieve balances info from c_boss");
        }
        ServiceResponseDto<List<MultiGroupData>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(groupList);
        return serviceResponse;
    }

    public ServiceResponseDto<OwnGroupInfoDto> getOwnGroupData(String msisdn) throws BusinessException, InternalException {
        String accountId = cBossService.getAccountId(msisdn);
        List<OwnGroupInfo> groupInfos = entityManager.createNativeQuery(SqlConstants.OWN_GROUP_INF, OwnGroupInfo.class)
                .setParameter(1, accountId).getResultList();
        List<OwnGroupHistory> groupHistories = entityManager.createNativeQuery(SqlConstants.OWN_GROUP_HST, OwnGroupHistory.class)
                .setParameter(1, accountId).getResultList();
        OwnGroupInfoDto groupData = new OwnGroupInfoDto(groupInfos, groupHistories);
        ServiceResponseDto<OwnGroupInfoDto> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(groupData);
        return serviceResponse;
    }
}
