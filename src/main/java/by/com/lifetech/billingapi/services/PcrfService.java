package by.com.lifetech.billingapi.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.PcrfInfoDto;
import by.com.lifetech.billingapi.models.dto.PcrsServiceDto;
import by.com.lifetech.billingapi.models.dto.cboss.GetInfoFullDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.dictionary.PcrfAttrRepository;

@Service
@RequiredArgsConstructor
public class PcrfService {
	Logger logger = LoggerFactory.getLogger(PcrfService.class);

	private final RestTemplate restTemplateWithConnectTimeout;
	private final ExceptionUtils exceptionUtils;

	@Value("${pcrf.url}")
	private String pcrfUrl;
	@Value("${pcrf.username}")
	private String pcrfUsername;
	@Value("${pcrf.password}")
	private String pcrfPass;

	@Value("classpath:/static/pcrfTemplate.xml")
	Resource resourceFile;

	private final CBossService cBossService;
	private final PcrfAttrRepository pcrfAttrRepository;

	public ServiceResponseDto<PcrfInfoDto> getPcrfData(String msisdn, Lang lang) throws BusinessException, InternalException {
		ServiceResponseDto<PcrfInfoDto> serviceResponse = new ServiceResponseDto<PcrfInfoDto>();
		GetInfoFullDto cbossInfoFull = cBossService.getInfoFull(msisdn);

		if (cbossInfoFull.getNpmSubscriberMap() == null) {
			throw exceptionUtils.getMsisdnNotFoundException();
		}

		String imsi = cbossInfoFull.getNpmSubscriberMap().getImsi();

		String pcrfRequest = asString(resourceFile)
				.replace("IMSI", imsi)
				.replace("${username}", pcrfUsername)
				.replace("${password}", pcrfPass);

		List<PcrsServiceDto> pcrfServiceList = new ArrayList<>();
		PcrfInfoDto pcrfInfo = new PcrfInfoDto(msisdn, imsi, pcrfServiceList);

		try {
			ResponseEntity<String> xml = restTemplateWithConnectTimeout.postForEntity(pcrfUrl, pcrfRequest, String.class);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBody().getBytes(StandardCharsets.UTF_8));
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(inputStream);

            logger.debug("pcrf response: {}", xml.getBody());
			PcrsServiceDto srv = null;

			while (reader.hasNext()) {
				XMLEvent nextEvent = reader.nextEvent();

				if (nextEvent.isStartElement()) {

					StartElement startElement = nextEvent.asStartElement();

					switch (startElement.getName().getLocalPart()) {
						case "subscribedService":
							nextEvent = reader.nextEvent();
							srv = new PcrsServiceDto();
							break;
						case "key":
							nextEvent = reader.nextEvent();
							if (nextEvent.asCharacters().getData().equals("SRVNAME")) {
                                logger.debug("srvname key= {}", nextEvent.asCharacters().getData());

								while (reader.hasNext()) {
									nextEvent = reader.nextEvent();
									if (nextEvent.isStartElement()) {
										startElement = nextEvent.asStartElement();

										if (startElement.getName().getLocalPart().equals("value")) {
											nextEvent = reader.nextEvent();
											if (srv != null) {
												srv.setCode(nextEvent.asCharacters().getData());
											}
                                            logger.debug("srvname val= {}", nextEvent.asCharacters().getData());
											break;
										}
									}
								}
							}
							if (nextEvent.asCharacters().getData().equals("SRVUSAGESTATE")) {
                                logger.debug("statekey= {}", nextEvent.asCharacters().getData());

								while (reader.hasNext()) {
									nextEvent = reader.nextEvent();
									if (nextEvent.isStartElement()) {
										startElement = nextEvent.asStartElement();

										if (startElement.getName().getLocalPart().equals("value")) {
											nextEvent = reader.nextEvent();
                                            logger.debug("stateval= {}", nextEvent.asCharacters().getData());
											if (srv != null) {
												srv.setValue(nextEvent.asCharacters().getData());
											}
											break;
										}
									}
								}
							}
							break;
						case "value":
							nextEvent = reader.nextEvent();
							break;
						default:
							break;
					}
				}
				if (nextEvent.isEndElement()) {
					EndElement endElement = nextEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("subscribedService")) {
						pcrfServiceList.add(srv);
					}
				}

			}
            logger.debug("pcrfInfo= {}", pcrfInfo.toString());

			pcrfAttrRepository.findAll().forEach(d -> {
				PcrsServiceDto s = new PcrsServiceDto(d.getCode(), d.getNameByLang(lang), "0", d.getSort());
				int i = pcrfInfo.getPcrfServices().indexOf(s);
				if (i >= 0) {
					s = pcrfInfo.getPcrfServices().get(i);
					s.setName(d.getNameByLang(lang));
					s.setSort(d.getSort());
				} else {
					pcrfInfo.getPcrfServices().add(s);
				}
			});
			pcrfInfo.setPcrfServices(pcrfInfo.getPcrfServices().stream()
					.sorted(Comparator.comparingInt(PcrsServiceDto::getSort)).toList());

		} catch (RestClientException | XMLStreamException e) {
			logger.error("PCRF result parse ERROR. {}", e.getMessage());
			throw new ExternalServiceException("PCRF result parse ERROR");
		}

		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(pcrfInfo);

		return serviceResponse;
	}

	public static String asString(Resource resource) {
		try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
			return FileCopyUtils.copyToString(reader);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
