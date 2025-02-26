package by.com.lifetech.billingapi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.cboss.*;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import by.com.lifetech.billingapi.exceptions.BusinessException;

@Service
@RequiredArgsConstructor
public class CBossService {

	Logger logger = LoggerFactory.getLogger(CBossService.class);

	private final RestTemplate restTemplateWithConnectTimeout;
	private final ExceptionUtils exceptionUtils;
	XmlMapper xmlMapper = new XmlMapper();

	@Value("${cboss.url}")
	private String cBossUrl;
	@Value("${cboss.username}")
	private String cBossUser;
	@Value("${cboss.password}")
	private String cBossPass;
	@Value("${spring.profiles.active:}")
	private String activeProfiles;

	public GetInformationDto getInformation(String msisdn) throws InternalException {

		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_information&msisdn=" + msisdn;
		GetInformationDto cbossInf;
		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.exchange(fooResourceUrl, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
			cbossInf = xmlMapper.readValue(xml.getBody(), GetInformationDto.class);
		} catch (JsonProcessingException | RestClientException e) {
			logger.error("Can not get information from c_boss by msisdn: {}", msisdn);
			throw new ExternalServiceException("c_boss api service error");
		}
		return cbossInf;
	}
	
	public GetInfoFullDto getInfoFull(String msisdn) throws BusinessException, InternalException {
		
		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_info_full&msisdn=" + msisdn;
		GetInfoFullDto cbossInf;
		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.exchange(fooResourceUrl, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
			if (xml.getBody().contains("ERROR: Unknown msisdn")) {
				throw exceptionUtils.getMsisdnNotFoundException();
			}
			cbossInf = xmlMapper.readValue(xml.getBody(), GetInfoFullDto.class);
		} catch (JsonProcessingException | RestClientException e) {
			logger.error("Can not get information from c_boss by msisdn: {}", msisdn);
			throw new ExternalServiceException("c_boss api service error");
		}
		
		return cbossInf;		
	}

	public GetInformationDto getInformationAccount(String accountId) throws InternalException {
		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_information_account&account=" + accountId;
		GetInformationDto cbossInf;
		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.exchange(fooResourceUrl, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
			cbossInf = xmlMapper.readValue(xml.getBody(), GetInformationDto.class);
		} catch (JsonProcessingException | RestClientException e) {
			logger.error("Can not get information from c_boss by acc_id: {}", accountId);
			throw new ExternalServiceException("c_boss api service error");
		}
		return cbossInf;
	}

	public List<GetInformationBalanceDto> getBalances(String msisdn) throws BusinessException, InternalException {

		GetInformationDto getInformationDto = getInformation(msisdn);
		if (getInformationDto.getSubscriber() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return getInformationDto.getSubscriber().getBalances().stream()
				.filter((balance) -> balance.getValue() != 0).collect(Collectors.toList());
	}

	public List<GetInformationBalanceDto> getAllBalances(String msisdn) throws InternalException, BusinessException {

		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_information_all&msisdn=" + msisdn;
		GetInformationDto cbossInf;
		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.exchange(fooResourceUrl, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
			cbossInf = xmlMapper.readValue(xml.getBody(), GetInformationDto.class);
		} catch (JsonProcessingException | RestClientException e) {
			logger.error("Can not get information_all from c_boss by msisdn: {}", msisdn);
			throw new ExternalServiceException("c_boss api service error");
		}
		List<GetInformationBalanceDto> balances = cbossInf.getSubscriber().getBalances();

		// Russia roaming disc logic
		LocalDateTime startDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
		LocalDateTime endDate = LocalDate.now().withDayOfMonth(1).plusMonths(1).atStartOfDay();
		List<GetInformationBalanceDto> roamBalances = getSubscriberFull(msisdn).getCreBalance().getBalances().stream()
				.filter(b -> List.of("RGPRS_Russia_Roam_Disc","RMTC_Russia_Roam_Disc","RMOC_Russia_Roam_Disc").contains(b.getBalanceName()))
				.peek(b -> {
					b.setStart(startDate);
					b.setEnd(endDate);
					balances.add(b);
                })
				.toList();

		return balances.stream()
				.filter((balance) -> balance.getValue() != 0 || roamBalances.contains(balance))
				.filter((balance) -> balance.getEnd() == null || balance.getEnd().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());
	}

	public List<GetInformationBalanceDto> getBalancesByAccId(String accountId) throws BusinessException, InternalException {

		GetInformationDto getInformationDto = getInformationAccount(accountId);
		if (getInformationDto.getSubscriber() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return getInformationDto.getSubscriber().getBalances().stream()
				.filter((balance) -> balance.getValue() != 0).collect(Collectors.toList());
	}

	public String getAccountId(String msisdn) throws BusinessException, InternalException {
		GetInformationDto getInformationDto = getInformation(msisdn);
		if (getInformationDto.getSubscriber() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return getInformationDto.getSubscriber().getAccountId();
	}

	public String getMsisdn(String accountId) throws BusinessException, InternalException {
		GetInformationDto getInformationDto = getInformationAccount(accountId);
		if (getInformationDto.getSubscriber() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return getInformationDto.getSubscriber().getMsisdn();
	}
	
	public GetInformationSubscriberDto getSubscriber(String msisdn) throws BusinessException, InternalException {
		GetInformationSubscriberDto sub = getInformation(msisdn).getSubscriber();
		if (sub == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return sub;
	}
	
	public GetInfoFullDto getSubscriberFull(String msisdn) throws BusinessException, InternalException {
		GetInfoFullDto sub =  getInfoFull(msisdn);
		if (sub.getCreAccount() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}
		return sub;
	}

	public GetReservationDto getReservation(String msisdn) throws BusinessException, InternalException {
		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_reservation&msisdn=" + msisdn;
		GetReservationDto cbossInf;
		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.exchange(fooResourceUrl, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), String.class);
			if (xml.getBody() != null && xml.getBody().contains("Fatal error")) {
				throw exceptionUtils.getMsisdnNotFoundException();
			}
			cbossInf = xmlMapper.readValue(xml.getBody(), GetReservationDto.class);
		} catch (JsonProcessingException | RestClientException e) {
			logger.error("Can not get information from c_boss by msisdn: {}", msisdn);
			throw new ExternalServiceException("c_boss api service error");
		}
		return cbossInf;
	}

	public boolean hasGprsSession(String msisdn) throws BusinessException, InternalException {
		List<CreEvent> eventList = getReservation(msisdn).getCreEventList();
		boolean result = false;
		if (eventList == null) {
			return false;
		}
		for (CreEvent e: eventList) {
            if (e.getId() != null && e.getId().contains("huawei.com")) {
                result = true;
                break;
            }
		}
		return result;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		if (activeProfiles == null) {
			return requestHeaders;
		}
		for (String profileName : activeProfiles.split(",")) {
			if (profileName.equalsIgnoreCase("prod")) {
				requestHeaders.setBasicAuth(cBossUser, cBossPass);
				return requestHeaders;
			}
		}
		return requestHeaders;
	}
}
