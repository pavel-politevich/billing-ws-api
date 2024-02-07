package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.CallChainRequestDto;
import by.com.lifetech.billingapi.models.dto.RegHistoryRequestDto;
import by.com.lifetech.billingapi.models.dto.ServiceOperationsRequestDto;
import by.com.lifetech.billingapi.models.dto.SubscriberInfDto;
import by.com.lifetech.billingapi.models.dto.THistoryRequestDto;
import by.com.lifetech.billingapi.models.entity.RegistrationHistory;
import by.com.lifetech.billingapi.models.entity.TransactionHistory;
import by.com.lifetech.billingapi.services.ChainService;
import by.com.lifetech.billingapi.services.SubscriberInfoService;
import by.com.lifetech.billingapi.services.SubscriberOperationsService;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value="api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@EnableScheduling
@Tag(name = "Billing API", description = "Basic Billing API Methods")
public class BillingController {

	Logger logger = LoggerFactory.getLogger(BillingController.class);

	@Autowired
	SubscriberInfoService subscriberInfoService;

	@Autowired
	SubscriberOperationsService subscriberOperationsService;

	@Autowired
	ChainService chainService;
	
	@Autowired
	private CacheManager cacheManager;

	@Hidden
	@GetMapping("/test")
	ResponseEntity<String> getTest() {
		return ResponseEntity.ok("{\"test\" : \"success\"}");
	}

	@Operation(summary = "Find registration history", description = "Parameters that do not need to be filtered are passed as empty strings.")
	@PostMapping(value = "/findreghistory", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ServiceResponseDto<Page<RegistrationHistory>>> getRegHistory(@RequestBody RegHistoryRequestDto req,
			@ParameterObject Pageable pageable) throws BusinessException {
		return ResponseEntity.ok(subscriberInfoService.getRegistrationHistory(req, pageable));
	}

	@Operation(summary = "Find transaction history", description = "Parameters that do not need to be filtered are passed as empty strings.")
	@PostMapping(value = "/findthistory", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ServiceResponseDto<Page<TransactionHistory>>> getTHistory(@RequestBody THistoryRequestDto req,
			@ParameterObject Pageable pageable) throws BusinessException {
		return ResponseEntity.ok(subscriberInfoService.getTransactionHistory(req, pageable));
	}

	@Operation(summary = "Clearing the cache", description = "Allows you to clear the dictionaries cache. Runs according to schedule.")
	@Scheduled(fixedRateString = "${caching.spring.dictTTL}")
	@GetMapping("/clearcache")
	public ResponseEntity<String> evictAllCaches() {
		logger.info("emptying dict caches");
		cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
		return ResponseEntity.ok("{\"result\" : \"success\"}");
	}

	@Operation(summary = "Change tariff logic", description = "call change_tarif_best chain with break session logic")
	@PostMapping("/change_tp")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> runChangeTariff(@RequestParam("msisdn") String msisdn,
													   @RequestParam("tariff") String tariffName, @RequestParam("channel") String channel,
													   @RequestParam("agent") String agent) throws BusinessException {
		return ResponseEntity.ok(subscriberOperationsService.changeTariff(msisdn, tariffName, channel, agent));
	}

	@Operation(summary = "Change tariff for special mid's", description = "By special service OM-code")
	@PostMapping("/special_change_tp")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> runSpecialMidChange(@RequestParam("msisdn") String msisdn,
			@RequestParam("omcode") String productOfferingCode, @RequestParam("channel") String channel,
			@RequestParam("agent") String agent) throws BusinessException {
		return ResponseEntity
				.ok(subscriberOperationsService.specialMidChange(msisdn, productOfferingCode, channel, agent));
	}

	@Hidden
	@PostMapping(value = "/callchain", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ChainResult> callAnyChain(@RequestBody CallChainRequestDto req) throws BusinessException {
		return ResponseEntity.ok(chainService.executeChain(req.getChainType(), req.getChainName(), req.getMapParams()));
	}

	@Operation(summary = "Get subscriber", description = "Subscriber info from billing")
	@GetMapping(value = "/subscriber", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ServiceResponseDto<SubscriberInfDto>> getSubscriber(@RequestParam("msisdn") String msisdn,
			@RequestParam("lang") String lang) throws BusinessException {
		return ResponseEntity.ok(subscriberInfoService.getSubscriberInf(msisdn, lang));
	}
	
	@Operation(summary = "Operations with subscriber services", description = "Order, Reorder, Refuse")
	@PostMapping("/services")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> executeFulFill(@RequestBody ServiceOperationsRequestDto req) throws BusinessException {
		return ResponseEntity.ok(subscriberOperationsService.serviceOperation(req));
	}

}
