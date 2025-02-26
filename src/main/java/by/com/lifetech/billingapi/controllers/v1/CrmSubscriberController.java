package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.*;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.MultiGroupData;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.models.responses.AvailableRetentionTariffs;
import by.com.lifetech.billingapi.models.responses.AvailableSpecificTariffs;
import by.com.lifetech.billingapi.models.responses.AvailableTariffsResponse;
import by.com.lifetech.billingapi.services.*;
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
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/subscriber", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscriber API", description = "for Life CRM")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class CrmSubscriberController {

    private final SubscriberInfoService subscriberInfoService;
    private final SubscriberOperationsService subscriberOperationsService;
    private final SubscriberGroupsService groupsService;
    private final HssService hssService;
    private final PcrfService pcrfService;

    @Operation(summary = "Get subscriber", description = "Search by MSISDN, ICCID, IMSI or contract number")
    @GetMapping(value = "/{valueSearch}/short", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<List<SubscriberInfDto>>> getSubscriber(
            @PathVariable("valueSearch") @NotNull String valueSearch, @RequestParam("lang") Lang lang)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(subscriberInfoService.getSubscriberInf(valueSearch, lang));
    }

    @Operation(summary = "Get HSS data", description = "based on CRM directories")
    @GetMapping("/{msisdn}/hss")
    ResponseEntity<ServiceResponseDto<List<HssGroupDto>>> getHssData(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(hssService.getHssData(msisdn, lang));
    }

    @Operation(summary = "Get PCRF data", description = "Only active pcrf services")
    @GetMapping("/{msisdn}/pcrf")
    ResponseEntity<ServiceResponseDto<PcrfInfoDto>> getPcrfData(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(pcrfService.getPcrfData(msisdn, lang));
    }

    @Operation(summary = "Get available tariffs for change", description = "Based on current subscriber's tariff")
    @GetMapping("/{msisdn}/available-tariffs")
    ResponseEntity<ServiceResponseDto<AvailableTariffsResponse>> getAvailableTariffs(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang)
            throws InternalException {
        return ResponseEntity.ok(subscriberInfoService.getAvailableTariffs(msisdn, lang));
    }

    @Operation(summary = "Get available retention offers")
    @GetMapping("/{msisdn}/available-tariffs/retention")
    ResponseEntity<ServiceResponseDto<AvailableRetentionTariffs>> getAvailableRetentionOffers(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang)
            throws InternalException {
        return ResponseEntity.ok(subscriberInfoService.getAvailableRetentionOffers(msisdn, lang));
    }

    @Operation(summary = "Get available specific offers")
    @GetMapping("/{msisdn}/available-tariffs/specific")
    ResponseEntity<ServiceResponseDto<AvailableSpecificTariffs>> getAvailableSpecificOffers(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang)
            throws InternalException {
        return ResponseEntity.ok(subscriberInfoService.getAvailableSpecificOffers(msisdn, lang));
    }

    @GetMapping("/{msisdn}/services")
    @Operation(summary = "Available and active services")
    ResponseEntity<ServiceResponseDto<List<GroupServicesDto>>> getServices(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberInfoService.getServices(msisdn, lang));
    }

    @GetMapping("/{msisdn}/active-services")
    @Operation(summary = "Active services for msisdn")
    ResponseEntity<ServiceResponseDto<List<ActiveServiceDto>>> getActiveServices(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang,
            @RequestParam("channel") @NotEmpty String channel) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberInfoService.getActiveServices(msisdn, channel, lang));
    }

    @GetMapping("/{msisdn}/tariff-info")
    @Operation(summary = "Tariff advanced information")
    ResponseEntity<ServiceResponseDto<TariffInfoDto>> getTariffInfo(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberInfoService.getTariffInfo(msisdn, lang));
    }

    @PostMapping("/{msisdn}/change-tariff")
    @Operation(summary = "Change tariff new logic", description = "parameter MID is optional (only for special change)")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> changeTariffBest(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestBody ChangeTariffRequest req)
            throws BusinessException, InternalException {
        req.setMsisdn(msisdn);
        ServiceResponseDto<Map<String, Object>> response = subscriberOperationsService.changeTariffLogic(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{msisdn}/balances")
    @Operation(summary = "Get balances", description = "Non-zero balances")
    ResponseEntity<ServiceResponseDto<Set<BalanceGroupDto>>> getBalances(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang) throws InternalException, BusinessException {
        return ResponseEntity.ok(subscriberInfoService.getBalances(msisdn, lang));
    }

    @PutMapping("/{msisdn}/balances")
    @Operation(summary = "Update balance", description = "Milliseconds in pocket not allowed")
    ResponseEntity<ServiceResponseDto<Set<BalanceGroupDto>>> updateBalance(
            @RequestBody @Validated UpdateBalanceRequest req,
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang) throws BusinessException, InternalException {
        subscriberOperationsService.updateBalance(req, msisdn);
        return ResponseEntity.ok(subscriberInfoService.getBalances(msisdn, lang));
    }

    @GetMapping("/{msisdn}/full-info")
    @Operation(summary = "Get subscriber full information")
    ResponseEntity<ServiceResponseDto<SubscriberFullInfo>> getSubscriberFull(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("lang") Lang lang) throws BusinessException, InternalException {
        return ResponseEntity.ok(subscriberInfoService.getSubscriberFull(msisdn, lang));
    }

    @PostMapping("/{msisdn}/deactivation")
    @Operation(summary = "Deactivation subscriber", description = "use DICT_SUBS_TERM_TYPE for terminationType")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> subscriberDeactivation(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn, @RequestBody SubscriberTerminationRequest req)
            throws BusinessException, InternalException {
        req.setMsisdn(msisdn);
        return ResponseEntity.ok(subscriberOperationsService.subscriberTermination(req));
    }

    @GetMapping("/{msisdn}/groups/multi-group")
    @Operation(summary = "Get Multi-group data")
    ResponseEntity<ServiceResponseDto<List<MultiGroupData>>> getMultiGroupsData(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn) {
        return ResponseEntity.ok(groupsService.getMultiGroupData(msisdn));
    }

    @GetMapping("/{msisdn}/groups/own-group")
    @Operation(summary = "Get 'For your own group' info")
    ResponseEntity<ServiceResponseDto<OwnGroupInfoDto>> getOwnGroupsData(
            @PathVariable("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(groupsService.getOwnGroupData(msisdn));
    }

    @GetMapping(value = "/{msisdn}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation(summary = "Get subscriber photo from db")
    ResponseEntity<?> getSubscriberPhoto(@PathVariable("msisdn") @MsisdnDefaultCheck String msisdn,
            @RequestParam("contractId") @NotEmpty String contractId) {
        return ResponseEntity.ok(subscriberInfoService.getPhoto(msisdn,contractId));
    }
}
