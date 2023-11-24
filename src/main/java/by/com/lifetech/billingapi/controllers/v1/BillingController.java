package by.com.lifetech.billingapi.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
import org.springframework.web.bind.annotation.RestController;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.RegHistoryRequestDto;
import by.com.lifetech.billingapi.models.entity.RegistrationHistory;
import by.com.lifetech.billingapi.services.SubscriberInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1")
@EnableScheduling
@Tag(name="Billing API", description="Basic Billing API Methods")
public class BillingController {

	Logger logger = LoggerFactory.getLogger(BillingController.class);
	
	@Autowired
	SubscriberInfoService subscriberInfoService;

	@Hidden
	@GetMapping("/test")
	String getTest() {
		logger.info("Test OK");
		return "{\"test\" : \"success\"}";
	}
	
	@Operation(
			summary = "Find registration history",
			description = "Parameters that do not need to be filtered are passed as empty strings."
		)
	@PostMapping(value = "/findreghistory", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<RegistrationHistory>> getRegHistory(@RequestBody RegHistoryRequestDto req, @ParameterObject Pageable pageable) throws BusinessException {
		return ResponseEntity.ok(subscriberInfoService.getRegistrationHistory(req, pageable));
	}
	
	
	@Operation(
			summary = "Clearing the cache",
			description = "Allows you to clear the dictionaries cache. Runs according to schedule."
		)
	@CacheEvict(allEntries = true, cacheNames = { "localNames", "registrationTypes" })
	@Scheduled(fixedRateString = "${caching.spring.dictTTL}")
	@GetMapping("/clearcache")
	public String emptyCache() {
	    logger.info("emptying dict caches");
	    return "{\"result\" : \"success\"}";
	}

}
