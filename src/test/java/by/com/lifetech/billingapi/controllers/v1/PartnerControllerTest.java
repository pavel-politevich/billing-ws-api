package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.models.dto.ipay.SubscriberFIODto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.SaluteServiceOperation;
import by.com.lifetech.billingapi.models.requests.IPayChargingRequest;
import by.com.lifetech.billingapi.models.requests.IPayMoneyBackRequest;
import by.com.lifetech.billingapi.models.requests.SaluteServiceRequest;
import by.com.lifetech.billingapi.models.requests.TVplusPurchaseRequest;
import by.com.lifetech.billingapi.models.responses.SaluteCheckActiveResponse;
import by.com.lifetech.billingapi.services.IPayService;
import by.com.lifetech.billingapi.services.MediatechService;
import by.com.lifetech.billingapi.services.SaluteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerControllerTest {
    @Mock
    private MediatechService mediatechService;
    @Mock
    private SaluteService saluteService;
    @Mock
    private IPayService iPayService;

    @InjectMocks
    private PartnerController partnerController;

    @Test
    @DisplayName("Get expected result for getTVplusSubscriber request")
    void getTVplusSubscriber_whenGetParameters_thenReturnValidAnswer() throws Exception {
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("serviceBalance", 0);
        responseMap.put("account_id", "188361");
        responseMap.put("mainBalance", "0,10");
        responseMap.put("activeProducts", "");
        responseMap.put("graceProducts", "");
        responseMap.put("error", "0");
        responseMap.put("msisdn", "375256257275");
        responseMap.put("turboAvaliable", false);
        responseMap.put("RatePlan", "INF");
        ServiceResponseDto<Map<String, Object>> getTVplusSubscriberResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        getTVplusSubscriberResponse.setResultMap(responseMap);
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(getTVplusSubscriberResponse);
        when(mediatechService.tvPlusGetInfo("375256257275")).thenReturn(getTVplusSubscriberResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.getTVplusSubscriber("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(mediatechService, times(1)).tvPlusGetInfo(anyString());
    }

    @Test
    @DisplayName("Get expected result for callTVplusOTP request")
    void callTVplusOTP_whenGetParameters_thenReturnValidAnswer() throws Exception {
        TVplusPurchaseRequest request = new TVplusPurchaseRequest("375256257275", "400900", BigDecimal.ZERO, "123456789455", "Фильм");
        ServiceResponseDto<Map<String, Object>> callTVplusOTPResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(callTVplusOTPResponse);
        when(mediatechService.tvPlusPurchase(request)).thenReturn(callTVplusOTPResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.callTVplusOTP(request);

        assertEquals(expectedResponse, actualResponse);
        verify(mediatechService, times(1)).tvPlusPurchase(any());
    }

    @Test
    @DisplayName("Get expected result for getSaluteSubscriptionActive request")
    void getSaluteSubscriptionActive_whenGetParameters_thenReturnValidAnswer() throws Exception {
        SaluteCheckActiveResponse saluteCheckActiveResponse = new SaluteCheckActiveResponse(1, false);
        ResponseEntity<SaluteCheckActiveResponse> expectedResponse = ResponseEntity.ok(saluteCheckActiveResponse);
        when(saluteService.isSubscriptionActive("375256257275")).thenReturn(saluteCheckActiveResponse);

        ResponseEntity<SaluteCheckActiveResponse> actualResponse = partnerController.getSaluteSubscriptionActive("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(saluteService, times(1)).isSubscriptionActive(anyString());
    }

    @Test
    @DisplayName("Get expected result for operateSaluteService request")
    void operateSaluteService_whenGetParameters_thenReturnValidAnswer() throws Exception {
        SaluteServiceRequest saluteServiceRequest = new SaluteServiceRequest("375256257275", "0", SaluteServiceOperation.ACT);
        ServiceResponseDto<Map<String, Object>> operateSaluteServiceResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(operateSaluteServiceResponse);
        when(saluteService.operateSaluteService(saluteServiceRequest)).thenReturn(operateSaluteServiceResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.operateSaluteService(saluteServiceRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(saluteService, times(1)).operateSaluteService(any());
    }

    @Test
    @DisplayName("Get expected result for getIPayServiceInfo request")
    void getIPayServiceInfo_whenGetParameters_thenReturnValidAnswer() throws Exception {
        ServiceResponseDto<Map<String, Object>> getIPayServiceInfoResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("contractDate", "2024-08-20T09:00:00.000+00:00");
        getIPayServiceInfoResponse.setResultMap(resultMap);
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(getIPayServiceInfoResponse);
        when(iPayService.getServiceInfo("375256257275", BigDecimal.ZERO)).thenReturn(getIPayServiceInfoResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.getIPayServiceInfo("375256257275", BigDecimal.ZERO);

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).getServiceInfo(anyString(), any());
    }

    @Test
    @DisplayName("Get expected result for getIPaySubscriberFIO request")
    void getIPaySubscriberFIO_whenGetParameters_thenReturnValidAnswer() throws Exception {
        ServiceResponseDto<SubscriberFIODto> getIPaySubscriberFIOResponse = new ServiceResponseDto<SubscriberFIODto>().setDefaultSuccessResponse();
        SubscriberFIODto subscriberFIODto = new SubscriberFIODto("Ivan", "Ivanovich", "Ivanov");
        getIPaySubscriberFIOResponse.setResultMap(subscriberFIODto);
        ResponseEntity<ServiceResponseDto<SubscriberFIODto>> expectedResponse = ResponseEntity.ok(getIPaySubscriberFIOResponse);
        when(iPayService.getSubscriberFIO("375256257275")).thenReturn(getIPaySubscriberFIOResponse);

        ResponseEntity<ServiceResponseDto<SubscriberFIODto>> actualResponse = partnerController.getIPaySubscriberFIO("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).getSubscriberFIO(anyString());
    }

    @Test
    @DisplayName("Get expected result for checkSubscriberBalance request")
    void checkSubscriberBalance_whenGetParameters_thenReturnValidAnswer() throws Exception {
        ServiceResponseDto<Map<String, Object>> checkSubscriberBalanceResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("MainBalance", 0.1);
        resultMap.put("ExtendedBalance", 0);
        checkSubscriberBalanceResponse.setResultMap(resultMap);
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(checkSubscriberBalanceResponse);
        when(iPayService.checkSubscriberBalance("375256257275")).thenReturn(checkSubscriberBalanceResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.checkSubscriberBalance("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).checkSubscriberBalance(anyString());
    }

    @Test
    @DisplayName("Get expected result for checkRequestStatus request")
    void checkRequestStatus_whenGetParameters_thenReturnValidAnswer() throws Exception {
        ServiceResponseDto<Map<String, Object>> checkRequestStatusResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("depositStatus", "0");
        checkRequestStatusResponse.setResultMap(resultMap);
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(checkRequestStatusResponse);
        when(iPayService.checkRequestStatus("27535")).thenReturn(checkRequestStatusResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.checkRequestStatus("27535");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).checkRequestStatus(anyString());
    }

    @Test
    @DisplayName("Get expected result for iPayMoneyBack request")
    void iPayMoneyBack_whenGetParameters_thenReturnValidAnswer() throws Exception {
        ServiceResponseDto<Map<String, Object>> iPayMoneyBackResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(iPayMoneyBackResponse);
        when(iPayService.iPayMoneyBack("27535")).thenReturn(iPayMoneyBackResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.iPayMoneyBack(new IPayMoneyBackRequest("27535"));

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).iPayMoneyBack(anyString());
    }

    @Test
    @DisplayName("Get expected result for iPayCharging request")
    void iPayCharging_whenGetParameters_thenReturnValidAnswer() throws Exception {
        IPayChargingRequest iPayChargingRequest = new IPayChargingRequest("27535", "375256257275", BigDecimal.valueOf(5));
        ServiceResponseDto<Map<String, Object>> iPayChargingResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("fhCode", "7238746238");
        iPayChargingResponse.setResultMap(resultMap);
        ResponseEntity<ServiceResponseDto<Map<String, Object>>> expectedResponse = ResponseEntity.ok(iPayChargingResponse);
        when(iPayService.iPayCharging(iPayChargingRequest)).thenReturn(iPayChargingResponse);

        ResponseEntity<ServiceResponseDto<Map<String, Object>>> actualResponse = partnerController.iPayCharging(iPayChargingRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(iPayService, times(1)).iPayCharging(any());
    }
}