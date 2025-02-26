package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.dto.UniversalDictionary;
import by.com.lifetech.billingapi.models.enums.DictName;
import by.com.lifetech.billingapi.models.enums.DictSearchType;
import by.com.lifetech.billingapi.models.enums.FullDictName;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.services.DictionaryService;
import by.com.lifetech.billingapi.utils.NotLogging;
import by.life.crmadvancedsearch.model.SearchCriterion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/dictionaries", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM Dictionary API", description = "For Life CRM")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Operation(summary = "Get dictionaries", description = "find values from the dictionary by name")
    @GetMapping("")
    @NotLogging
    ResponseEntity<ServiceResponseDto<List<UniversalDictionary>>> findDictionary(
            @RequestParam(value="value", required=false, defaultValue = "") String value,
            @RequestParam("dictName") DictName dictName,
            @RequestParam("lang") Lang lang) {
        return ResponseEntity.ok(dictionaryService.getDictBySearchType(dictName, DictSearchType.CONTAINS_BY_VALUE, value, lang));
    }

    @Operation(summary = "Get dictionaries", description = "find values from the dictionary by name")
    @GetMapping("/by-code")
    @NotLogging
    ResponseEntity<ServiceResponseDto<UniversalDictionary>> findDictionaryByCode(
            @RequestParam(value="code") @NotEmpty String value,
            @RequestParam("dictName") DictName dictName,
            @RequestParam("lang") Lang lang) {
        List<UniversalDictionary> resultList = dictionaryService.getDictBySearchType(dictName, DictSearchType.EQUALS_BY_CODE, value, lang).getResultMap();
        ServiceResponseDto<UniversalDictionary> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(resultList.stream().findFirst().orElse(null));
        return ResponseEntity.ok(serviceResponse);
    }

    @Operation(summary = "Get full dictionaries")
    @PostMapping("/get-full")
    @NotLogging
    ResponseEntity<ServiceResponseDto<List<?>>> getFullDictionary(
            @RequestParam("dictName") FullDictName dictName,
            @RequestBody List<SearchCriterion> searchCriteria) {
        return ResponseEntity.ok(dictionaryService.getFullDictionary(dictName, searchCriteria));
    }
}
