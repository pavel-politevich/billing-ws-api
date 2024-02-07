package by.com.lifetech.billingapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationDto;

@Service
public class CBossService {

	Logger logger = LoggerFactory.getLogger(CBossService.class);

	RestTemplate restTemplate = new RestTemplate();
	XmlMapper xmlMapper = new XmlMapper();

	@Value("${cboss.url}")
	private String cBossUrl;

	public GetInformationDto getInformation(String msisdn) throws BusinessException {

		xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		String fooResourceUrl = cBossUrl + "/c_boss?cmd=get_information&msisdn=" + msisdn;
		ResponseEntity<String> xml = restTemplate.getForEntity(fooResourceUrl, String.class);
		GetInformationDto cbossInf = null;
		try {
			cbossInf = xmlMapper.readValue(xml.getBody(), GetInformationDto.class);
		} catch (JsonMappingException e) {
			logger.error("Can not get information from c_boss by msisdn: {0}", msisdn);
			throw new BusinessException("c_boss api service error");
		} catch (JsonProcessingException e) {
			logger.error("Can not get information from c_boss by msisdn: {0}", msisdn);
			throw new BusinessException("c_boss api service error");
		}
		return cbossInf;
	}

	public List<GetInformationBalanceDto> getBalances(String msisdn) throws BusinessException {

		if (getInformation(msisdn).getSubscriber() == null) {
			throw new BusinessException(String.format("subscriber %s not found", msisdn));
		}
		return getInformation(msisdn).getSubscriber().getBalance().stream()
				.filter((balance) -> balance.getValue() > 0).collect(Collectors.toList());
	}

	public String getAccountId(String msisdn) throws BusinessException {

		if (getInformation(msisdn).getSubscriber() == null) {
			throw new BusinessException(String.format("subscriber %s not found", msisdn));
		}
		return getInformation(msisdn).getSubscriber().getAccount_id();
	}
}
