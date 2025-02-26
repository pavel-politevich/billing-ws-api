package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.BalanceGroupDto;
import by.com.lifetech.billingapi.models.dto.ContractDetails;
import by.com.lifetech.billingapi.models.dto.ContractLineShort;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.UpdateIndAddress;
import by.com.lifetech.billingapi.models.requests.UpdateIndContractRequest;
import by.com.lifetech.billingapi.services.ContractInfoService;
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
@RequestMapping(value = "api/v1/contract", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM contract API", description = "for Life CRM")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class CrmContractController {

    private final ContractInfoService contractInfoService;

    @Operation(summary = "Get contract information")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<ContractDetails>> getContractInfo(
            @RequestParam("contractId") @NotNull String contractId, @RequestParam("lang") Lang lang)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(contractInfoService.getContractInfo(contractId, lang));
    }

    @Operation(summary = "Get contract lines")
    @GetMapping(value = "/{contractId}/lines", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<List<ContractLineShort>>> getContractLines(
            @PathVariable("contractId") @NotEmpty String contractId, @RequestParam("lang") Lang lang)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(contractInfoService.getContractLines(contractId, lang));
    }

    @Operation(summary = "Update individual contract", description = "For all lines by passport")
    @PostMapping(value = "/update-ind", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> updateContractInfo(@RequestBody UpdateIndContractRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(contractInfoService.updateIndContract(req));
    }

    @GetMapping("/{contractId}/balances")
    @Operation(summary = "Get balances", description = "Non-zero balances")
    ResponseEntity<ServiceResponseDto<Set<BalanceGroupDto>>> getBalances(
            @PathVariable("contractId") @NotEmpty String contractId, @RequestParam("lang") Lang lang) throws InternalException, BusinessException {
        return ResponseEntity.ok(contractInfoService.getBalances(contractId, lang));
    }

    @Operation(summary = "Update individual address")
    @PostMapping(value = "/update-address", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> updateContractAddress(@RequestBody UpdateIndAddress req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(contractInfoService.updateIndAddress(req));
    }
}
