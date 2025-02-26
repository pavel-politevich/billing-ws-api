package by.com.lifetech.billingapi.exceptions;


import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultAdvice 
 {

	@ExceptionHandler(BusinessException.class)
    public ResponseEntity<ServiceResponseDto<String>> handleBusinessException(BusinessException ex) {
		ServiceResponseDto<String> serviceResponse = new ServiceResponseDto<>();
		serviceResponse.setResultCode(ServiceResultCode.BUSINESS_ERROR.name());
		serviceResponse.setBusinessError(ex.getError());
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

	 @ExceptionHandler(InternalException.class)
	 public ResponseEntity<ServiceResponseDto<String>> handleInternalException(InternalException ex) {
		 ServiceResponseDto<String> serviceResponse = new ServiceResponseDto<>();
		 serviceResponse.setResultDescription(ex.getMessage());
		 serviceResponse.setResultCode(ex.getResultCode().name());
		 return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	 }

	@ExceptionHandler({ValidationException.class, MissingRequestValueException.class
			, HttpMessageNotReadableException.class, BindException.class})
	public ResponseEntity<ServiceResponseDto<String>> handleValidationExceptions(Exception ex) {
		ServiceResponseDto<String> serviceResponse = new ServiceResponseDto<>();
		serviceResponse.setResultCode(ServiceResultCode.VALIDATION_ERROR.name());

		String message;
		if (ex instanceof BindException) {
			message = getBindExceptionMessage((BindException) ex);
		} else if (ex instanceof HttpMessageNotReadableException) {
			message = "Required request body is missing";
		} else {
			message = ex.getMessage();
		}

		serviceResponse.setResultDescription(message);
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}

	 @ExceptionHandler(IPayException.class)
	 public ResponseEntity<ServiceResponseDto<String>> handleIPayException(IPayException ex) {
		 ServiceResponseDto<String> serviceResponse = new ServiceResponseDto<>();
		 serviceResponse.setResultCode(ServiceResultCode.BUSINESS_ERROR.name());
		 serviceResponse.setBusinessError(new ServiceBusinessError(ex.getIPayErrorCode().getIPayErrorCode()
				 , ex.getMessage().isEmpty() ? ex.getIPayErrorCode().getMessage() : ex.getMessage()));
		 return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	 }

	 @ExceptionHandler(AccessDeniedException.class)
	 public ResponseEntity<BasicClientError> handleAccessDeniedException(AccessDeniedException ex) {
		 HttpStatus status = HttpStatus.FORBIDDEN;
		 return ResponseEntity.status(status)
				 .body(new BasicClientError(LocalDateTime.now(), status.value(), status.getReasonPhrase()));
	 }

	 @ExceptionHandler(NoResourceFoundException.class)
	 public ResponseEntity<BasicClientError> handleNoResourceFoundException(NoResourceFoundException ex) {
		 HttpStatus status = HttpStatus.NOT_FOUND;
		 return ResponseEntity.status(status)
				 .body(new BasicClientError(LocalDateTime.now(), status.value(), status.getReasonPhrase()));
	 }

	 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	 public ResponseEntity<BasicClientError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		 HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		 return ResponseEntity.status(status)
				 .body(new BasicClientError(LocalDateTime.now(), status.value(), status.getReasonPhrase()));
	 }

	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ServiceResponseDto<String>> handleOtherException(Exception ex) {
		 ServiceResponseDto<String> serviceResponse = new ServiceResponseDto<>();
		 serviceResponse.setResultDescription("Unexpected internal error has occurred");
		 serviceResponse.setResultCode(ServiceResultCode.INTERNAL_ERROR.name());
		 return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	 }

	private String getBindExceptionMessage(BindException ex) {
		Map<String, String> errorMap = new LinkedHashMap<>();
        if (ex.hasFieldErrors()) {
        	ex.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        }
        return errorMap.toString();
	}

}
