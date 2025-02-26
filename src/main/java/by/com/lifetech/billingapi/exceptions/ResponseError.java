package by.com.lifetech.billingapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseError {
	private String message;

    public ResponseError() {
    }

    public ResponseError(String message) {
        this.message = message;
    }

}
