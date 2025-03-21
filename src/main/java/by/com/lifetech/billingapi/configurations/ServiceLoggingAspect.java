package by.com.lifetech.billingapi.configurations;

import by.com.lifetech.billingapi.configurations.annotations.NotLoggingResponse;
import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.IPayException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.enums.IPayErrorCode;
import by.life.crmadvancedsearch.model.SearchCriterion;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class ServiceLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Pointcut("execution(* by.com.lifetech.billingapi.controllers.v1.*.*(..)) && !@annotation(by.com.lifetech.billingapi.configurations.annotations.NotLogging)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logServiceMethodEntry(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        StringBuilder logMessage = new StringBuilder("START calling ");
        logMessage.append(methodName).append(" with arguments: ");

        for (int i = 0; i < args.length; i++) {
            logMessage.append(parameterNames[i]).append("=").append(parseArgument(args[i]));
            if (i < args.length - 1) {
                logMessage.append(", ");
            }
        }

        logMessage.append(";");
        logger.info(logMessage.toString());
    }

    private Object parseArgument(Object arg) {
        if (isListOfType(arg, SearchCriterion.class)) {
            return ((List<SearchCriterion>) arg).stream()
                    .map(s -> String.valueOf(s.getKey() + " " + s.getOperation() + " " + s.getValues())).toList().toString();
        }

        return arg;
    }

    private boolean isListOfType(Object object, Class<?> elementType) {
        if (object instanceof List<?> list) {
            if (!list.isEmpty()) {
                Object firstElement = list.get(0);
                return elementType.isInstance(firstElement);
            }
        }
        return false;
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logServiceMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if (method.isAnnotationPresent(NotLoggingResponse.class)) {
            logger.info("STOP calling {};", methodName);
        } else {
            logger.info("STOP calling {} with result: {}", methodName, parseResult(result));
        }
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("STOP calling {} with error: {}", methodName, getErrorText(ex));
    }

    private String getErrorText(Exception ex) {
        if (ex instanceof BusinessException be) {
            return String.format("%s - %s", be.getError().getErrorName(), be.getError().getErrorMessage());
        } else if (ex instanceof ChainException ce) {
            return String.format("%s - %s, %s", ce.getResultCode(), ce.getMessage(), ce.getChainInfo());
        } else if (ex instanceof InternalException ie) {
            return  String.format("%s - %s", ie.getResultCode(), ie.getMessage());
        } else if (ex instanceof IPayException ipe) {
            IPayErrorCode iPayErrorCode = ipe.getIPayErrorCode();
            return String.format("%s - %s, iPay code: %s", iPayErrorCode.getBusinessErrorCode(), iPayErrorCode.getMessage(), iPayErrorCode.getIPayErrorCode());
        } else {
            return ex.toString();
        }
    }

    private String parseResult(Object result) {
        if (result == null) {
            return "";
        }

        if (!(result instanceof ResponseEntity)) {
            return result.toString();
        }

        Object responseBody = ((ResponseEntity<?>) result).getBody();
        return responseBody == null ? result.toString() : responseBody.toString();
    }
}