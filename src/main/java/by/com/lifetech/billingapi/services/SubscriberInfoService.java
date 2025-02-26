package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.*;
import by.com.lifetech.billingapi.models.dto.cboss.GetInfoFullDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationSubscriberDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.dto.SubscriberFullInfo;
import by.com.lifetech.billingapi.models.entity.ProductOffering;
import by.com.lifetech.billingapi.models.entity.SubscribersPhoto;
import by.com.lifetech.billingapi.models.entity.dictionary.DictCrmBalance;
import by.com.lifetech.billingapi.models.enums.*;
import by.com.lifetech.billingapi.models.repository.ProductOfferingsRepository;
import by.com.lifetech.billingapi.models.repository.SubscribersPhotoRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.*;
import by.com.lifetech.billingapi.models.responses.AvailableRetentionTariffs;
import by.com.lifetech.billingapi.models.responses.AvailableSpecificTariffs;
import by.com.lifetech.billingapi.models.responses.AvailableTariffsResponse;
import by.com.lifetech.billingapi.utils.BalanceFormatter;
import by.com.lifetech.billingapi.utils.SqlConstants;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SubscriberInfoService {
    public static final String COST_FO_ATTR_CODE = "COST_FO";
    private final ContractTypeRepository contractTypeRepository;
    private final SubscriberSegmentRepository subscriberSegmentRepository;
    private final SubscriberStateRepository subscriberStateRepository;
    private final LevelRiskFraudRepository levelRiskFraudRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final ProductOfferingsRepository productOfferingsRepository;
    private final DictCrmBalanceRepository dictCrmBalanceRepository;
    private final ServiceSubGroupRepository serviceSubGroupRepository;
    private final ServiceGroupRepository serviceGroupRepository;
    private final BalanceFlagMappingRepository balanceFlagMappingRepository;
    private final DictSubsRiskFraudRepository subsRiskFraudRepository;
    private final ChainService chainService;
    private final CBossService cBossService;
    private final DictionaryService dictionaryService;
    private final ProductOfferingService productOfferingService;
    private final DictCommunitiesRepository dictCommunitiesRepository;
    private final SubscribersPhotoRepository photoRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Value("${app.default.channel}")
    private String defaultChannel;

    Logger logger = LoggerFactory.getLogger(SubscriberInfoService.class);
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public ServiceResponseDto<List<SubscriberInfDto>> getSubscriberInf(String valueSearch, Lang lang)
            throws BusinessException, InternalException {
        // valueSearch - 12 digits for MSISDN, 18-19 for ICCID, 15 for IMSI and 9 for contract number
        Map<String, Object> map = new HashMap<>();
        map.put("valueSearch", valueSearch);

        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.CIM, "getSubscriberInfList", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }

        ServiceResponseDto<List<SubscriberInfDto>> serviceResponse = new ServiceResponseDto<>();
        List<SubscriberInfDto> subscriberInfDtoList;
        try {
            subscriberInfDtoList = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("LIST_ACCOUNTS"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain getSubscriberInfList response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }

        if (subscriberInfDtoList.size() == 1 && !subscriberInfDtoList.get(0).getState().getCode().contains("TRM")) {
            SubscriberInfDto sub = subscriberInfDtoList.get(0);
            sub.setReservations(cBossService.getReservation(sub.getMsisdn()).getCreEventList());
        }
        mapSubscriberDictAttrs(subscriberInfDtoList, lang);
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(subscriberInfDtoList);

        return serviceResponse;
    }

    private void mapSubscriberDictAttrs(List<SubscriberInfDto> subscriberInfDtoList, Lang lang) {
        subscriberInfDtoList.forEach(sub -> {
            sub.getTariff().setName(dictionaryService.getChannelLocalName(sub.getTariff().getCode(), lang));
            sub.getCategory().setName(dictionaryService.getChannelLocalName(sub.getCategory().getCode(), lang));

            contractTypeRepository.findByCode(sub.getContractType().getCode())
                    .ifPresent(name -> sub.getContractType().setName(name.getNameByLang(lang)));

            subscriberSegmentRepository.findByCode(sub.getSegment().getCode())
                    .ifPresent(name -> sub.getSegment().setName(name.getNameByLang(lang)));

            String state = sub.getState().getCode();
            String stateCode = state.substring(0, state.indexOf("/", 5));
            String reasonCode = state.substring(state.indexOf('/', 5) + 1);
            subscriberStateRepository.findByCodeAndReason(stateCode, reasonCode)
                    .ifPresent(name -> sub.getState().setName(name.getNameByLang(lang)));

            levelRiskFraudRepository.findByCode(sub.getRiskLevel().getCode())
                    .ifPresent(name -> sub.getRiskLevel().setName(name.getNameByLang(lang)));
            levelRiskFraudRepository.findByCode(sub.getSubProfileRisk().getCode())
                    .ifPresent(name -> sub.getSubProfileRisk().setName(name.getNameByLang(lang)));
            levelRiskFraudRepository.findByCode(sub.getFinRisk().getCode())
                    .ifPresent(name -> sub.getFinRisk().setName(name.getNameByLang(lang)));
            levelRiskFraudRepository.findByCode(sub.getGeoRisk().getCode())
                    .ifPresent(name -> sub.getGeoRisk().setName(name.getNameByLang(lang)));
            subsRiskFraudRepository.findByCode(sub.getRiskFraud().getCode())
                    .ifPresent(name -> sub.getRiskFraud().setName(name.getNameByLang(lang)));

            subscriptionTypeRepository.findByCode(sub.getLineLevel().getCode())
                    .ifPresent(name -> sub.getLineLevel().setName(name.getNameByLang(lang)));
            dictCommunitiesRepository.findByCode(sub.getCommunity().getCode())
                    .ifPresent(name -> sub.getCommunity().setName(name.getNameByLang(lang)));
        });
    }

    public ServiceResponseDto<AvailableTariffsResponse> getAvailableTariffs(String msisdn, Lang lang) throws InternalException {
        ServiceResponseDto<AvailableTariffsResponse> serviceResponse = new ServiceResponseDto<>();
        GetInformationSubscriberDto subs = cBossService.getInformation(msisdn).getSubscriber();
        if (subs == null) {
            serviceResponse.setDefaultErrorResponse().setResultDescription("msisdn not found");
            return serviceResponse;
        }
        boolean isResident = isResident(Long.valueOf(subs.getAccountId()));
        serviceResponse.setDefaultSuccessResponse();
        AvailableTariffsResponse tariffResponse = new AvailableTariffsResponse();
        tariffResponse.setBasicTariffs(productOfferingService.getAvailableTariffs(subs.getTariffPlan(), lang, !isResident));
        if (isResident) {
            tariffResponse.setSpecialTariffs(productOfferingService.getAvailableChangeOffers(subs.getTariffPlan(), lang));
        }
        else {
            tariffResponse.setSpecialTariffs(new ArrayList<>());
        }
        serviceResponse.setResultMap(tariffResponse);
        return serviceResponse;
    }

    public ServiceResponseDto<AvailableRetentionTariffs> getAvailableRetentionOffers(String msisdn, Lang lang) throws InternalException {
        ServiceResponseDto<AvailableRetentionTariffs> serviceResponse = new ServiceResponseDto<>();
        GetInformationSubscriberDto subs = cBossService.getInformation(msisdn).getSubscriber();
        if (subs == null) {
            serviceResponse.setDefaultErrorResponse().setResultDescription("msisdn not found");
            return serviceResponse;
        }
        serviceResponse.setDefaultSuccessResponse();
        AvailableRetentionTariffs tariffs = new AvailableRetentionTariffs();
        tariffs.setRetentionTariffs(productOfferingService.getAvailableSpecialOffers(msisdn, "RETENTION_OFFER", lang));
        serviceResponse.setResultMap(tariffs);
        return serviceResponse;
    }

    public ServiceResponseDto<AvailableSpecificTariffs> getAvailableSpecificOffers(String msisdn, Lang lang) throws InternalException {
        ServiceResponseDto<AvailableSpecificTariffs> serviceResponse = new ServiceResponseDto<>();
        GetInformationSubscriberDto subs = cBossService.getInformation(msisdn).getSubscriber();
        if (subs == null) {
            serviceResponse.setDefaultErrorResponse().setResultDescription("msisdn not found");
            return serviceResponse;
        }
        serviceResponse.setDefaultSuccessResponse();
        AvailableSpecificTariffs tariffs = new AvailableSpecificTariffs();
        tariffs.setSpecificTariffs(productOfferingService.getAvailableSpecialOffers(msisdn, "SPECIFIC_OFFER", lang));
        serviceResponse.setResultMap(tariffs);
        return serviceResponse;
    }

    public ServiceResponseDto<List<GroupServicesDto>> getServices(String msisdn, Lang lang) throws InternalException, BusinessException {
        ServiceResponseDto<List<GroupServicesDto>> serviceResponse = new ServiceResponseDto<>();
        Set<BillingProductDto> services = new HashSet<>();


        GetInfoFullDto subs = cBossService.getSubscriberFull(msisdn);
        boolean isOwnGroup = subs.getCreBalance().getBalances().stream()
                .filter(b -> b.getBalanceName().equals("Bundle_Flag_Flex_Disc_TP"))
                .anyMatch(b -> b.getValue() > 0d);
        List<ActiveBillingProductDto> actServices = getActiveBillingProducts(msisdn, subs.getCreBalance().getBalances(), defaultChannel);

        actServices.forEach(c -> {
            BillingProductDto bp = productOfferingService.getBillingProduct(productOfferingsRepository.findById(c.getCode()).orElseThrow(), lang);
            bp.setState(ProductState.valueOf(c.getState()));
            bp.setDateFrom(c.getDateFrom());
            bp.setDateTo(c.getDateTo());
            bp.setSubgroupCode("ACTIVE_GRACE_SERVICE");
            if (isOwnGroup) {
                String costFO = productOfferingsRepository.getProductAttribute(bp.getCode(), COST_FO_ATTR_CODE);
                if (costFO != null) {
                    bp.setServiceCost(costFO);
                }
            }
            services.add(bp);
        });

        productOfferingService.getAvailableServicesForTariff(subs.getCreAccount().getTariffPlan(),
                (subs.getCreAccount().isUseCommonMain() ? "CORP" : "IND")).forEach(s -> {
            BillingProductDto bp =  productOfferingService.getBillingProduct(s, lang);
            if (isOwnGroup) {
                s.getAttributes().stream().filter(attr -> attr.getCode().equals(COST_FO_ATTR_CODE)).findFirst()
                        .ifPresent(c -> bp.setServiceCost(c.getValue()));
            }
            services.add(bp);
        });


        List<GroupServicesDto> resultGroups = new ArrayList<>();
        serviceSubGroupRepository.findAll().forEach(sub -> {
            SubGroupServicesDto sgr = new SubGroupServicesDto();

            sgr.setSubGroupCode(sub.getCode());
            sgr.setSubGroupName(sub.getNameByLang(lang));
            sgr.setSort(sub.getSort());
            sgr.setServices(services.stream().filter(s -> s.getSubgroupCode().equals(sub.getCode())).collect(Collectors.toSet()));
            if (sgr.getServices().isEmpty()) {
                return;
            }
            int i = resultGroups.indexOf(new GroupServicesDto(sub.getGroupId()));
            if (i >= 0) {
                resultGroups.get(i).getSubgroups().add(sgr);
            } else {
                GroupServicesDto gr = new GroupServicesDto();
                gr.setGroupCode(sub.getGroupId());
                List<SubGroupServicesDto> subgroups = new ArrayList<>();
                subgroups.add(sgr);
                serviceGroupRepository.findById(sub.getGroupId()).ifPresent(r -> {
                    gr.setGroupName(r.getNameByLang(lang));
                    gr.setSort(r.getSort());
                });
                gr.setSubgroups(subgroups);
                resultGroups.add(gr);
            }
        });

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse
                .setResultMap(resultGroups.stream().sorted(Comparator.comparingInt(GroupServicesDto::getSort)).toList());
        return serviceResponse;
    }

    public ServiceResponseDto<List<ActiveServiceDto>> getActiveServices (String msisdn, String channel, Lang lang) throws BusinessException, InternalException {
        ServiceResponseDto<List<ActiveServiceDto>> serviceResponse = new ServiceResponseDto<>();
        GetInfoFullDto subs = cBossService.getSubscriberFull(msisdn);
        boolean isOwnGroup = subs.getCreBalance().getBalances().stream()
                .filter(b -> b.getBalanceName().equals("Bundle_Flag_Flex_Disc_TP"))
                .anyMatch(b -> b.getValue() > 0d);
        List<ActiveServiceDto> actServices = new ArrayList<>();

        getActiveBillingProducts(msisdn, subs.getCreBalance().getBalances(), channel).forEach(c -> {
            ProductOffering po = productOfferingsRepository.findById(c.getCode()).orElseThrow();
            ActiveServiceDto actService = new ActiveServiceDto();
            actService.setCode(c.getCode());
            po.getAttributes().stream().filter(attr -> attr.getCode().equals("COST")).findFirst()
                    .ifPresent(v -> {
                        actService.setFullCost(Double.parseDouble(v.getValue().replace(",",".")));
                        actService.setCost(Double.parseDouble(v.getValue().replace(",",".")));
                    });
            if (isOwnGroup) {
                po.getAttributes().stream().filter(attr -> attr.getCode().equals(COST_FO_ATTR_CODE)).findFirst()
                        .ifPresent(v -> actService.setCost(Double.parseDouble(v.getValue().replace(",","."))));
            }
            actService.setName(dictionaryService.getLocalNameByChannel(po.getCode(), channel, lang));
            if (actService.getName() == null) {
                actService.setName(dictionaryService.getChannelLocalName(po.getCode(), lang));
            }
            actService.setState(c.getState());
            actService.setDateFrom(c.getDateFrom());
            actService.setDateTo(c.getDateTo());
            if (po.getProductInfo() != null) {
                actService.setReactivation(po.getProductInfo().getReorderRule() != null);
            }
            actServices.add(actService);
        });

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(actServices);
        return serviceResponse;
    }

    public ServiceResponseDto<Set<BalanceGroupDto>> getBalances(String msisdn, Lang lang) throws InternalException, BusinessException {
        ServiceResponseDto<Set<BalanceGroupDto>> serviceResponse = new ServiceResponseDto<>();
        boolean hasSession = cBossService.hasGprsSession(msisdn);
        List<BalanceGroupDto> groups = getBalanceGroupDtos(cBossService.getAllBalances(msisdn), lang, hasSession);
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(groups.stream().sorted(Comparator.comparingInt(BalanceGroupDto::getSort))
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return serviceResponse;
    }

    public List<BalanceGroupDto> getBalanceGroupDtos(List<GetInformationBalanceDto> balanceList, Lang lang, boolean hasSession) {
        List<BalanceGroupDto> groups = new ArrayList<>();
        mapUnlimBalanceDates(balanceList).forEach(b -> {
            if (hasSession && b.getBalanceName().equals("Bundle_Flag_Infinite_TP_Speed") && b.getValue() == 5d) {
                b.setReserved(1d);
            }
            BalanceInfDto balInf = new BalanceInfDto(b);
            fillPocketsNames(balInf);
            fillBalanceInfo(b, balInf, groups, lang);
        });

        return formatBalanceGroupAmounts(groups);
    }

    private void fillBalanceInfo(GetInformationBalanceDto balance, BalanceInfDto balanceInfo, List<BalanceGroupDto> groups, Lang lang) {
        dictCrmBalanceRepository.findByCode(balance.getBalanceName()).ifPresent(d -> {
            balanceInfo.setName(d.getNameByLang(lang));
            balanceInfo.setSort(d.getSort());
            balanceInfo.setGroupCode(d.getGroupCode());
            balanceInfo.setMeasureCode(d.getMeasureCode());

            groups.add(createBalanceGroup(balanceInfo, d, groups));
        });
    }

    private void fillPocketsNames(BalanceInfDto balanceInfo) {
        balanceInfo.getPockets().forEach(p -> {
            if (p.getCode() != null) {
                dictCrmBalanceRepository.getPocketName(p.getCode()).ifPresentOrElse(p::setName, () -> p.setName(p.getCode()));
            }
        });
    }

    private BalanceGroupDto createBalanceGroup(BalanceInfDto balanceInfo, DictCrmBalance dictCrmBalance, List<BalanceGroupDto> groups) {
        BalanceGroupDto balanceGroup = new BalanceGroupDto();
        balanceGroup.setGroupCode(balanceInfo.getGroupCode());
        balanceGroup.setGroupName(dictCrmBalance.getGroupName());
        balanceGroup.setSort(dictCrmBalance.getGroupOrder());

        int balanceGroupIndex = groups.indexOf(balanceGroup);
        if (balanceGroupIndex >= 0) {
            balanceGroup = groups.get(balanceGroupIndex);
        }

        if (balanceGroup.getBalances() == null) {
            balanceGroup.setBalances(new ArrayList<>());
        }

        balanceGroupIndex = balanceGroup.getBalances().indexOf(balanceInfo);
        if (balanceGroupIndex >= 0) {
            balanceGroup.getBalances().get(balanceGroupIndex).getPockets().addAll(balanceInfo.getPockets());
        } else {
            balanceGroup.getBalances().add(balanceInfo);
        }
        return balanceGroup;
    }

    private List<BalanceGroupDto> formatBalanceGroupAmounts(List<BalanceGroupDto> groups) {
        groups.forEach(gr -> gr.getBalances().forEach(bl -> {
            bl.setFormattedValue(BalanceFormatter.getFormattedValue(bl.getAmount(), bl.getMeasureCode()));
            bl.getPockets().forEach(pl -> pl.setFormattedValue(BalanceFormatter.getFormattedValue(pl.getAmount(), bl.getMeasureCode())));
        }));
        return groups;
    }

    public ServiceResponseDto<SubscriberFullInfo> getSubscriberFull(String msisdn, Lang lang)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);

        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.CIM, "getSubscriberFull", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }

        ServiceResponseDto<SubscriberFullInfo> serviceResponse = new ServiceResponseDto<>();
        SubscriberFullInfo subscriberFullInfo;
        try {
            subscriberFullInfo = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("resultMap"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain getSubscriberFull response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        mapSubscriberDictAttrs(Collections.singletonList(subscriberFullInfo), lang);
        String offerName = dictionaryService.getChannelLocalName(subscriberFullInfo.getEquipmentInformation().getDeviceId(), lang);
        if (offerName != null) {
            subscriberFullInfo.getEquipmentInformation().setOfferName(offerName);
        } else {
            subscriberFullInfo.getEquipmentInformation().setOfferName(subscriberFullInfo.getEquipmentInformation().getDeviceName());
        }

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(subscriberFullInfo);
        return serviceResponse;
    }

    private List<GetInformationBalanceDto> mapUnlimBalanceDates(List<GetInformationBalanceDto> balances) {

        balances.forEach(b -> {
            if (b.getValue() < 1) {
                return;
            }
            balanceFlagMappingRepository.findByBalanceName(b.getBalanceName()).stream().findFirst()
                    .flatMap(first -> Optional.of(first)
                                    .flatMap(m -> balances.stream()
                                                    .filter(f -> f.getBalanceName().equals(m.getFlagName()))
                                                    .filter(f -> f.getValue() > 0).findFirst()
                                    )
                    )
                    .ifPresent(flag -> {
                        b.setStart(flag.getStart());
                        b.setEnd(flag.getEnd());
                    });
        });

        return balances;
    }

    private List<ActiveBillingProductDto> getActiveBillingProducts(String msisdn, List<GetInformationBalanceDto> balanceList, String channel) throws BusinessException, InternalException {
        List<ActiveBillingProductDto> serviceList = productOfferingService.getActiveProductsDates(msisdn, channel);
        for (GetInformationBalanceDto b : balanceList) {
            if (b.getBalanceName().equals("Bundle_Flag_CL_Active") && b.getValue() > 0d) {
                serviceList.stream().filter(s -> s.getCode().contains("S_CL_")).findFirst()
                        .ifPresent(cl -> cl.setDateTo(b.getEnd().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ".000+03:00"));
            }
        }
        serviceList = serviceList.stream().filter(s -> !s.getType().equals("TARIFF")).toList();
        return serviceList;
    }

    public ServiceResponseDto<TariffInfoDto> getTariffInfo(String msisdn, Lang lang)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.OM, "getTariffDates", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        ServiceResponseDto<TariffInfoDto> serviceResponse = new ServiceResponseDto<>();
        TariffInfoDto tariffInfoDto;
        try {
            tariffInfoDto = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("TARIFF_INFO"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        tariffInfoDto.setTariffName(dictionaryService.getChannelLocalName(tariffInfoDto.getTariffCode(), lang));
        tariffInfoDto.setMidName(dictionaryService.getChannelLocalName(tariffInfoDto.getMidCode(), lang));
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(tariffInfoDto);
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Double>> getCompensationLimit (String msisdn) throws BusinessException, InternalException {
        String accountId = cBossService.getAccountId(msisdn);
        Double limit;
        try {
            limit = (Double) entityManager.createNativeQuery(SqlConstants.COMPENSATION_LIMIT_SQL, Double.class)
                    .setParameter(1, accountId).getSingleResult();
        } catch (NoResultException e) {
            limit = 0d;
        }
        ServiceResponseDto<Map<String, Double>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(Map.of("limit", limit));
        return serviceResponse;
    }

    public byte[] getPhoto(String msisdn, String contractId) {
        Optional<SubscribersPhoto> photoData = photoRepository.findByPhotoId(msisdn, contractId);
        byte[] photo = null;
        try {
            photo = photoData.orElseThrow().getImageData();
        } catch (NoSuchElementException e) {
            logger.info("Photo not found in table SUBSCRIBERS_PHOTO");
        }
        return photo;
    }

    private boolean isResident(Long accountId) {
        String passportType;
        Set<String> documentTypes = EnumSet.allOf(DocumentType.class)
                .stream()
                .filter(type -> type.getCitizenship().equals(Citizenship.RESIDENT))
                .map(DocumentType::name)
                .collect(Collectors.toSet());
        try {
            passportType = (String) entityManager.createNativeQuery(SqlConstants.GET_PASSPORT_TYPE_BY_ID_SQL, String.class)
                    .setParameter(1, accountId).getSingleResult();
        } catch (Exception e) {
            return false;
        }
        return documentTypes.contains(passportType);
    }
}
