package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.AdvancedSearchDto;
import by.com.lifetech.billingapi.models.dto.AdvancedSearchShorInfo;
import by.com.lifetech.billingapi.models.dto.SearchSubscriberDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.AdvancedSearchFIO;
import by.com.lifetech.billingapi.models.requests.SearchSubscriberRequest;
import by.com.lifetech.billingapi.models.responses.SearchForTerminationResponse;
import by.com.lifetech.billingapi.services.SubscriberSearchService;
import by.life.crmadvancedsearch.model.SearchCriterion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/subscriber/search", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscriber Search API")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class SearchController {
    private final SubscriberSearchService subscriberSearchService;

    @PostMapping("/before-deactivate")
    @Operation(summary = "Search subscriber before deactivation", description = "Only msisdn required")
    ResponseEntity<ServiceResponseDto<SearchForTerminationResponse>> searchBeforeDeactivate(
            @RequestBody SearchSubscriberRequest req,
            @RequestParam("lang") Lang lang) throws BusinessException, InternalException {
        return ResponseEntity.ok(subscriberSearchService.searchForTermination(req, lang));
    }

    @PostMapping("/dealer")
    @Operation(summary = "Search subscriber for dealer", description = "By MSISDN only OR MSISDN + PASSPORT")
    ResponseEntity<ServiceResponseDto<SearchSubscriberDto>> searchMsisdnForDealer(
            @RequestBody SearchSubscriberRequest req,
            @RequestParam("lang") Lang lang) throws BusinessException, InternalException {
        return ResponseEntity.ok(subscriberSearchService.searchForDealer(req, lang));
    }

    @PostMapping("/advanced-search")
    @Operation(summary = "Advanced search subscriber")
    ResponseEntity<ServiceResponseDto<Page<AdvancedSearchDto>>> advancedSearchSubscriber(
            @RequestBody @NotEmpty List<SearchCriterion> searchCriteria, @RequestParam("lang") Lang lang,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(subscriberSearchService.advancedSearch(searchCriteria, pageable, lang));
    }

    @PostMapping("/advanced-search/e-archive")
    @Operation(summary = "Advanced search fo e-Archive")
    ResponseEntity<ServiceResponseDto<List<AdvancedSearchShorInfo>>> advancedSearchForEArchive(
            @RequestBody @NotEmpty List<SearchCriterion> searchCriteria) {
        return ResponseEntity.ok(subscriberSearchService.advancedSearch(searchCriteria));
    }

    @GetMapping("/fio")
    @Operation(summary = "Get FIO by account id")
    ResponseEntity<ServiceResponseDto<AdvancedSearchFIO>> getFioForEArchive(
            @RequestParam("accountId") @NotNull Long accountId) throws BusinessException {
        return ResponseEntity.ok(subscriberSearchService.getSubscriberName(accountId));
    }
}
