package by.com.lifetech.billingapi.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
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
import by.com.lifetech.billingapi.models.dto.HssAttributeDto;
import by.com.lifetech.billingapi.models.dto.HssGroupDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.dictionary.HssAttrGroupRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.HssAttrRepository;

@Service
@RequiredArgsConstructor
public class HssService {
    Logger logger = LoggerFactory.getLogger(HssService.class);

    private final RestTemplate restTemplateWithConnectTimeout;
    private final HssAttrRepository hssAttrRepository;
    private final HssAttrGroupRepository hssAttrGroupRepository;
    private final CBossService cBossService;
    private final ExceptionUtils exceptionUtils;

    @Value("${hss.url}")
    private String hssUrl;
    @Value("${hss.username}")
    private String hssUsername;
    @Value("${hss.password}")
    private String hssPass;

    @Value("classpath:/static/hssTemplate.xml")
    Resource resourceFile;

    public ServiceResponseDto<List<HssGroupDto>> getHssData(String msisdn, Lang lang) throws InternalException, BusinessException {
        ServiceResponseDto<List<HssGroupDto>> serviceResponse = new ServiceResponseDto<>();

        if (cBossService.getInformation(msisdn).getSubscriber() == null) {
            throw exceptionUtils.getMsisdnNotFoundException();
        }

        String hssRequest = asString(resourceFile)
                .replace("MSISDN", msisdn)
                .replace("${username}",hssUsername)
                .replace("${password}",hssPass);

        List<HssGroupDto> hssGroupList = new ArrayList<>();
        String searchGroupName = null;

        try {
            ResponseEntity<String> xml = restTemplateWithConnectTimeout.postForEntity(hssUrl, hssRequest, String.class);
            String xmlBody = xml.getBody();
            if (xmlBody == null) {
                throw new ExternalServiceException("HSS not responding");
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBody().getBytes(StandardCharsets.UTF_8));
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(inputStream);

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {

                    StartElement startElement = nextEvent.asStartElement();
                    String attrName = startElement.getName().getLocalPart();
                    
                    Attribute url = startElement.getAttributeByName(new QName("Name"));
					if (url != null) {
	                    searchGroupName = url.getValue().replace("\"", "");
	                }
					
					//Non-typical parameters
					boolean nonTypicalParam = Boolean.FALSE;
					Pattern pattern = Pattern.compile("[TBS]{2}");
					Matcher matcher = pattern.matcher(attrName);
					if (searchGroupName != null && searchGroupName.equals("Basic Service") && matcher.matches()) {
						nonTypicalParam = Boolean.TRUE;
					}

                    if (hssAttrRepository.existsByCode(attrName) || nonTypicalParam) {
                        nextEvent = reader.nextEvent();
                        HssAttributeDto attr = new HssAttributeDto();
                        String attrValue = nextEvent.asCharacters().getData();
                        
                        pattern = Pattern.compile("[TBS]{2}\\d{2}");
    					matcher = pattern.matcher(attrValue);
                        if (nonTypicalParam && matcher.find()) {
                        	attrName = attrValue.substring(matcher.start(), matcher.end());
                        	attrValue = "TRUE";
                        }

                        attr.setCode(attrName);
                        attr.setValue(attrValue);

                        hssAttrRepository.findByCodeAndSearchGroupCode(attrName, searchGroupName).ifPresent(dict -> {
                            attr.setName(dict.getNameByLang(lang));
                            attr.setSort(dict.getSort());

                            HssGroupDto gr = new HssGroupDto();
                            gr.setAttrList(new ArrayList<>());
                            gr.setCode(dict.getGroupCode());

                            hssAttrGroupRepository.findById(dict.getGroupCode()).ifPresent(obj -> {
                                gr.setSort(obj.getSort());
                                gr.setName(obj.getNameByLang(lang));

                                if (!hssGroupList.contains(gr)) {
                                    logger.debug("Hss group not in list. Add: {}", gr);
                                    hssGroupList.add(gr);
                                }
                            });

                            hssGroupList.stream().filter(grp -> grp.getCode().equals(dict.getGroupCode())).findFirst()
                                    .ifPresent(lst -> {
                                        if (!lst.getAttrList().contains(attr)) {
                                            lst.getAttrList().add(attr);
                                        }
                                    });
                        });
                    }
                }
            }

        } catch (RestClientException | XMLStreamException e) {
            logger.error("HSS result parse ERROR. {}", e.getMessage());
            throw new ExternalServiceException("HSS result parse ERROR");
        }

        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(hssGroupList);

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

