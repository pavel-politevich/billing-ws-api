package by.com.lifetech.billingapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.enums.PofileIdType;
import by.com.lifetech.billingapi.wsdl.om.OrderManagement;
import by.com.lifetech.billingapi.wsdl.om.OrderManagementService;
import by.com.lifetech.billingapi.wsdl.om.ProductComponent;
import by.com.lifetech.billingapi.wsdl.om.SearchKey;
import by.com.lifetech.billingapi.wsdl.om.SimpleProductOffering;
import by.com.lifetech.billingapi.wsdl.om.SearchKey.Values;
import by.com.lifetech.billingapi.wsdl.om.SearchKey.Values.Entry;
import by.com.lifetech.billingapi.wsdl.om.ws.in.FulfillRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.ObjectFactory;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;
import jakarta.xml.ws.BindingProvider;

import org.slf4j.Logger;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Service
public class OmProfileService {

	@Value("${ws.om.wsdl}")
	private String wsOmUrl;

	@Value("${ws.om.username}")
	private String omUserName;

	@Value("${ws.om.password}")
	private String omPassword;

	Logger logger = LoggerFactory.getLogger(OmProfileService.class);

	public FulfillResult fulFillRequest(PofileIdType profileId, Map<String, String> searchKeys, String omCode, String channel)
			throws BusinessException {

		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(omUserName, omPassword.toCharArray());
			}
		};

		logger.info("START execute OM FulFill {}, Product: {} with params: {}", profileId, omCode, searchKeys);

		Authenticator.setDefault(myAuth);
		OrderManagement service;
		try {
			service = new OrderManagement(new URL(wsOmUrl));
		} catch (MalformedURLException e) {
			throw new BusinessException("OM Profiles service is temporarily unavailable");
		}

		OrderManagementService port = service.getOrderManagementPort();
		BindingProvider binding = (BindingProvider) port;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, omUserName);
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, omPassword);

		ObjectFactory factory = new ObjectFactory();
		FulfillRequest fulfillReq = factory.createFulfillRequest();

		ProductComponent prodComp = new ProductComponent();
		SimpleProductOffering simpleProd = new SimpleProductOffering();
		simpleProd.setId(omCode);

		prodComp.setProductOffering(simpleProd);

		fulfillReq.setChannel(channel);
		fulfillReq.setProfileId(profileId.name());
		fulfillReq.setProduct(prodComp);

		SearchKey key = new SearchKey();
		SearchKey.Values val = new Values();

		for (Map.Entry<String, String> e : searchKeys.entrySet()) {
			SearchKey.Values.Entry entry = new Entry();
			entry.setKey(e.getKey());
			entry.setValue(e.getValue());
			val.getEntry().add(entry);
		}

		key.setValues(val);
		fulfillReq.setSearchKey(key);
		FulfillResult fulFillResult = port.fulfill(fulfillReq);

		logger.info("STOP execute OM FulFill {} - resultCode: {}, resultDescription: {}, transactionId: {}", profileId,
				fulFillResult.getCommonResult().getResultCode(),
				fulFillResult.getCommonResult().getResultBusinessCode(), fulFillResult.getTransactionId());

		return fulFillResult;

	}

}
