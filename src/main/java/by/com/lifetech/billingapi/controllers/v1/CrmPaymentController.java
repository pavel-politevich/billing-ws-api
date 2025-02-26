package by.com.lifetech.billingapi.controllers.v1;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.FinancialHistory;
import by.com.lifetech.billingapi.models.entity.PaymentDetails;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.services.PaymentService;
import by.com.lifetech.billingapi.services.SubscriberHistoryService;
import by.life.crmadvancedsearch.model.SearchCriterion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "CRM Payment API", description = "for Life CRM")
@Validated
@PreAuthorize("@SecurityCheckService.isValidAuthorities()")
@RequiredArgsConstructor
public class CrmPaymentController {

    private final PaymentService paymentService;
    private final SubscriberHistoryService subscriberHistoryService;

    @Operation(summary = "Dealer payment")
    @PostMapping(value = "/dealer-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callDealerPayment(
            @RequestBody @Validated DealerPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.dealerPayment(req));
    }

    @Operation(summary = "Manual payment for IND or CORP",
            description = "Use DICT_MP_FIN_OPERATION for finOpCode")
    @PostMapping(value = "/manual-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callManualPayment(
            @RequestBody @Validated ManualPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.manualPayment(req));
    }

    @Operation(summary = "Manual Penalty payment")
    @PostMapping(value = "/penalty-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callManualPenaltyPayment(
            @RequestBody @Validated ManualPenaltyPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.manualPayment(req));
    }

    @Operation(summary = "Manual Correction for MSISDN", description = "Use DICT_MC_FIN_OP_IND for finOpCode")
    @PostMapping(value = "/correction-line", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callManualCorrectionLinePayment(
            @RequestBody @Validated ManualCorrectionLineRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.manualPayment(req));
    }

    @Operation(summary = "Manual Correction for CONTRACT", description = "Use DICT_MC_FIN_OP_CORP for finOpCode")
    @PostMapping(value = "/correction-contract", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callManualCorrectionContractPayment(
            @RequestBody @Validated ManualCorrectionContractRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.manualPayment(req));
    }

    @Operation(summary = "Get financial history", description = "with SearchCriterion")
    @PostMapping(value = "/get-finhistory", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Page<FinancialHistory>>> getFinHistoryWithCriteria(
            @ParameterObject Pageable pageable,
            @RequestParam Lang lang,
            @RequestBody @NotEmpty List<SearchCriterion> searchCriteria) {
        return ResponseEntity.ok(subscriberHistoryService.getFinancialHistory(searchCriteria, pageable, lang));
    }

    @Operation(summary = "Get financial history details")
    @PostMapping(value = "/get-finhistory/details", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Optional<PaymentDetails>>> getFinHistoryDetails(
            @RequestBody @Validated PaymentDetailsRequest req) {
        return ResponseEntity.ok(subscriberHistoryService.getFinancialHistoryDetails(req));
    }

    @Operation(summary = "Cancel payment by finHistory code")
    @PostMapping(value = "/cancel-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callCancelPayment(
            @RequestBody @Validated CancelPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.cancelPayment(req));
    }

    @Operation(summary = "Reverse payment to agent")
    @PostMapping(value = "/reverse-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callReversePayment(
            @RequestBody @Validated CancelPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.reversePayment(req));
    }

    @Operation(summary = "Transfer to unrecognized")
    @PostMapping(value = "/transfer-to-ur", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callTransferToUnrecognized(
            @RequestBody @Validated CancelPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.transferToUnrecognized(req));
    }

    @Operation(summary = "Transfer to another account", description = "msisdnB OR agreementNoB")
    @PostMapping(value = "/transfer-to-acc", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ServiceResponseDto<Map<String, Object>>> callTransferToAnother(
            @RequestBody @Validated TransferPaymentRequest req)
            throws BusinessException, InternalException {
        return ResponseEntity.ok(paymentService.transferToAccount(req));
    }
}
