package by.com.lifetech.billingapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicClientError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
}
