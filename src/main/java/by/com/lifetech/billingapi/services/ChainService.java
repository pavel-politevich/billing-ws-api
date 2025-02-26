package by.com.lifetech.billingapi.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import by.com.lifetech.billingapi.wsdl.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.enums.ChainType;
import jakarta.xml.ws.BindingProvider;

@Service
@RequiredArgsConstructor
public class ChainService {
    private final ExceptionUtils exceptionUtils;

    @Value("${chain.om.wsdl}")
    private String chainOmUrl;

    @Value("${chain.cim.wsdl}")
    private String chainCimUrl;

    @Value("${chain.fm.wsdl}")
    private String chainFmUrl;

    @Value("${chain.username}")
    private String chainUserName;

    @Value("${chain.password}")
    private String chainPassword;

    Logger logger = LoggerFactory.getLogger(ChainService.class);

    public ChainResult executeChain(ChainType chainType, String chainName, Map<String, Object> inputParams) throws BusinessException, InternalException {
        List<InputParameter> inputParameters = new ArrayList<InputParameter>();
        inputParams.forEach((k, v) -> inputParameters.add(new InputParameter(k, v)));

        logger.info("START execute chain {} name {} with params: {}", chainType, chainName, inputParams);

        String url = switch (chainType) {
            case OM -> chainOmUrl;
            case CIM -> chainCimUrl;
            case FM -> chainFmUrl;
            default -> chainOmUrl;
        };

        ChainAPI_Service service;
        try {
            service = new ChainAPI_Service(new URL(url));
        } catch (MalformedURLException e) {
            throw new ExternalServiceException("chain api service is temporarily unavailable");
        }

        ChainAPI port = service.getChainAPIPort();
        BindingProvider binding = (BindingProvider) port;

        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, chainUserName);
        binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, chainPassword);

        ChainRequest chainRequest = new ChainRequest();
        chainRequest.setChainName(chainName);
        chainRequest.getInputParameters().addAll(inputParameters);

        ChainResult chainResult;
        try {
            chainResult = port.executeChain(chainRequest);
            String resultCode = chainResult.getResultCode().value();
            String transactionId = chainResult.getTransactionId().toString();
            List<ChainResultElement> resList = chainResult.getResultList();
            logger.info("STOP execute chain {} - resultCode: {}, transactionId: {}, resultList: {}", chainName, resultCode, transactionId, resList);
        } catch (Exception e) {
            throw new ExternalServiceException("chain api service error");
        }

        if (chainResult.getResultCode().equals(ResultCodeType.CHAIN_BUSINESS_ERROR)) {
            BusinessException e = exceptionUtils.getBusinessErrorByChainResult(chainResult);
            logger.error("Chain {} - {} : '{}'", chainName, e.getError().getErrorName(), e.getError().getErrorMessage());
            throw e;
        }

        if (!chainResult.getResultCode().equals(ResultCodeType.SUCCESS)) {
            ChainException e = exceptionUtils.getChainErrorByChainResult(chainResult);
            String chainInfo = Optional.ofNullable(e.getChainInfo()).orElse("").isEmpty() ? ""
                    : "; " + e.getChainInfo();
            logger.error("Chain {} - {}{}", chainName, e.getMessage(), chainInfo);
            throw e;
        }

        return chainResult;
    }
}
