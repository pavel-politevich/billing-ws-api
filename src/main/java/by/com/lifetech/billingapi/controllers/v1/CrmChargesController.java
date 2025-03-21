package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.models.dto.ChargingEventGroupDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.ChargeDetails;
import by.com.lifetech.billingapi.models.entity.ChargeSummary;
import by.com.lifetech.billingapi.models.entity.Charges;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.ChargeDetailsRequest;
import by.com.lifetech.billingapi.models.requests.ChargesRequest;
import by.com.lifetech.billingapi.services.SubscriberChargesService;
import by.com.lifetech.billingapi.configurations.annotations.NotLogging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/get-charges", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM Charges API", description = "for Life CRM")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class CrmChargesController {

    private final SubscriberChargesService chargesService;

    @Operation(summary = "Get charges")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Page<Charges>>> getSubscriberCharges(
            @RequestBody @Validated ChargesRequest req, @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(chargesService.getCharges(req, pageable));
    }

    @Operation(summary = "Get charge summary")
    @PostMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<List<ChargeSummary>>> getSubscriberChargesSummary(
            @RequestBody @Validated ChargesRequest req) {
        return ResponseEntity.ok(chargesService.getChargeSummary(req));
    }

    @Operation(summary = "Get charge details")
    @PostMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<List<ChargeDetails>>> getSubscriberChargeDetails(
            @RequestBody @Validated ChargeDetailsRequest req) {
        return ResponseEntity.ok(chargesService.getChargeDetails(req));
    }

    @Operation(summary = "Get DICT_CHARGING_EVENT_GROUP")
    @GetMapping("/get-dict-groups")
    @NotLogging
    ResponseEntity<List<ChargingEventGroupDto>> findDictionaryByCode(
            @RequestParam("lang") Lang lang) {
        return ResponseEntity.ok(chargesService.getDictEventGroup(lang));
    }
}
