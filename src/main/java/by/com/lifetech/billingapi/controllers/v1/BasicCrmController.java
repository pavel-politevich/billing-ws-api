package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.RegistrationHistory;
import by.com.lifetech.billingapi.models.entity.SalesPortalMid;
import by.com.lifetech.billingapi.models.entity.TransactionHistory;
import by.com.lifetech.billingapi.models.enums.CommonCheckAnswer;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.models.requests.autopay.AutopayMainServiceRequest;
import by.com.lifetech.billingapi.models.requests.autopay.AutopayRecipientServiceRequest;
import by.com.lifetech.billingapi.models.requests.autopay.AutopayServiceRequest;
import by.com.lifetech.billingapi.services.*;
import by.life.crmadvancedsearch.model.SearchCriterion;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Basic CRM API", description = "Basic Billing API Methods")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class BasicCrmController {

    private final SubscriberHistoryService subscriberHistoryService;
	private final SubscriberOperationsService subscriberOperationsService;
	private final SubscriberInfoService subscriberInfoService;
    private final LifeChannelsService lifeChannelsService;
	private final ProductOfferingService productOfferingService;
	private final SmsService smsService;


	@Operation(summary = "Find registration history", description = "with SearchCriterion")
	@PostMapping(value = "/get-reghistory", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ServiceResponseDto<Page<RegistrationHistory>>> getRegHistory(
			@RequestBody @NotEmpty List<SearchCriterion> searchCriteria,
			@ParameterObject Pageable pageable, @RequestParam Lang lang) {
		return ResponseEntity.ok(subscriberHistoryService.getRegistrationHistory(searchCriteria, pageable, lang));
	}
	
	@Operation(summary = "Find transaction history", description = "with SearchCriterion")
	@PostMapping(value = "/get-thistory", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ServiceResponseDto<Page<TransactionHistory>>> getTHistoryWithCriteria(
			@ParameterObject Pageable pageable, @RequestParam(required = false) String msisdn,
			@RequestParam @NotNull String contractNumber, @RequestParam Lang lang,
			@RequestBody @NotEmpty List<SearchCriterion> searchCriteria) {
		return ResponseEntity.ok(subscriberHistoryService.searchTransactionHistory(searchCriteria, msisdn,
				contractNumber, pageable, lang));
	}

	@Hidden
	@Operation(summary = "Change tariff logic", description = "call change_tarif_best chain with break session logic")
	@PostMapping("/change_tp")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> runChangeTariff(
			@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn,
			@RequestParam("tariff") @NotNull String tariffName, @RequestParam("channel") @NotNull String channel,
			@RequestParam("agent") String agent) throws BusinessException, InternalException {
		return ResponseEntity.ok(subscriberOperationsService.changeTariff(msisdn, tariffName, channel, agent, null));
	}

	@Hidden
	@Operation(summary = "Change tariff for special mid's", description = "By special service OM-code")
	@PostMapping("/special_change_tp")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> runSpecialMidChange(
			@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn,
			@RequestParam("omcode") @NotNull String productOfferingCode,
			@RequestParam("channel") @NotNull String channel, @RequestParam("agent") String agent)
			throws BusinessException, InternalException {
		return ResponseEntity
				.ok(subscriberOperationsService.specialMidChange(msisdn, productOfferingCode, channel, agent));
	}

	@Operation(summary = "Operations with subscriber services", description = "Order, Reorder, Refuse")
	@PostMapping("/services")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> executeFulFill(
			@RequestBody @Validated ServiceOperationsRequest req) throws InternalException {
		return ResponseEntity.ok(subscriberOperationsService.serviceOperation(req));
	}

	@GetMapping("/check-common-owner")
	@Operation(summary = "Check common owner for two msisdn by identification code of document", description = "Life channels authority logic")
	ResponseEntity<ServiceResponseDto<CommonCheckAnswer>> checkCommonOwner(
			@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("checkMsisdn") @MsisdnDefaultCheck String checkMsisdn) {
		return ResponseEntity.ok(lifeChannelsService.checkCommonOwner(msisdn, checkMsisdn));
	}

	@GetMapping("/check-service-reconnect")
	@Operation(summary = "Check is service was activated earlier", description = "For LifeGo channel")
	ResponseEntity<ServiceResponseDto<CommonCheckAnswer>> checkIsServiceWasActEarlier(
			@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn, @RequestParam("omCode") @NotBlank String omCode) throws BusinessException, InternalException {
		return ResponseEntity.ok(lifeChannelsService.checkIsServiceWasActEarlier(msisdn, omCode));
	}


	@GetMapping("/sales-portal-mids")
	@Operation(summary = "Get mids for sales portal")
	ResponseEntity<ServiceResponseDto<List<SalesPortalMid>>> getSalesPortalMids() {
		return ResponseEntity.ok(productOfferingService.getSalesPortalMids());
	}

	@GetMapping("/compensation/limit")
	@Operation(summary = "Get limit of bonus compensation")
	ResponseEntity<ServiceResponseDto<Map<String, Double>>> getCompensationLimit(
			@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
		return ResponseEntity.ok(subscriberInfoService.getCompensationLimit(msisdn));
	}

	@Operation(summary = "Set compensation bonus")
	@PostMapping("/compensation/bonus")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> setCompensationBonus(
			@RequestBody @Validated SubscriberCompensationRequest req) throws InternalException, BusinessException {
		return ResponseEntity.ok(subscriberOperationsService.lossCompensation(req));
	}

	@PostMapping("/check-nonres-passport")
	@Operation(summary = "Check passport data for non-resident subscriber", description = "For ChatBot channel")
	ResponseEntity<ServiceResponseDto<Boolean>> checkNonResidentPassport(
			@RequestBody @Validated CheckNonResidentPassportRequest request) {
		return ResponseEntity.ok(lifeChannelsService.checkNonResidentPassport(request));
	}

	@GetMapping("/autopay/amount")
	@Operation(summary = "Get amount for write off from subscriber's card", description = "For Autopayment by necessity service")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> getAmountForAutoPayment(@RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws InternalException, BusinessException {
		return ResponseEntity.ok(lifeChannelsService.getAmountForAutoPayment(msisdn));
	}

	@PostMapping("/thistory")
	@Operation(summary = "Write transaction history", description = "For Life channels")
	ResponseEntity<ServiceResponseDto<Map<String, Long>>> insertTransactionHistory(@RequestBody @Validated BasicTransactionRequest request) {
		return ResponseEntity.ok(lifeChannelsService.insertTransactionHistory(request));
	}

	@PostMapping("/autopay/main")
	@Operation(summary = "Activate autopayment main service", description = "For Autopayment by necessity service")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> activateAutoPayMainService(
			@RequestBody @Validated AutopayMainServiceRequest req) throws InternalException, BusinessException {
		return ResponseEntity.ok(lifeChannelsService.activateAutoPayMainService(req));
	}

	@DeleteMapping("/autopay/main")
	@Operation(summary = "Deactivate autopayment main service", description = "For Autopayment by necessity service")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> deactivateAutoPayMainService(
			@RequestBody @Validated AutopayServiceRequest req) throws InternalException, BusinessException {
		return ResponseEntity.ok(lifeChannelsService.deactivateAutoPayMainService(req));
	}

	@PostMapping("/autopay/recipient")
	@Operation(summary = "Activate autopayment recipient service", description = "For Autopayment by necessity service")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> activateAutoPayRecipientService(
			@RequestBody @Validated AutopayRecipientServiceRequest req) throws InternalException, BusinessException {
		return ResponseEntity.ok(lifeChannelsService.activateAutoPayRecipientService(req));
	}

	@DeleteMapping("/autopay/recipient")
	@Operation(summary = "Deactivate autopayment recipient service", description = "For Autopayment by necessity service")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> deactivateAutoPayRecipientService(
			@RequestBody @Validated AutopayRecipientServiceRequest req) throws InternalException, BusinessException {
		return ResponseEntity.ok(lifeChannelsService.deactivateAutoPayRecipientService(req));
	}

	@PostMapping("/sms/send/basic")
	@Operation(summary = "Send sms with parameters")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> sendSms(
			@RequestBody @Validated SmsBasicRequest request) throws InternalException {
		return ResponseEntity.ok(smsService.sendSmsWithParameter(request));
	}
}

