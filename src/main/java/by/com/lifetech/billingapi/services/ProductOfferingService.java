package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.*;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.OmWebAttribute;
import by.com.lifetech.billingapi.models.entity.ProductOffering;
import by.com.lifetech.billingapi.models.entity.SalesPortalMid;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.enums.ProductState;
import by.com.lifetech.billingapi.models.repository.*;
import by.com.lifetech.billingapi.models.requests.RegServicesActivationRequest;
import by.com.lifetech.billingapi.models.requests.StartPackRequest;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOfferingService {

	Logger logger = LoggerFactory.getLogger(ProductOfferingService.class);
	
	@Value("${app.default.channel}")
	private String defaultChannel;
	private final ProductOfferingsRepository productOfferingsRepository;
	private final TariffRelationshipRepository tariffRelationshipRepository;
	private final WebAttributeRepository webAttrRepository;
	private final SalesPortalMidRepository salesPortalMidRepository;
	private final StartPackOpenedRepository startPackOpenedRepository;
	private final CrmSpecialOffersRepository specialOffersRepository;
	private final AssetTypeRepository assetTypeRepository;
	private final DictionaryService dictionaryService;
	private final ChainService chainService;
	private final CacheManager cacheManager;
	private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

	@PersistenceContext
	private EntityManager entityManager;

	public List<ProductOffering> getAvailableServicesForTariff(String tariffCode, String subscriberType) {
		return getProductOfferings(productOfferingsRepository.getAvailableProducts(tariffCode, subscriberType, defaultChannel));
	}

	private List<ProductOffering> getProductOfferings (List<String> poList) {
		List<ProductOffering> resultList = new ArrayList<>();
		List<String> notInCacheIds = new ArrayList<>();
		poList.forEach(s -> {
			ProductOffering po = cacheManager.getCache("productOfferings").get(s, ProductOffering.class);
			if (po != null) {
				resultList.add(po);
			}
			else {
				notInCacheIds.add(s);
			}
		});
		if (!notInCacheIds.isEmpty()) {
			productOfferingsRepository.findAllById(notInCacheIds).forEach(po -> {
				cacheManager.getCache("productOfferings").put(po.getCode(), po);
				resultList.add(po);
			});
		}
		return resultList;
	}
	
	public List<ActiveBillingProductDto> getActiveProductsDates(String msisdn, String channel) throws BusinessException, InternalException {

		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		map.put("channel", channel);
		ChainResult chainResult = chainService.executeChain(ChainType.OM, "getActiveProductsDates", map);

		List<ActiveBillingProductDto> actProductList;
		try {
			actProductList = objectMapper.readValue(chainResult.getResultList().stream()
					.filter(el -> el.getName().equals("result")).findFirst().orElseThrow().getValue().toString(),
                    new TypeReference<>() {});
		} catch (JsonProcessingException e) {
			logger.error("Can not parse chain response. Error: {}", e.getMessage());
			throw new InternalException("Error when receiving active products");
		}
		return actProductList;
	}

	@Cacheable(value = "availableBasicTariffs")
	public List<TariffChangeDto> getAvailableTariffs(String tariffCode, Lang lang, boolean nonResident) {
		List<TariffChangeDto> tariffs = new ArrayList<>();
		tariffRelationshipRepository.findAllBySourceProductAndType(tariffCode, "ALLOW_CHANGE").forEach(t -> {
			if (nonResident && t.getNonResident() == 0) {
				return;
			}
			if (!nonResident && t.getNonResident() == 1) {
				return;
			}
			TariffChangeDto tdto = new TariffChangeDto();
			tdto.setCode(t.getTargetProduct());
			tdto.setName(dictionaryService.getChannelLocalName(tdto.getCode(), lang));
			tdto.setTariffChangeFee(Double.parseDouble(t.getCost().replace(",", ".")));
			tdto.setTariffCost(Double
					.parseDouble(productOfferingsRepository.getProductAttribute(tdto.getCode(), "COST").replace(",", ".")));
			tdto.setTariffInfo(webAttrRepository.getAttrsByChannel(tdto.getCode(), "LIFE_CRM", tdto.getCode(), "1").stream()
					.collect(Collectors.toMap(c -> c.getAttrCode().getCode(), OmWebAttribute::getValue)));
			tariffs.add(tdto);
		});

		return tariffs.stream().sorted(Comparator.comparingDouble(TariffChangeDto::getTariffCost).reversed())
				.collect(Collectors.toList());
	}

	@Cacheable(value = "availableChangeTariffs")
	public List<TariffChangeDto> getAvailableChangeOffers(String tariffCode, Lang lang) {
		List<TariffChangeDto> tariffs = new ArrayList<>();
		List<TariffRelationshipInfo> relationshipList = tariffRelationshipRepository.findDistinctByTypeAndCostAndMidNotNull("ALLOW_CHANGE","0");
		relationshipList
				.forEach(t -> {
					TariffChangeDto tdto = new TariffChangeDto();
					tdto.setCode(t.getTargetProduct());
					tdto.setMid(t.getMid());
					tdto.setName(dictionaryService.getChannelLocalName(t.getTargetProduct(), lang));
					tdto.setTariffChangeFee(Double.parseDouble(t.getCost().replace(",", ".")));
					tdto.setTariffCost(Double.parseDouble(
							productOfferingsRepository.getProductAttribute(tdto.getMid(), "COST").replace(",", ".")));
					tdto.setTariffInfo(webAttrRepository.getAttrsByChannel(tdto.getMid(), "LIFE_CRM", tdto.getCode(), "1")
							.stream().collect(Collectors.toMap(c -> c.getAttrCode().getCode(),
									OmWebAttribute::getValue)));
					tariffs.add(tdto);
				});

		return tariffs.stream().sorted(Comparator.comparingDouble(TariffChangeDto::getTariffCost).reversed())
				.collect(Collectors.toList());
	}

	public List<TariffChangeDto> getAvailableSpecialOffers(String msisdn, String type, Lang lang) {
		List<TariffChangeDto> tariffs = new ArrayList<>();
		specialOffersRepository.findAll().stream().filter(f -> f.getOfferType().equals(type))
				.forEach(t -> {
					if (t.getNeedCheck()) {
						Map<String, Object> map = new HashMap<>();
						map.put("msisdn", msisdn);
						map.put("mid", t.getMid());
						try {
							chainService.executeChain(ChainType.OM, "LH_Change_Offer_Check", map);
						} catch (BusinessException | InternalException e) {
							return;
						}
					}
					TariffChangeDto tdto = new TariffChangeDto();
					tdto.setCode(t.getTariffCode());
					tdto.setMid(t.getMid());
					tdto.setName(dictionaryService.getLocalNameByChannel(t.getMid(), "LIFECRM", lang));
					if (tdto.getName() == null) {
						tdto.setName(dictionaryService.getChannelLocalName(t.getTariffCode(), lang));
					}
					tdto.setTariffChangeFee(0d);
					tdto.setTariffCost(Double.parseDouble(
							productOfferingsRepository.getProductAttribute(tdto.getMid(), "COST").replace(",", ".")));
					tdto.setTariffInfo(webAttrRepository.getAttrsByChannel(tdto.getMid(), "LIFE_CRM", tdto.getCode(), "1")
							.stream().collect(Collectors.toMap(c -> c.getAttrCode().getCode(),
									OmWebAttribute::getValue)));
					tariffs.add(tdto);
				});
		return tariffs;
	}
	
	public BillingProductDto getBillingProduct(ProductOffering po, Lang lang) {
		logger.debug("Search BillingProduct for productId: {}", po.getCode());
		
		BillingProductDto pr = new BillingProductDto();
		pr.setCode(po.getCode());
		pr.setState(ProductState.DEACTIVE);
		po.getAttributes().stream().filter(attr -> attr.getCode().equals("COST")).findFirst()
				.ifPresent(c -> pr.setServiceCost(c.getValue()));
		pr.setProductType(po.getProductType());
		pr.setProductInfo(po.getProductInfo());
		if (po.getProductInfo() != null) {
			pr.setName(po.getNameByLang(lang));
		}
		else {
			String localName = dictionaryService.getChannelLocalName(po.getCode(), lang);
			pr.setName((localName != null)? localName: pr.getCode());
		}
		pr.setSubgroupCode(po.getGroupCode());
		
		if (pr.getProductInfo() != null) {
			pr.setReactivation(pr.getProductInfo().getReorderRule() != null);
			pr.setDeactivation(pr.getProductInfo().getRefuseRule() != null);
		}
		return pr;
	}

	public ServiceResponseDto<List<SalesPortalMid>> getSalesPortalMids() {
		ServiceResponseDto<List<SalesPortalMid>> response = new ServiceResponseDto<>();
		response.setDefaultSuccessResponse().setResultMap(salesPortalMidRepository.findAll());
		return response;
	}

	public ServiceResponseDto<List<TariffRegistrationDto>> getTariffsForRegistration(StartPackRequest req, Lang lang) {
		List<TariffRegistrationDto> tariffs = new ArrayList<>();
		startPackOpenedRepository.findDistinctBy().stream()
				.filter(f -> f.getMethodOfSale().contains(req.getMethodOfSale()))
				.filter(f -> f.getCustomerType().contains(req.getCustomerType()))
				.filter(f -> f.isNonresident() == req.isNonresident())
				.filter(f -> f.getAge() <= req.getAge())
				.forEach(t -> {
					TariffRegistrationDto tdto = new TariffRegistrationDto();
					tdto.setCode(t.getTariffCode());
					tdto.setName(dictionaryService.getChannelLocalName(t.getTariffCode(), lang));
					tariffs.add(tdto);
				});
		ServiceResponseDto<List<TariffRegistrationDto>> response = new ServiceResponseDto<>();
		response.setDefaultSuccessResponse().setResultMap(new ArrayList<>(new HashSet<>(tariffs)));
		return response;
	}

	public ServiceResponseDto<List<CategoryRegistrationDto>> getOffersForRegistration(StartPackRequest req, String tariffCode, Lang lang){
		List<CategoryRegistrationDto> resultList = new ArrayList<>();
		startPackOpenedRepository.findStartPacks(req.getMethodOfSale(), req.getCustomerType(), tariffCode, req.getAge()).forEach(s -> {
			CategoryRegistrationDto category = new CategoryRegistrationDto();
			category.setCode(s.getCode());
			category.setName(dictionaryService.getChannelLocalName(s.getCode(), lang));
			category.setAge(s.getAge());
			if (category.getCode() == null) {
				category.setCost(Double.parseDouble(productOfferingsRepository.getProductAttribute(tariffCode, "COST").replace(",", ".")));
			}
			else {
				category.setCost(Double.parseDouble(s.getIuiCost().replace(",",".")));
			}
			category.setNonResident(s.isNonresident());
			category.setDevice(assetTypeRepository.findByCode(s.getCode()));
			resultList.add(category);
		});
		ServiceResponseDto<List<CategoryRegistrationDto>> response = new ServiceResponseDto<>();
		response.setDefaultSuccessResponse().setResultMap(resultList);
		return response;
	}

	public ServiceResponseDto<Integer> massActivationServices(RegServicesActivationRequest request){
		int groupId = 0;
		for (String productCode : request.getProductList()) {
			Session session = entityManager.unwrap( Session.class );
			int finalGroupId = groupId;
			groupId = session.doReturningWork(
					connection -> {
						try (CallableStatement function = connection
								.prepareCall(
										"{ ? = call mes.insert_service_tbl(?, ?, ?, ?, ?, ?) }" )) {
							function.registerOutParameter( 1, Types.INTEGER );
							function.setString( 2, request.getMsisdn() );
							function.setString( 3, "life CRM" );
							function.setString( 4, productCode );
							function.setString( 5, "ORDER_WITHOUT_SEGMENT" );
							function.setInt( 6, 0 );
							function.setInt( 7, finalGroupId);
							function.execute();
							return function.getInt( 1 );
						}
					} );
		}
		ServiceResponseDto<Integer> response = new ServiceResponseDto<>();
		response.setDefaultSuccessResponse().setResultMap(groupId);
		return response;
	}
}
