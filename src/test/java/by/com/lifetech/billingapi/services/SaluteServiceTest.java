package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationSubscriberDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.SaluteServiceOperation;
import by.com.lifetech.billingapi.models.repository.ProductOfferingsRepository;
import by.com.lifetech.billingapi.models.requests.SaluteServiceRequest;
import by.com.lifetech.billingapi.models.responses.SaluteCheckActiveResponse;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaluteServiceTest {
    @Mock
    private CBossService cBossService;
    @Mock
    private ExceptionUtils exceptionUtils;
    @Mock
    private ProductOfferingsRepository productOfferingsRepository;
    @Mock
    private ChainService chainService;

    @InjectMocks
    private SaluteService saluteService;

    @ParameterizedTest
    @ValueSource(strings = {"INFPROMAX", "BKASSA"})
    @DisplayName("Get result of Salute check active availability for not available tariff")
    void isSubscriptionActive_whenNotAvailableTariff_thenReturnResponseWithZeroStatus(String input) throws Exception {
        GetInformationSubscriberDto informationSubscriber = new GetInformationSubscriberDto();
        informationSubscriber.setTariffPlan(input);
        GetInformationDto getInformation = new GetInformationDto();
        getInformation.setSubscriber(informationSubscriber);
        SaluteCheckActiveResponse expectedCheckActiveResponse = new SaluteCheckActiveResponse(0, false);
        when(cBossService.getInformation("375256257275")).thenReturn(getInformation);

        SaluteCheckActiveResponse actualCheckActiveResponse = saluteService.isSubscriptionActive("375256257275");

        assertEquals(expectedCheckActiveResponse, actualCheckActiveResponse);
        verify(cBossService, times(1)).getInformation(anyString());
    }

    @Test
    @DisplayName("Get result of Salute check active availability for available tariff")
    void isSubscriptionActive_whenAvailableTariff_thenReturnResponseWithActiveStatus() throws Exception {
        GetInformationSubscriberDto informationSubscriber = new GetInformationSubscriberDto();
        informationSubscriber.setTariffPlan("INF");
        GetInformationDto getInformation = new GetInformationDto();
        getInformation.setSubscriber(informationSubscriber);
        SaluteCheckActiveResponse expectedCheckActiveResponse = new SaluteCheckActiveResponse(1, false);
        when(cBossService.getInformation("375256257275")).thenReturn(getInformation);

        SaluteCheckActiveResponse actualCheckActiveResponse = saluteService.isSubscriptionActive("375256257275");

        assertEquals(expectedCheckActiveResponse, actualCheckActiveResponse);
        verify(cBossService, times(1)).getInformation(anyString());
    }

    @Test
    @DisplayName("Throw exception for Salute check active availability when subscriber not found")
    void isSubscriptionActive_whenSubscriberNotFound_thenThrowMsisdnNotFoundException() throws Exception {
        GetInformationDto getInformation = new GetInformationDto();
        BusinessException expectedBusinessException = new BusinessException("ERR40001", "MSISDN не найден");
        when(cBossService.getInformation("375256257275")).thenReturn(getInformation);
        when(exceptionUtils.getMsisdnNotFoundException()).thenReturn(expectedBusinessException);

        BusinessException actualBusinessException = assertThrows(BusinessException.class, () -> {
            saluteService.isSubscriptionActive("375256257275");
        });

        assertEquals(expectedBusinessException, actualBusinessException);
        verify(cBossService, times(1)).getInformation(anyString());
        verify(exceptionUtils, times(1)).getMsisdnNotFoundException();
    }

    @Test
    @DisplayName("Execute Salute operation for existed Salute's profile")
    void operateSaluteService_whenSaluteServiceExist_thenExecuteOperation() throws Exception {
        SaluteServiceRequest request = new SaluteServiceRequest("375256257275", "0", SaluteServiceOperation.ACT);
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", "375256257275");
        map.put("productOfferingCode", "S_SALUTE_DEACT");
        map.put("channel", "SALUTE");
        when(productOfferingsRepository.getProductCodeByAttributeValue("SALUTE_PROFILE_ID", "0"))
                .thenReturn(Optional.of("S_SALUTE_DEACT"));
        when(chainService.executeChain(ChainType.OM, SaluteServiceOperation.ACT.getChainName(), map))
                .thenReturn(new ChainResult());

        ServiceResponseDto<Map<String, Object>> actualResponse = saluteService.operateSaluteService(request);

        assertEquals("SUCCESS", actualResponse.getResultCode());
        verify(productOfferingsRepository, times(1))
                .getProductCodeByAttributeValue(anyString(), anyString());
        verify(chainService, times(1))
                .executeChain(eq(ChainType.OM), anyString(), any());
    }

    @Test
    @DisplayName("Throw BusinessException for Salute operation call when not existed profile")
    void operateSaluteService_whenSaluteServiceNotExist_thenThrowBusinessException() throws Exception {
        SaluteServiceRequest request = new SaluteServiceRequest("375256257275", "-10", SaluteServiceOperation.ACT);
        when(productOfferingsRepository.getProductCodeByAttributeValue("SALUTE_PROFILE_ID", "-10"))
                .thenReturn(Optional.empty());

        BusinessException actualBusinessException = assertThrows(BusinessException.class, () -> {
            saluteService.operateSaluteService(request);
        });

        assertEquals("PROFILE_ID_NOT_FOUND", actualBusinessException.getError().getErrorName());
        verify(productOfferingsRepository, times(1)).getProductCodeByAttributeValue(anyString(), anyString());
    }
}