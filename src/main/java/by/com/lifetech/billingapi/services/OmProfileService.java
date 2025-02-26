package by.com.lifetech.billingapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.models.enums.PofileIdType;
import by.com.lifetech.billingapi.models.enums.ProductState;
import by.com.lifetech.billingapi.wsdl.om.OrderManagement;
import by.com.lifetech.billingapi.wsdl.om.OrderManagementService;
import by.com.lifetech.billingapi.wsdl.om.ProductComponent;
import by.com.lifetech.billingapi.wsdl.om.SearchKey;
import by.com.lifetech.billingapi.wsdl.om.SimpleProductOffering;
import by.com.lifetech.billingapi.wsdl.om.SearchKey.Values;
import by.com.lifetech.billingapi.wsdl.om.SearchKey.Values.Entry;
import by.com.lifetech.billingapi.wsdl.om.ws.in.FulfillRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.GetProductsRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.ObjectFactory;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GetProductsResult;
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
			throws InternalException {

		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(omUserName, omPassword.toCharArray());
			}
		};

		Authenticator.setDefault(myAuth);
		OrderManagement service;
		try {
			service = new OrderManagement(new URL(wsOmUrl));
		} catch (MalformedURLException e) {
			throw new ExternalServiceException("OM Profiles service is temporarily unavailable");
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

		return fulFillResult;
	}
	
	public GetProductsResult getProductsRequest(PofileIdType profileId, Map<String, String> searchKeys, String omCode,
			String channel) throws InternalException {

		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(omUserName, omPassword.toCharArray());
			}
		};

		Authenticator.setDefault(myAuth);
		OrderManagement service;
		try {
			service = new OrderManagement(new URL(wsOmUrl));
		} catch (MalformedURLException e) {
			throw new ExternalServiceException("OM Profiles service is temporarily unavailable");
		}

		OrderManagementService port = service.getOrderManagementPort();
		BindingProvider binding = (BindingProvider) port;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, omUserName);
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, omPassword);

		ObjectFactory factory = new ObjectFactory();
		GetProductsRequest getProdReq = factory.createGetProductsRequest();

		getProdReq.setChannel(channel);
		getProdReq.setProfileId(profileId.name());
		if (omCode != null) {
			getProdReq.setProductOfferingId(omCode);
		}

		SearchKey key = new SearchKey();
		SearchKey.Values val = new Values();

		for (Map.Entry<String, String> e : searchKeys.entrySet()) {
			SearchKey.Values.Entry entry = new Entry();
			entry.setKey(e.getKey());
			entry.setValue(e.getValue());
			val.getEntry().add(entry);
		}

		key.setValues(val);
		getProdReq.setSearchKey(key);
		GetProductsResult getProductsResult = port.getProducts(getProdReq);

		return getProductsResult;

	}
	
	public ProductState getProductState (String productCode, String msisdn, String channel) throws InternalException {
		Map<String, String> map = new HashMap<>();
		map.put("MSISDN", msisdn);
		GetProductsResult profileResult = getProductsRequest(PofileIdType.GET_PRODUCT, map, productCode, channel);
		if (profileResult.getProductList() == null) {
			return ProductState.DEACTIVE;
		}
		@SuppressWarnings("unchecked")
		List<ProductComponent> productlist = (List<ProductComponent>) (Object) profileResult.getProductList();
		
		return ProductState.valueOf(productlist.get(0).getProductStatus());
	}

}
