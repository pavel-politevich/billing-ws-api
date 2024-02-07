package by.com.lifetech.billingapi.controllers.v1;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.TVplusPurchaseRequestDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.services.MediatechService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value="api/v1/partner/tvplus", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "API for partners", description = "for external partners")
public class MediatechController {
	
	@Autowired
	MediatechService mediatechService;
	
	Logger logger = LoggerFactory.getLogger(MediatechController.class);
	
	@Operation(summary = "Info for subscriber", description = "call TVplus_getInfo")
	@GetMapping("/getinfo")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> getTVplusSubscriber(@RequestParam("msisdn") String msisdn) throws BusinessException {
		return ResponseEntity.ok(mediatechService.TVPlusGetInfo(msisdn));
	}
	
	@Operation(summary = "TV+ One Time Purchase", description = "call TVplus_OTP")
	@PostMapping("/purchase")
	ResponseEntity<ServiceResponseDto<Map<String, Object>>> callTVplusOTP(@RequestBody TVplusPurchaseRequestDto req) throws BusinessException {
		return ResponseEntity.ok(mediatechService.TVPlusPurchase(req));
	}

}
