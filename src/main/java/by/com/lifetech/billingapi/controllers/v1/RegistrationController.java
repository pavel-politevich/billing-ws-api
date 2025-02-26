package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.*;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.AssetType;
import by.com.lifetech.billingapi.models.entity.RegistrationServicesByTp;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.models.responses.ActivationCheckResultResponse;
import by.com.lifetech.billingapi.services.ActivationChecksService;
import by.com.lifetech.billingapi.services.DeviceService;
import by.com.lifetech.billingapi.services.ProductOfferingService;
import by.com.lifetech.billingapi.services.SubscriberActivationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/registration", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM Online Registration API")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class RegistrationController {
    private final ActivationChecksService activationChecksService;
    private final SubscriberActivationService subscriberActivationService;
    private final ProductOfferingService productOfferingService;
    private final DeviceService deviceService;

    @Operation(summary = "Check Blacklist")
    @PostMapping("/check/blacklist")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkBlacklist(
            @RequestBody @Validated CheckByPassportRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkBlacklist(req));
    }

    @Operation(summary = "Check Fraud")
    @PostMapping("/check/fraud")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkFraud(
            @RequestBody @Validated CheckByPassportRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkFraud(req));
    }

    @Operation(summary = "Check MIA")
    @PostMapping("/check/mia")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkMIA(
            @RequestBody @Validated CheckMvdNtecRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkMIA(req));
    }

    @Operation(summary = "Check NTEC")
    @PostMapping("/check/ntec")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checksNTEC(
            @RequestBody @Validated CheckMvdNtecRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkNTEC(req));
    }

    @Operation(summary = "Check Limit SIM-card")
    @PostMapping("/check/sim-limit")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkSimCardLimitation(
            @RequestBody @Validated CheckByPassportRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkLimitationSimCard(req));
    }

    @Operation(summary = "Check debt by passport")
    @PostMapping("/check/debt")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkDebtByPassport(
            @RequestBody @Validated CheckByPassportRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkDebt(req));
    }

    @Operation(summary = "Checks category (mid) groups")
    @PostMapping("/check/offer-group")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checksByCategory(
            @RequestBody @Validated CheckMidRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkByCategory(req));
    }

    @Operation(summary = "Check Terminal Group max limit")
    @PostMapping("/check/terminal-group")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checksByTerminalGroup(
            @RequestBody @Validated CheckTerminalRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkTerminalGroup(req));
    }

    @Operation(summary = "Check credit services by msisdn")
    @PostMapping("/check/credit")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkCreditServicesByMsisdn(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkCreditsByMsisdn(msisdn));
    }

    @Operation(summary = "Check obligations by msisdn")
    @PostMapping("/check/obligations")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkObligationsByMsisdn(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkObligationByMsisdn(msisdn));
    }

    @Operation(summary = "Check terrorist list for individual")
    @PostMapping("/check/terr-ind")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkTerrListIndividual(
            @RequestBody @Validated CheckByFioRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkTerrListInd(req));
    }

    @Operation(summary = "Calling group of checks by passport")
    @PostMapping("/check/by-passport")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkByPassport(
            @RequestBody @Validated CheckByPassportRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkByPassport(req));
    }

    @Operation(summary = "Calling group of checks by full passport data")
    @PostMapping("/check/by-passport-full")
    ResponseEntity<ServiceResponseDto<ActivationCheckResultResponse>> checkByPassportFull(
            @RequestBody @Validated CheckMvdNtecRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.checkByPassportAndFio(req));
    }

    @Operation(summary = "Get available credit services")
    @GetMapping("/check/available-credits")
    ResponseEntity<ServiceResponseDto<CreditServicesAvailabilityDto>> getAvailableCreditServices(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(activationChecksService.callCreditChecks(msisdn));
    }

    @Operation(summary = "Subscriber online registration for lifeID")
    @PostMapping("/activation/lifeid")
    ResponseEntity<ServiceResponseDto<OnlineRegistrationDto>> onlineRegistrationLifeId(@RequestBody @Validated RegistrationSimRequest req) throws InternalException {
        return ResponseEntity.ok(subscriberActivationService.onlineRegistrationLifeId(req));
    }

    @Operation(summary = "Subscriber online checks for lifeID")
    @PostMapping("/check/lifeid")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> onlineChecksLifeId(@RequestBody @Validated RegistrationSimRequest req) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberActivationService.onlineChecksLifeId(req));
    }

    @Operation(summary = "Subscriber new-sim registration for CRM")
    @PostMapping("/activation/crm")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> crmEmptySimRegistration(@RequestBody @Validated RegistrationSimRequest req) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberActivationService.crmEmptySimRegistration(req));
    }

    @Operation(summary = "Existing subscriber registration to the offer")
    @PostMapping("/existing")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> crmExistingRegistration(@RequestBody @Validated RegistrationExistingRequest req) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberActivationService.crmExistingRegistration(req));
    }

    @Operation(summary = "Offer without obligation")
    @PostMapping("/without-obligation")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> crmWithoutObligationRegistration(@RequestBody @Validated RegistrationWithoutOblRequest req) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberActivationService.crmWithoutObligationRegistration(req));
    }

    @Operation(summary = "Get tariffs for registration")
    @PostMapping("/get-offer/tariffs")
    ResponseEntity<ServiceResponseDto<List<TariffRegistrationDto>>> getTariffsForRegistration(
            @RequestBody @Validated StartPackRequest req, @RequestParam("lang") Lang lang
    ) {
        return ResponseEntity.ok(productOfferingService.getTariffsForRegistration(req, Lang.RU));
    }

    @Operation(summary = "Get start packs")
    @PostMapping("/get-offer/{tariff}")
    ResponseEntity<ServiceResponseDto<List<CategoryRegistrationDto>>> getStartPacks(
            @RequestBody @Validated StartPackRequest req,
            @PathVariable("tariff") @NotNull String tariff,
            @RequestParam("lang") Lang lang
    ) {
        return ResponseEntity.ok(productOfferingService.getOffersForRegistration(req, tariff, Lang.RU));
    }

    @Operation(summary = "Check secret tariff")
    @PostMapping("/check/secret-tariff")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> checkSecretTariff(@RequestBody @Validated CheckSecretTariffRequest req) throws InternalException, BusinessException {
        return ResponseEntity.ok(activationChecksService.checkSecretTariff(req));
    }

    @Operation(summary = "Get services for activation")
    @PostMapping("/services/get")
    ResponseEntity<ServiceResponseDto<List<RegistrationServicesByTp>>> getServicesForActivation(@RequestBody @Validated RegServicesRequest req) {
        return ResponseEntity.ok(subscriberActivationService.getServicesForActivation(req));
    }

    @Operation(summary = "Mass activation services")
    @PostMapping("/services/activate")
    ResponseEntity<ServiceResponseDto<Integer>> massActivationServices(@RequestBody @Validated RegServicesActivationRequest req) {
        return ResponseEntity.ok(productOfferingService.massActivationServices(req));
    }

    @Operation(summary = "Get device po-instance attribute")
    @GetMapping("/get-offer/po-instance")
    ResponseEntity<ServiceResponseDto<List<OmPoInstanceValuesInfo>>> getPoInstanceAttributes(
            @RequestParam("mid") @NotEmpty String productCode,
            @RequestParam("device") @NotEmpty String deviceCode,
            @RequestParam("tariff") @NotEmpty String tariff) {
        return ResponseEntity.ok(deviceService.getPoInstanceAttributes(tariff,productCode,deviceCode));
    }

    @Operation(summary = "Get OTP devices")
    @GetMapping("/get-offer/otp")
    ResponseEntity<ServiceResponseDto<List<AssetType>>> getOtpDevicesForRegistration() {
        return ResponseEntity.ok(deviceService.getOtpDevices());
    }
}
