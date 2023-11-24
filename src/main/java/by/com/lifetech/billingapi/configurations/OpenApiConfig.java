package by.com.lifetech.billingapi.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Life:) billing One Api",
                description = "One API for third-party apps", version = "1.0.0",
                contact = @Contact(
                        name = "SSDEV Team",
                        email = "LT-SOC-PC-SSDEV@life.com.by",
                        url = "https://lifetech.by"
                )
        )
)
public class OpenApiConfig {

}
