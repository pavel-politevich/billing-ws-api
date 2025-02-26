package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.requests.MsisdnChangeRequest;
import by.com.lifetech.billingapi.models.requests.SimChangeRequest;
import by.com.lifetech.billingapi.services.SimStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/sim-stock", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM Sim-stock API")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class SimCardController {
    private final SimStockService simCardService;

    @PostMapping("/sim-change")
    @Operation(summary = "Change SIM card", description = "use DICT_SIM_CHANGE for type")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> changeSimCard(
            @RequestBody SimChangeRequest req)  throws BusinessException, InternalException {
        return ResponseEntity.ok(simCardService.changeSim(req));
    }

    @PostMapping("/msisdn-change")
    @Operation(summary = "Change MSISDN", description = "use DICT_NUMBER_SELECTION for msisdnCategory")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> changeMsisdnMod(
            @RequestBody MsisdnChangeRequest req)  throws BusinessException, InternalException {
        return ResponseEntity.ok(simCardService.changeMsisdn(req));
    }
}
