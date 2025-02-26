package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.AdvancedSearchDto;
import by.com.lifetech.billingapi.models.dto.AdvancedSearchShorInfo;
import by.com.lifetech.billingapi.models.dto.BalanceShortDto;
import by.com.lifetech.billingapi.models.dto.SearchSubscriberDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.AdvancedSearch;
import by.com.lifetech.billingapi.models.dto.UniversalDictionary;
import by.com.lifetech.billingapi.models.entity.SimCard;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.*;
import by.com.lifetech.billingapi.models.repository.dictionary.ContractTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.DictCrmBalanceRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriberStateRepository;
import by.com.lifetech.billingapi.models.requests.SearchSubscriberRequest;
import by.com.lifetech.billingapi.models.responses.SearchForTerminationResponse;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.life.crmadvancedsearch.builder.SpecificationBuilder;
import by.life.crmadvancedsearch.model.SearchCriterion;
import by.life.crmadvancedsearch.model.SearchOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberSearchService {
    public static final String CONTRACT_TERM_SERVICE = "S_COTRACT_TERMINATION_FEE_OTF";
    private final ChainService chainService;
    private final CBossService cBossService;
    private final ProductOfferingsRepository productOfferingsRepository;
    private final DictCrmBalanceRepository dictCrmBalanceRepository;
    private final SubscriberStateRepository subscriberStateRepository;
    private final AdvancedSearchRepository searchRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final DictionaryService dictionaryService;
    private final SimCardRepository simCardRepository;
    Logger logger = LoggerFactory.getLogger(SubscriberSearchService.class);
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    @Value("${termination.fee.days}")
    private int daysForTermFee;

    public ServiceResponseDto<SearchForTerminationResponse> searchForTermination(SearchSubscriberRequest req, Lang lang) throws BusinessException, InternalException {

        SearchForTerminationResponse subscriber = new SearchForTerminationResponse(getSearchMsisdn(req, lang));
        List<String> balListFiler = List.of("Line_Main", "Line_Bonus", "Line_Debt");
        List<GetInformationBalanceDto> balList = List.of();
        try {
            balList = cBossService.getBalances(req.getMsisdn());
        } catch (BusinessException | InternalException e) {
            logger.error("Can not get balances from c_boss for {}",req.getMsisdn());
        }

        if (subscriber.getActivationDate().isAfter(LocalDateTime.now().minusDays(daysForTermFee)) && subscriber.getContractType().equals("IND")) {
            subscriber.setTerminationFee(Double
                    .parseDouble(productOfferingsRepository.getProductAttribute(CONTRACT_TERM_SERVICE, "COST").replace(",", ".")));
        }

        balList.stream().filter(b -> balListFiler.contains(b.getBalanceName())).forEach(b ->
                dictCrmBalanceRepository.findByCode(b.getBalanceName()).ifPresent(dict -> {
                    BalanceShortDto balanceShortDto = new BalanceShortDto();
                    balanceShortDto.setCode(dict.getCode());
                    balanceShortDto.setName(dict.getNameByLang(lang));
                    balanceShortDto.setAmount(b.getValue());
                    balanceShortDto.setReserved(b.getReserved());
                    subscriber.getMainBalances().add(balanceShortDto);
                })
        );

        ServiceResponseDto<SearchForTerminationResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(subscriber);
        return serviceResponse;
    }

    public ServiceResponseDto<SearchSubscriberDto> searchForDealer(SearchSubscriberRequest req, Lang lang) throws BusinessException, InternalException {
        SearchSubscriberDto subscriber = getSearchMsisdn(req, lang);
        ServiceResponseDto<SearchSubscriberDto> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(subscriber);
        return serviceResponse;
    }

    private SearchSubscriberDto getSearchMsisdn(SearchSubscriberRequest req, Lang lang) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.CIM, "searchMsisdnForDealer", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }

        SearchSubscriberDto subscriber;
        try {
            subscriber = objectMapper
                    .readValue(chainResult.getResultList().stream().filter(el -> el.getName().equals("RESULT_ACCOUNT"))
                            .findFirst().orElseThrow().getValue().toString(), new TypeReference<>() {}
                    );
        } catch (JsonProcessingException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }

        String state = subscriber.getState().getCode();
        String stateCode = state.substring(0, state.indexOf("/", 5));
        String reasonCode = state.substring(state.indexOf('/', 5) + 1);
        subscriberStateRepository.findByCodeAndReason(stateCode, reasonCode)
                .ifPresent(name -> subscriber.getState().setName(name.getNameByLang(lang)));
        return subscriber;
    }

    public ServiceResponseDto<Page<AdvancedSearchDto>> advancedSearch(List<SearchCriterion> searchCriteria
            , Pageable pageRequest, Lang lang) {
        String iccid;
        String imsi;
        Optional<SimCard> iccidOpt = Optional.empty();

        for (SearchCriterion s : searchCriteria) {
            if (s.getKey().equals("imsi") && !s.getValues().get(0).isEmpty()) {
                imsi = s.getValues().get(0);
                iccidOpt = simCardRepository.findByImsi(imsi);
            }
        }
        List<SearchCriterion> iccidCardCriteria = new ArrayList<>();
        if (iccidOpt.isPresent()) {
            iccid = iccidOpt.get().getIccid();
            iccidCardCriteria.add(new SearchCriterion("iccid", SearchOperation.EQUALS, List.of(iccid)));
        }
        Specification<AdvancedSearch> iccidSpec = new SpecificationBuilder<AdvancedSearch>()
                .and(iccidCardCriteria)
                .build();
        Specification<AdvancedSearch> spec = new SpecificationBuilder<AdvancedSearch>()
                .and(searchCriteria)
                .withCustomSpecification(iccidSpec)
                .build();
        Page<AdvancedSearch> searchList = searchRepository.findAll(spec, pageRequest);

        List<AdvancedSearchDto> resultList = new ArrayList<>();
        searchList.getContent().forEach(s -> {
            AdvancedSearchDto dto = getAdvancedSearchDto(s);
            contractTypeRepository.findByCode(s.getContractTypeCode())
                    .ifPresent(name -> dto.getContractType().setName(name.getNameByLang(lang)));
            dto.getTariff().setName(dictionaryService.getChannelLocalName(dto.getTariff().getCode(), lang));
            String state = dto.getState().getCode();
            String stateCode = state.substring(0, state.indexOf("/", 5));
            String reasonCode = state.substring(state.indexOf('/', 5) + 1);
            subscriberStateRepository.findByCodeAndReason(stateCode, reasonCode)
                    .ifPresent(name -> dto.getState().setName(name.getNameByLang(lang)));

            resultList.add(dto);
        });

        ServiceResponseDto<Page<AdvancedSearchDto>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(new PageImpl<>(resultList, pageRequest, searchList.getTotalElements()));
        return serviceResponse;
    }

    public ServiceResponseDto<List<AdvancedSearchShorInfo>> advancedSearch(List<SearchCriterion> searchCriteria) {
        Specification<AdvancedSearch> spec = new SpecificationBuilder<AdvancedSearch>().build(searchCriteria);
        List<AdvancedSearchShorInfo> searchList = searchRepository.findBy(spec, q -> q.as(AdvancedSearchShorInfo.class).all());
        ServiceResponseDto<List<AdvancedSearchShorInfo>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(searchList);
        return serviceResponse;
    }

    private static AdvancedSearchDto getAdvancedSearchDto(AdvancedSearchInfo s) {
        AdvancedSearchDto dto = new AdvancedSearchDto();
        dto.setAccountId(s.getAccountId());
        dto.setMsisdn(s.getMsisdn());
        dto.setContractId(s.getContractId());
        dto.setActivationDate(s.getActivationDate());
        dto.setContractType(new UniversalDictionary(s.getContractTypeCode(), null));
        dto.setTariff(new UniversalDictionary(s.getTariffCode(), null));
        dto.setState(new UniversalDictionary(s.getStateCode(), null));
        dto.setFirstName(s.getFirstName());
        dto.setLastName(s.getLastName());
        dto.setMiddleName(s.getMiddleName());
        dto.setCompanyName(s.getCompanyName());
        return dto;
    }

    public ServiceResponseDto<AdvancedSearchFIO> getSubscriberName(Long accountId) throws BusinessException {
        AdvancedSearchFIO searchFIO;
        try {
            searchFIO = searchRepository.findDistinctByAccountId(accountId).orElseThrow();
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        ServiceResponseDto<AdvancedSearchFIO> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(searchFIO);
        return serviceResponse;
    }
}
