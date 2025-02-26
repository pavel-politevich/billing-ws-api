package by.com.lifetech.billingapi.controllers.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("dev")
@EnabledIfSystemProperty(named = "test.integration", matches = "true")
@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTVplusSubscriber() {
    }

    @Test
    void callTVplusOTP() {
    }

    @Test
    @DisplayName("Get Salute subscription status for valid msisdn")
    void getSaluteSubscriptionActive_whenCorrectMSISDN_thenReturnSaluteState() throws Exception {
       var requestBuilder = MockMvcRequestBuilders.get("/api/v1/partner/salute/is-active/375256257276")
                .with(user("SALUTE").roles("SALUTE"));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.status").exists(),
                        jsonPath("$.active").exists()
                );
    }

    @Test
    @DisplayName("Get VALIDATION_ERROR for Salute subscription status when invalid msisdn")
    void getSaluteSubscriptionActive_whenIncorrectMSISDN_thenReturnValidationError() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/api/v1/partner/salute/is-active/3752562572")
                .with(user("SALUTE").roles("SALUTE"));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.resultCode").value("VALIDATION_ERROR")
                );
    }

    @Test
    @DisplayName("Get BUSINESS_ERROR for Salute subscription status when not existed msisdn")
    void getSaluteSubscriptionActive_whenNotExistedMSISDN_thenReturnBusinessError() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/api/v1/partner/salute/is-active/375256211111")
                .with(user("SALUTE").roles("SALUTE"));

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.resultCode").value("BUSINESS_ERROR"),
                        jsonPath("$.businessError.errorName").value("ERR40001")
                );
    }

    @Test
    void operateSaluteService() {
    }

    @Test
    void getIPayServiceInfo() {
    }

    @Test
    void getIPaySubscriberFIO() {
    }

    @Test
    void checkSubscriberBalance() {
    }

    @Test
    void checkRequestStatus() {
    }

    @Test
    void iPayMoneyBack() {
    }

    @Test
    void iPayCharging() {
    }
}