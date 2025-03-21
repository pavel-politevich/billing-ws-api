package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.utils.Uuid33CustomGenerator;
import by.com.lifetech.billingapi.wsdl.message.*;
import jakarta.validation.ValidationException;
import jakarta.xml.ws.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SmsMessageService {
	@Value("${sms.message.url}")
	private String url;

	@Value("${sms.message.username}")
	private String userName;

	@Value("${sms.message.password}")
	private String password;

	private final String DEFAULT_EXTERNAL_ID = "0000000";
	private final String DEFAULT_CHANNEL = "MES";
	private final String DEFAULT_CAMPAIGN_ID = "1";

	Logger logger = LoggerFactory.getLogger(SmsMessageService.class);

	private MessageTransmissionAPI getPort() throws ExternalServiceException {
		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password.toCharArray());
			}
		};
		Authenticator.setDefault(myAuth);

		MessageTransmissionAPI_Service service = null;
		try {
			service = new MessageTransmissionAPI_Service(new URL(url));
		} catch (MalformedURLException e) {
			throw new ExternalServiceException("Sms message service is temporarily unavailable");
		}

		MessageTransmissionAPI port = service.getMessageTransmissionAPIPort();
		BindingProvider binding = (BindingProvider) port;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

		return port;
	}

	public GeneralResult sendSms(Message message) throws ExternalServiceException {
		Optional.ofNullable(message).orElseThrow(() -> new ValidationException("Sms message is null"));
		Optional.ofNullable(message.getDest()).orElseThrow(() -> new ValidationException("Sms destination is null"));
		Optional.ofNullable(message.getIdrCode()).orElseThrow(() -> new ValidationException("Sms code is null"));
		logger.info("START send sms to processing queue - code: {}, dest: {}", message.getIdrCode(), message.getDest());

		setDefaultMessageValues(message);

		GeneralResult result = checkResult(getPort().transmitMessage(message));
		logger.info("STOP send sms to processing queue - transactionId: {}, description: {}"
				, result.getTransactionId(), result.getResultDescription());
		return result;
	}

	private void setDefaultMessageValues(Message message) {
		message.setExternalId(Optional.ofNullable(message.getExternalId()).orElse(Uuid33CustomGenerator.uuid33()));

        Identification identification = Optional.ofNullable(message.getIdentification()).orElse(new Identification());
		identification.setChannel(Optional.ofNullable(identification.getChannel()).orElse(DEFAULT_CHANNEL));
		identification.setCampaignId(Optional.ofNullable(identification.getCampaignId()).orElse(DEFAULT_CAMPAIGN_ID));
		message.setIdentification(identification);
	}

	public GeneralResult sendSms(String smsCode, String msisdn, List<Param> params) throws ExternalServiceException {
		Message message = new Message();
		message.setDest(msisdn);
		message.setIdrCode(smsCode);

		if (params != null) {
			message.getParams().addAll(params);
		}

		return sendSms(message);
	}

	public GeneralResult sendSmsWithMapParams(String smsCode, String msisdn, Map<String, String> paramsMap) throws ExternalServiceException {
		return sendSms(smsCode, msisdn, fillParamsFromMap(paramsMap));
	}

	private List<Param> fillParamsFromMap(Map<String, String> paramsMap) {
		if (paramsMap == null) {
			return null;
		}

		return paramsMap.entrySet().stream()
				.map(entry -> new Param(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	public GetAllMessagesResult getAllMessages() throws ExternalServiceException {
		return (GetAllMessagesResult) checkResult(getPort().getAllMessages());
	}

	private GeneralResult checkResult(GeneralResult result) throws ExternalServiceException {
		if(!result.getResultCode().equals(ResultCodeType.SUCCESS)) {
			String message = String.format("Sms transactionId: %s return error: %s, description: %s"
					, Optional.ofNullable(result.getTransactionId()).orElse("")
					, Optional.ofNullable(result.getError()).orElse("")
					, Optional.ofNullable(result.getResultDescription()).orElse(""));
			throw new ExternalServiceException(message);
		}

		return result;
	}
}
