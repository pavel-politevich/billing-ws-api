package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.models.dto.TransactionTypeDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.*;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.*;
import by.com.lifetech.billingapi.models.repository.dictionary.*;
import by.com.lifetech.billingapi.models.requests.PaymentDetailsRequest;
import by.life.crmadvancedsearch.builder.SpecificationBuilder;
import by.life.crmadvancedsearch.model.SearchCriterion;
import by.life.crmadvancedsearch.model.SearchOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberHistoryService {

    private final RegistrationHistoryRepository registrationHistoryRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final RegistrationTypeRepository registrationTypeRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final DictionaryService dictionaryService;
    private final FinancialHistoryRepository financialHistoryRepository;
    private final DictBanksRepository dictBanksRepository;
    private final DictFinancialTypeRepository dictFinancialTypeRepository;
    private final DictFtReasonRepository dictFtReasonRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final LinkedPaymentRepository linkedPaymentRepository;

    public ServiceResponseDto<Page<RegistrationHistory>> getRegistrationHistory(List<SearchCriterion> searchCriteria, Pageable page, Lang lang) {

        Specification<RegistrationHistory> spec = new SpecificationBuilder<RegistrationHistory>().build(searchCriteria);
        Page<RegistrationHistory> regHistories = registrationHistoryRepository.findAll(spec, page);

        for (RegistrationHistory rh : regHistories) {
            rh.setTariffName(dictionaryService.getChannelLocalName(rh.getTariffCode(), lang));
            rh.setCategoryName(dictionaryService.getChannelLocalName(rh.getCategoryCode(), lang));
            registrationTypeRepository.findByRegistrationTypeCode(rh.getRegistrationTypeCode())
                    .ifPresent(name -> rh.setRegistrationTypeName(name.getNameByLang(lang)));
        }

        ServiceResponseDto<Page<RegistrationHistory>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(regHistories);

        return serviceResponse;
    }

    public ServiceResponseDto<Page<TransactionHistory>> searchTransactionHistory(List<SearchCriterion> searchCriteria
            ,String msisdn, String contractNumber, Pageable page, Lang lang) {
        SearchCriterion nullMsisdnCriteria = new SearchCriterion("aMobileNo", SearchOperation.IS_NULL, Collections.emptyList());
        SearchCriterion contractCriteria = new SearchCriterion("aContractCode", SearchOperation.EQUALS, List.of(contractNumber));
        SearchCriterion msisdnCriteria = null;
        if (msisdn != null) {
            msisdnCriteria = new SearchCriterion("aMobileNo", SearchOperation.EQUALS, List.of(msisdn));
        }

        List<SearchCriterion> contractPartCriteria = new ArrayList<>();
        contractPartCriteria.add(nullMsisdnCriteria);
        contractPartCriteria.add(contractCriteria);

        List<SearchCriterion> msisdnPartCriteria = new ArrayList<>();
        if (msisdnCriteria!= null) {
            msisdnPartCriteria.add(msisdnCriteria);
        }

        Specification<TransactionHistory> contractPartSpecification = new SpecificationBuilder<TransactionHistory>()
        		.and(contractPartCriteria)
                .build();

        Specification<TransactionHistory> msisdnPartSpecification = new SpecificationBuilder<TransactionHistory>()
                .and(msisdnPartCriteria)
                .build();

        Specification<TransactionHistory> resultSpecification = msisdnPartSpecification.or(contractPartSpecification);

        Specification<TransactionHistory> specification = new SpecificationBuilder<TransactionHistory>()
                .and(searchCriteria)
                .withCustomSpecification(resultSpecification)
                .build();
        
        Page<TransactionHistory> thList = transactionHistoryRepository.findAll(specification, page);

        for (TransactionHistory th : thList) {
            TransactionTypeDto thDto = new TransactionTypeDto(th.getTransactionTypeCode(), "");
            transactionTypeRepository.findByCode(th.getTransactionTypeCode()).ifPresent(name -> {
                thDto.setName(name.getNameByLang(lang));
                th.setTransactionTypeName(name.getNameByLang(lang));
            });
        }

        ServiceResponseDto<Page<TransactionHistory>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(thList);

        return serviceResponse;
    }

    public ServiceResponseDto<Page<FinancialHistory>> getFinancialHistory(List<SearchCriterion> searchCriteria
            , Pageable page, Lang lang) {
        Specification<FinancialHistory> spec = new SpecificationBuilder<FinancialHistory>().build(searchCriteria);
        Page<FinancialHistory> financialHistories = financialHistoryRepository.findAll(spec, page);

        for (FinancialHistory fh : financialHistories) {
            mapPaymentAttribute(fh, lang);
        }

        ServiceResponseDto<Page<FinancialHistory>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(financialHistories);
        return serviceResponse;
    }

    private void mapPaymentAttribute(FinHistAttrs fh, Lang lang) {
        dictFinancialTypeRepository.findByCode(fh.getTypeCode()).ifPresent(name -> fh.setTypeName(name.getNameByLang(lang)));
        dictFtReasonRepository.findByCode(fh.getReasonCode()).ifPresent(name -> fh.setReasonName(name.getNameByLang(lang)));
        dictBanksRepository.findByCode(fh.getBankCode()).ifPresent(name -> fh.setBankName(name.getNameByLang(lang)));
        dictBanksRepository.findByCode(fh.getBankBranchCode()).ifPresent(name -> fh.setBankBranchName(name.getNameByLang(lang)));
    }

    public ServiceResponseDto<Optional<PaymentDetails>> getFinancialHistoryDetails(PaymentDetailsRequest req) {
        Optional<PaymentDetails> details = paymentDetailsRepository.getPaymentDetails(req.getEntryDate(), req.getFinCode());
        List<LinkedPayment> linkedPayments = linkedPaymentRepository.getLinkedPayments(req.getFinCode());
        for (LinkedPayment fh : linkedPayments) {
            mapPaymentAttribute(fh, req.getLang());
        }
        details.ifPresent(d -> d.setLinkedPayments(linkedPayments));

        ServiceResponseDto<Optional<PaymentDetails>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(details);
        return serviceResponse;
    }
}
