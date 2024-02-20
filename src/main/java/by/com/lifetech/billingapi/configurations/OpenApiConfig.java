package by.com.lifetech.billingapi.configurations;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Life:) billing One Api",
                description = "One API for third-party apps", version = "1.0.0",
                contact = @Contact(
                        name = "SSDEV Team",
                        email = "LT-SOC-PC-SSDEV@life.com.by",
                        url = "https://lifetech.by"
                )
        ),security = { @SecurityRequirement(name = "basic-api") }
)

@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basic-api", scheme = "basic", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}
