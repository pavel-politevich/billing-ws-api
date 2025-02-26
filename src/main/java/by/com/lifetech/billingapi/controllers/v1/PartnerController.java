package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.IPayException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.ipay.SubscriberFIODto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.requests.IPayChargingRequest;
import by.com.lifetech.billingapi.models.requests.IPayMoneyBackRequest;
import by.com.lifetech.billingapi.models.requests.SaluteServiceRequest;
import by.com.lifetech.billingapi.models.requests.TVplusPurchaseRequest;
import by.com.lifetech.billingapi.models.responses.SaluteCheckActiveResponse;
import by.com.lifetech.billingapi.services.IPayService;
import by.com.lifetech.billingapi.services.MediatechService;
import by.com.lifetech.billingapi.services.SaluteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/partner", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "API for partners", description = "for external partners")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class PartnerController {
    private final MediatechService mediatechService;
    private final SaluteService saluteService;
    private final IPayService iPayService;
    Logger logger = LoggerFactory.getLogger(PartnerController.class);

    @Operation(summary = "Info for subscriber", description = "call TVplus_getInfo")
    @GetMapping("/tvplus/getinfo")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> getTVplusSubscriber(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(mediatechService.tvPlusGetInfo(msisdn));
    }

    @Operation(summary = "TV+ One Time Purchase", description = "call TVplus_OTP")
    @PostMapping("/tvplus/purchase")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callTVplusOTP(
            @RequestBody @Validated TVplusPurchaseRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(mediatechService.tvPlusPurchase(req));
    }
    
    @Operation(summary = "Salute get active subscriptions")
    @GetMapping("/salute/is-active/{msisdn}")
    ResponseEntity<SaluteCheckActiveResponse> getSaluteSubscriptionActive(
    		@PathVariable("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(saluteService.isSubscriptionActive(msisdn));
    }

    @Operation(summary = "Operate Salute service")
    @PostMapping("/salute/service")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> operateSaluteService(@RequestBody @Validated SaluteServiceRequest saluteServiceRequest) throws BusinessException, InternalException {
        return ResponseEntity.ok(saluteService.operateSaluteService(saluteServiceRequest));
    }

    @Operation(summary = "Check subscriber by iPay", description = "call iPayGetServiceInfo")
    @GetMapping("/ipay/getinfo")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> getIPayServiceInfo(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam(value = "amount", required = false) BigDecimal amount) throws IPayException {
        return ResponseEntity.ok(iPayService.getServiceInfo(msisdn, amount));
    }

    @Operation(summary = "Get subscriber FIO for iPay")
    @GetMapping("/ipay/getfio")
    ResponseEntity<ServiceResponseDto<SubscriberFIODto>> getIPaySubscriberFIO(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws IPayException {
        return ResponseEntity.ok(iPayService.getSubscriberFIO(msisdn));
    }

    @Operation(summary = "Check subscriber balance by iPay", description = "call checkSubscriberBalance")
    @GetMapping("/ipay/checkbalance")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> checkSubscriberBalance(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws IPayException {
        return ResponseEntity.ok(iPayService.checkSubscriberBalance(msisdn));
    }

    @Operation(summary = "Check iPay transaction's deposit status")
    @GetMapping("/ipay/check-request-status")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> checkRequestStatus(
            @RequestParam("operId") @NotEmpty String operId) throws IPayException {
        return ResponseEntity.ok(iPayService.checkRequestStatus(operId));
    }

    @Operation(summary = "Money back for iPay")
    @PostMapping("/ipay/moneyback")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> iPayMoneyBack(
            @RequestBody @Validated IPayMoneyBackRequest iPayMoneyBackRequest) throws IPayException {
        return ResponseEntity.ok(iPayService.iPayMoneyBack(iPayMoneyBackRequest.getOperID()));
    }

    @Operation(summary = "Charging for iPay")
    @PostMapping("/ipay/charging")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> iPayCharging(
            @RequestBody @Validated IPayChargingRequest iPayChargingRequest) throws IPayException {
        return ResponseEntity.ok(iPayService.iPayCharging(iPayChargingRequest));
    }
}
