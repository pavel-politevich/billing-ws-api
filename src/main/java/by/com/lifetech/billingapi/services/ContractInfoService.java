package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.*;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.dictionary.CodeNameDictionary;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.dictionary.*;
import by.com.lifetech.billingapi.models.requests.UpdateIndAddress;
import by.com.lifetech.billingapi.models.requests.UpdateIndContractRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.SqlConstants;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractInfoService {
    Logger logger = LoggerFactory.getLogger(ContractInfoService.class);
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    private final ChainService chainService;
    private final CBossService cBossService;
    private final SubscriberInfoService subscriberInfoService;
    private final DictAdrCountryRepository dictAdrCountryRepository;
    private final DictAdrRegionRepository dictAdrRegionRepository;
    private final DictAdrDistrictRepository dictAdrDistrictRepository;
    private final DictAdrCityTypeRepository dictAdrCityTypeRepository;
    private final DictAdrCityRepository dictAdrCityRepository;
    private final DictAdrStreetTypeRepository dictAdrStreetTypeRepository;
    private final SubscriberStateRepository subscriberStateRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final DictionaryService dictionaryService;
    private final DictOccupationRepository dictOccupationRepository;
    private final DictInformationSourceRepository dictInformationSourceRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ServiceResponseDto<ContractDetails> getContractInfo(String contractId, Lang lang) throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("contractCode", contractId);

        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.CIM, "getContractInfo", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());
        }

        ServiceResponseDto<ContractDetails> serviceResponse = new ServiceResponseDto<>();
        ContractDetails contractDetails;
        try {
            contractDetails = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("resultMap"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());
        }

        ContractAddressInfo address = contractDetails.getAddressInfo();
        setDictionaryName(address.getCountry(), lang, dictAdrCountryRepository::findById);
        setDictionaryName(address.getRegion(), lang, dictAdrRegionRepository::findById);
        setDictionaryName(address.getDistrict(), lang, dictAdrDistrictRepository::findById);
        setDictionaryName(address.getCityType(), lang, dictAdrCityTypeRepository::findById);
        setDictionaryName(address.getCity(), lang, dictAdrCityRepository::findById);
        setDictionaryName(address.getStreetType(), lang, dictAdrStreetTypeRepository::findById);
        contractDetails.setAddressInfo(address);

        ContractAdditionalInfo additionalInfo = contractDetails.getContractAdditionalInfo();
        setDictionaryName(additionalInfo.getOccupation(), lang, dictOccupationRepository::findById);
        setDictionaryName(additionalInfo.getSourceInfo(), lang, dictInformationSourceRepository::findById);

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(contractDetails);

        return serviceResponse;
    }

    public ServiceResponseDto<List<ContractLineShort>> getContractLines(String contractId, Lang lang) throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("contractCode", contractId);

        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.CIM, "getContractLines", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());
        }

        ServiceResponseDto<List<ContractLineShort>> serviceResponse = new ServiceResponseDto<>();
        List<ContractLineShort> contractLines;
        try {
            contractLines = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("resultMap"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain: getContractLines response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());
        }

        contractLines.forEach(sub -> {
            String state = sub.getState().getCode();
            String stateCode = state.substring(0, state.indexOf("/", 5));
            String reasonCode = state.substring(state.indexOf('/', 5) + 1);
            subscriberStateRepository.findByCodeAndReason(stateCode, reasonCode)
                    .ifPresent(name -> sub.getState().setName(name.getNameByLang(lang)));
            subscriptionTypeRepository.findByCode(sub.getLineLevel().getCode())
                    .ifPresent(name -> sub.getLineLevel().setName(name.getNameByLang(lang)));
            sub.getTariff().setName(dictionaryService.getChannelLocalName(sub.getTariff().getCode(), lang));
        });

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(contractLines);

        return serviceResponse;
    }

    private <T> void setDictionaryName(UniversalDictionary attribute, Lang lang
            , Function<String, Optional<T>> findFunction) {
        Optional.ofNullable(attribute)
                .map(UniversalDictionary::getCode).flatMap(findFunction).ifPresent(v ->
                        attribute.setName(((CodeNameDictionary)v).getNameByLang(lang)));
    }
    
    public ServiceResponseDto<Map<String, Object>> updateIndContract(UpdateIndContractRequest req) throws InternalException, BusinessException {
    	Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(req, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }
        
        try {
            chainService.executeChain(ChainType.CIM, "UpdatePassportAllLines", requestMap);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());

        }

        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<>();
        return serviceResponse.setDefaultSuccessResponse();
    }

    public ServiceResponseDto<Set<BalanceGroupDto>> getBalances(String contractId, Lang lang) throws BusinessException, InternalException {
        ServiceResponseDto<Set<BalanceGroupDto>> serviceResponse = new ServiceResponseDto<>();
        Long accountId;
        try {
            accountId = (Long) entityManager.createNativeQuery(SqlConstants.GET_TOP_ID_BY_CONTRACT_SQL, Long.class)
                    .setParameter(1, contractId).getSingleResult();
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        List<BalanceGroupDto> groups = subscriberInfoService.getBalanceGroupDtos(cBossService.getBalancesByAccId(String.valueOf(accountId)), lang, false);
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(groups.stream().sorted(Comparator.comparingInt(BalanceGroupDto::getSort))
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Object>> updateIndAddress(UpdateIndAddress req) throws InternalException, BusinessException {
        Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(req, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }
        try {
            chainService.executeChain(ChainType.CIM, "UpdateAddress", requestMap);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), BusinessErrorCode.CONTRACT_NOT_FOUND.getMessage());

        }
        ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<>();
        return serviceResponse.setDefaultSuccessResponse();
    }
}
