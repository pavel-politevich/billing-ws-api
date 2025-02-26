package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.dto.suspend.SuspendLineSnapshot;
import by.com.lifetech.billingapi.models.requests.CallChainRequest;
import by.com.lifetech.billingapi.models.requests.DefaultChainServiceRequest;
import by.com.lifetech.billingapi.models.requests.THistoryEventRequest;
import by.com.lifetech.billingapi.services.CacheService;
import by.com.lifetech.billingapi.services.ChainService;
import by.com.lifetech.billingapi.services.SubscriberOperationsService;
import by.com.lifetech.billingapi.services.SuspendService;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "API for utilities", description = "other")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class UtilityController {

    private final PasswordEncoder passwordEncoder;
    private final ChainService chainService;
    private final SubscriberOperationsService subscriberOperationsService;
    private final SuspendService suspendService;
    private final CacheService cacheService;

    @Hidden
    @GetMapping("/test")
    ResponseEntity<String> getTest() {
        return ResponseEntity.ok("{\"test\" : \"success\"}");
    }

    @Hidden
    @Operation(summary = "Suspend with lifecycle freezing", description = "Used to SUSPW/STD, SUSPF/STD, SUSPF/GAR, SUSPL, SUSPD, SUSPW/VAS")
    @PostMapping("/test/suspend")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> suspendWithFreezeLineLogic(
            @RequestBody @Validated DefaultChainServiceRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(suspendService.suspendWithFreezeLineLogic(req));
    }

    @Hidden
    @Operation(summary = "Deactivate suspension with lifecycle freezing", description = "Used to SUSPW/STD, SUSPF/STD, SUSPF/GAR, SUSPL, SUSPD, SUSPW/VAS")
    @PostMapping("/test/unsuspend")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> unsuspendWithFreezeLineLogic(
            @RequestBody @Validated DefaultChainServiceRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(suspendService.unsuspendWithFreezeLineLogic(req));
    }

    @Hidden
    @Operation(summary = "Transform old suspension with lifecycle freezing to new", description = "Used to SUSPW/STD, SUSPF/STD, SUSPF/GAR, SUSPL, SUSPD, SUSPW/VAS")
    @PostMapping("/test/suspend-oldtonew")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> suspendOldToNewLogic(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(suspendService.suspendOldToNewLogic(msisdn));
    }

    @Hidden
    @Operation(summary = "Update snapshot of suspended line", description = "Used to SUSPW/STD, SUSPF/STD, SUSPF/GAR, SUSPL, SUSPD, SUSPW/VAS")
    @PostMapping("/test/snapshot")
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> updateSuspendSnapshot(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn, @RequestBody @NotNull SuspendLineSnapshot snapshot) throws BusinessException, InternalException {
        return ResponseEntity.ok(suspendService.updateSuspendSnapshot(msisdn, snapshot));
    }

    @Hidden
    @Operation(summary = "Get snapshot of suspended line", description = "Used to SUSPW/STD, SUSPF/STD, SUSPF/GAR, SUSPL, SUSPD, SUSPW/VAS")
    @GetMapping("/test/snapshot")
    ResponseEntity<ServiceResponseDto<SuspendLineSnapshot>> getSuspendSnapshot(
            @RequestParam("msisdn") @MsisdnDefaultCheck String msisdn) throws BusinessException, InternalException {
        return ResponseEntity.ok(suspendService.getSuspendSnapshot(msisdn));
    }

    @Operation(summary = "Clearing the cache", description = "Allows you to clear the dictionaries cache.")
    @GetMapping(value = "/clearcache", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> evictAllCaches() {
        cacheService.clearCaches();
        return ResponseEntity.ok("{\"result\" : \"success\"}");
    }

    @Operation(summary = "Clearing special caches", description = "Allows you to clear the special caches.")
    @GetMapping(value = "/clearcache/special", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> evictSpecialCaches() {
        cacheService.clearSpecialCaches();
        return ResponseEntity.ok("{\"result\" : \"success\"}");
    }

    @Hidden
    @Operation(summary = "Get cache keys by template")
    @GetMapping(value = "/cache/keys")
    public ResponseEntity<Set<String>> getCacheKeys(@RequestParam("query") String query) {
        return ResponseEntity.ok(cacheService.getKeys(query));
    }

    @Hidden
    @Operation(summary = "Get cache value by key")
    @GetMapping(value = "/cache/get")
    public ResponseEntity<Object> getCacheValue(@RequestParam("key") String key) {
        return ResponseEntity.ok(cacheService.getValue(key));
    }

    @Operation(summary = "Get cache info memory")
    @GetMapping(value = "/cache/memory", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCacheMemory() {
        return ResponseEntity.ok(cacheService.getMemoryInfo());
    }

    @Hidden
    @GetMapping(value = "/gen_pass", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> generatePass(@RequestParam("pass") String pass) {
        return ResponseEntity.ok(passwordEncoder.encode(pass));
    }

    @Hidden
    @PostMapping("/callchain")
    ResponseEntity<ChainResult> callAnyChain(@RequestBody @Validated CallChainRequest req) throws BusinessException, InternalException {
        return ResponseEntity.ok(chainService.executeChain(req.getChainType(), req.getChainName(), req.getMapParams()));
    }

    @Operation(summary = "Insert Transaction History", description = "msisdn or contract code may be empty")
    @PostMapping("/write_th")
    ResponseEntity<ServiceResponseDto<Map<String, Long>>> writeTransactionHistory(@RequestBody @Validated THistoryEventRequest req) {
        return ResponseEntity.ok(subscriberOperationsService.writeTransactionHistory(req));
    }

}
