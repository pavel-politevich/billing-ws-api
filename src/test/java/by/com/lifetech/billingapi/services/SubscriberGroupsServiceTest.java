package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.OwnGroupInfoDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.MultiGroupData;
import by.com.lifetech.billingapi.models.entity.OwnGroupHistory;
import by.com.lifetech.billingapi.models.entity.OwnGroupInfo;
import by.com.lifetech.billingapi.utils.SqlConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriberGroupsServiceTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private CBossService cBossService;
    @Mock
    private Query mockQuery;

    @Mock
    private Query mockSecondQuery;

    @InjectMocks
    SubscriberGroupsService subscriberGroupsService;

    @BeforeEach
    void setUp() throws Exception {
        Field entityManagerField = SubscriberGroupsService.class.getDeclaredField("entityManager");
        entityManagerField.setAccessible(true);
        entityManagerField.set(subscriberGroupsService, entityManager);
    }

    @Test
    @DisplayName("Return multi group list for founded balances")
    void getMultiGroupData_whenBalanceFound_thenReturnResponse() throws Exception {
        MultiGroupData multiGroupData = getMultiGroupDataWithNameAndType("Bundle_GPRS_Shared", "BALANCE");
        GetInformationBalanceDto informationBalanceDto = getInformationBalanceDto("Bundle_GPRS_Shared", "TL_SMP1", 1.0);
        when(entityManager.createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(multiGroupData));
        when(cBossService.getBalances(anyString())).thenReturn(List.of(informationBalanceDto));
        ServiceResponseDto<List<MultiGroupData>>expectedResponse = new ServiceResponseDto<List<MultiGroupData>>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(List.of(multiGroupData));

        ServiceResponseDto<List<MultiGroupData>> actualResponse = subscriberGroupsService.getMultiGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap().get(0).getBalanceCode(),
                actualResponse.getResultMap().get(0).getBalanceCode());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class);
        verify(cBossService, times(1)).getBalances(anyString());
    }

    @Test
    @DisplayName("Return multi group list for not balance type")
    void getMultiGroupData_whenNotBalanceType_thenReturnResponse() throws Exception {
        MultiGroupData multiGroupData = getMultiGroupDataWithNameAndType("", "HISTORY");
        GetInformationBalanceDto informationBalanceDto = getInformationBalanceDto("Bundle_GPRS_Shared", "TL_SMP1", 1.0);
        when(entityManager.createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(multiGroupData));
        when(cBossService.getBalances(anyString())).thenReturn(List.of(informationBalanceDto));
        ServiceResponseDto<List<MultiGroupData>>expectedResponse = new ServiceResponseDto<List<MultiGroupData>>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(List.of(multiGroupData));

        ServiceResponseDto<List<MultiGroupData>> actualResponse = subscriberGroupsService.getMultiGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap().get(0).getBalanceCode(),
                actualResponse.getResultMap().get(0).getBalanceCode());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class);
        verify(cBossService, times(1)).getBalances(anyString());
    }

    @Test
    @DisplayName("Return multi group list for not founded balances")
    void getMultiGroupData_whenBalanceNotFound_thenReturnResponse() throws Exception {
        MultiGroupData multiGroupData = getMultiGroupDataWithNameAndType("Bundle_GPRS_Shared", "BALANCE");
        GetInformationBalanceDto informationBalanceDto = getInformationBalanceDto("Bundle_Flag_GPRS", "TL_SMP1", 1.0);
        when(entityManager.createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(multiGroupData));
        when(cBossService.getBalances(anyString())).thenReturn(List.of(informationBalanceDto));
        ServiceResponseDto<List<MultiGroupData>>expectedResponse = new ServiceResponseDto<List<MultiGroupData>>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(List.of(multiGroupData));

        ServiceResponseDto<List<MultiGroupData>> actualResponse = subscriberGroupsService.getMultiGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap().get(0).getBalanceCode(),
                actualResponse.getResultMap().get(0).getBalanceCode());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class);
        verify(cBossService, times(1)).getBalances(anyString());
    }

    @Test
    @DisplayName("Return multi group list when cboss throw exception")
    void getMultiGroupData_whenCbossThrowException_thenReturnResponse() throws Exception {
        MultiGroupData multiGroupData = getMultiGroupDataWithNameAndType("Bundle_GPRS_Shared", "BALANCE");
        when(entityManager.createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class))
                .thenReturn(mockQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(multiGroupData));
        when(cBossService.getBalances(anyString())).thenThrow(new BusinessException("", ""));
        ServiceResponseDto<List<MultiGroupData>>expectedResponse = new ServiceResponseDto<List<MultiGroupData>>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(List.of(multiGroupData));

        ServiceResponseDto<List<MultiGroupData>> actualResponse = subscriberGroupsService.getMultiGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap().get(0).getBalanceCode(),
                actualResponse.getResultMap().get(0).getBalanceCode());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.MULTI_GROUP_SQL, MultiGroupData.class);
        verify(cBossService, times(1)).getBalances(anyString());
    }

    private GetInformationBalanceDto getInformationBalanceDto(String name, String label, double value) {
        GetInformationBalanceDto informationBalanceDto = new GetInformationBalanceDto();
        informationBalanceDto.setBalanceName(name);
        informationBalanceDto.setLabel(label);
        informationBalanceDto.setValue(value);
        informationBalanceDto.setReserved(0.0);
        informationBalanceDto.setStart(LocalDateTime.now());
        informationBalanceDto.setEnd(LocalDateTime.now().plusDays(30));
        return informationBalanceDto;
    }

    private MultiGroupData getMultiGroupDataWithNameAndType(String name, String type) {
        MultiGroupData multiGroupData = new MultiGroupData();
        multiGroupData.setType(type);
        multiGroupData.setBalanceCode(name);
        return multiGroupData;
    }

    @Test
    @DisplayName("Return own group info when found data")
    void getOwnGroupData_whenOwnGroupDataFounded_thenReturnResponse() throws Exception {
        OwnGroupInfoDto ownGroupInfoDto = new OwnGroupInfoDto(List.of(new OwnGroupInfo()), List.of(new OwnGroupHistory()));
        when(entityManager.createNativeQuery(SqlConstants.OWN_GROUP_INF, OwnGroupInfo.class))
                .thenReturn(mockQuery);
        when(entityManager.createNativeQuery(SqlConstants.OWN_GROUP_HST, OwnGroupHistory.class))
                .thenReturn(mockSecondQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(ownGroupInfoDto.getGroupInfo());
        when(mockSecondQuery.setParameter(anyInt(), any())).thenReturn(mockSecondQuery);
        when(mockSecondQuery.getResultList()).thenReturn(ownGroupInfoDto.getGroupHistory());
        when(cBossService.getAccountId(anyString())).thenReturn("3002001");
        ServiceResponseDto<OwnGroupInfoDto> expectedResponse = new ServiceResponseDto<OwnGroupInfoDto>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(ownGroupInfoDto);

        ServiceResponseDto<OwnGroupInfoDto> actualResponse = subscriberGroupsService.getOwnGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap(), actualResponse.getResultMap());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.OWN_GROUP_INF, OwnGroupInfo.class);
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.OWN_GROUP_HST, OwnGroupHistory.class);
        verify(cBossService, times(1)).getAccountId(anyString());
    }

    @Test
    @DisplayName("Return response when not data found")
    void getOwnGroupData_whenOwnGroupDataNotFound_thenReturnResponse() throws Exception {
        OwnGroupInfoDto ownGroupInfoDto = new OwnGroupInfoDto(new ArrayList<>(), new ArrayList<>());
        when(entityManager.createNativeQuery(SqlConstants.OWN_GROUP_INF, OwnGroupInfo.class))
                .thenReturn(mockQuery);
        when(entityManager.createNativeQuery(SqlConstants.OWN_GROUP_HST, OwnGroupHistory.class))
                .thenReturn(mockSecondQuery);
        when(mockQuery.setParameter(anyInt(), any())).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(ownGroupInfoDto.getGroupInfo());
        when(mockSecondQuery.setParameter(anyInt(), any())).thenReturn(mockSecondQuery);
        when(mockSecondQuery.getResultList()).thenReturn(ownGroupInfoDto.getGroupHistory());
        when(cBossService.getAccountId(anyString())).thenReturn("3002001");
        ServiceResponseDto<OwnGroupInfoDto> expectedResponse = new ServiceResponseDto<OwnGroupInfoDto>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(ownGroupInfoDto);

        ServiceResponseDto<OwnGroupInfoDto> actualResponse = subscriberGroupsService.getOwnGroupData("375256257275");

        assertEquals(expectedResponse.getResultMap(), actualResponse.getResultMap());
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.OWN_GROUP_INF, OwnGroupInfo.class);
        verify(entityManager, times(1)).createNativeQuery(SqlConstants.OWN_GROUP_HST, OwnGroupHistory.class);
        verify(cBossService, times(1)).getAccountId(anyString());
    }
}