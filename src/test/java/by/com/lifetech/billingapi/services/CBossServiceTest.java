package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationSubscriberDto;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CBossServiceTest {
    @Mock
    private RestTemplate restTemplateWithConnectTimeout;
    @Mock
    private ExceptionUtils exceptionUtils;

    @InjectMocks
    private CBossService cBossService;

    @Test
    @DisplayName("Return information dto when cboss found info")
    void getInformation_whenFoundInformation_thenReturnResponse() throws Exception {
        when(restTemplateWithConnectTimeout.exchange(anyString(), any(), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(getInformationXMLResponse(), HttpStatusCode.valueOf(200)));
        GetInformationDto expectedResponse = new GetInformationDto();
        expectedResponse.setSubscriber(getInformationSubscriberDto());

        GetInformationDto actualResponse = cBossService.getInformation("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplateWithConnectTimeout, times(1))
                .exchange(anyString(), any(), any(), eq(String.class));
    }

    @Test
    @DisplayName("Return information dto with null subscriber when cboss not found info")
    void getInformation_whenNotFoundInformationByMsisdn_thenReturnResponse() throws Exception {
        when(restTemplateWithConnectTimeout.exchange(anyString(), any(), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(getNotFoundInformationXMLResponse(), HttpStatusCode.valueOf(200)));
        GetInformationDto expectedResponse = new GetInformationDto();

        GetInformationDto actualResponse = cBossService.getInformation("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplateWithConnectTimeout, times(1))
                .exchange(anyString(), any(), any(), eq(String.class));
    }

    @Test
    @DisplayName("Throw exception when cboss call return error for get information")
    void getInformation_whenCbossCallThrowException_thenThrowException() {
        when(restTemplateWithConnectTimeout.exchange(anyString(), any(), any(), eq(String.class)))
                .thenThrow(new RestClientException(""));

        assertThrows(ExternalServiceException.class, () -> {
            cBossService.getInformation("375256257275");
        });
        verify(restTemplateWithConnectTimeout, times(1))
                .exchange(anyString(), any(), any(), eq(String.class));
    }

    private GetInformationSubscriberDto getInformationSubscriberDto() {
        GetInformationSubscriberDto informationSubscriberDto = new GetInformationSubscriberDto();
        informationSubscriberDto.setAccountId("188361");
        informationSubscriberDto.setLanguageId("ru");
        informationSubscriberDto.setTariffPlan("TP_INF");
        informationSubscriberDto.setState("ACT/STD");
        informationSubscriberDto.setLineLevel("PRE");
        informationSubscriberDto.setUseCommonMain("false");
        GetInformationBalanceDto informationBalanceDto = new GetInformationBalanceDto();
        informationBalanceDto.setReserved(0.0);
        informationBalanceDto.setBalanceName("Bundle_Flag_Infinite_TP_Speed");
        informationBalanceDto.setLabel("TL_INF");
        informationBalanceDto.setValue(0.256000);
        informationBalanceDto.setStart("20241108102501");
        informationBalanceDto.setEnd("20241208102501");
        informationSubscriberDto.setBalances(List.of(informationBalanceDto));
        return informationSubscriberDto;
    }

    private String getInformationXMLResponse() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<information xmlns:ns1=\"http://cboss.orga-systems.com/xml/subscriber-information\"><subscriber\n" +
                "account_id=\"188361\">\n" +
                "<language_id>ru</language_id><tariff_plan>TP_INF</tariff_plan><state>ACT/STD</state><line_lvl>PRE</line_lvl><use_common_main>false</use_common_main>\n" +
                "<balance><balance_name>Bundle_Flag_Infinite_TP_Speed</balance_name><label>TL_INF</label><value>0.256000</value><reserved>0.000000</reserved><start>20241108102501</start><end>20241208102501</end></balance>\n" +
                "</subscriber></information>";
    }

    private String getNotFoundInformationXMLResponse() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<information xmlns:ns1=\"http://cboss.orga-systems.com/xml/subscriber-information\">Fatal error</information>";
    }

    @Test
    void getInfoFull() {
    }

    @Test
    @DisplayName("Throw exception when cboss call return error for full information")
    void getInfoFull_whenCbossCallThrowException_thenThrowException() {
        when(restTemplateWithConnectTimeout.exchange(anyString(), any(), any(), eq(String.class)))
                .thenThrow(new RestClientException(""));

        assertThrows(ExternalServiceException.class, () -> {
            cBossService.getInfoFull("375256257275");
        });
        verify(restTemplateWithConnectTimeout, times(1))
                .exchange(anyString(), any(), any(), eq(String.class));
    }

    @Test
    void getInformationAccount() {
    }

    @Test
    void getBalances() {
    }

    @Test
    void getAllBalances() {
    }

    @Test
    void getBalancesByAccId() {
    }

    @Test
    void getAccountId() {
    }

    @Test
    void getMsisdn() {
    }

    @Test
    void getSubscriber() {
    }

    @Test
    void getSubscriberFull() {
    }

    @Test
    void getReservation() {
    }

    @Test
    void hasGprsSession() {
    }
}