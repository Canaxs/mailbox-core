package com.mailbox.config;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Meriç",
                        email = "mmericcana@gmail.com",
                        url = "github.com/Canaxs"
                ),
                description = "Mailbox API",
                title = "MailBox",
                version = "1.0",
                license = @License(
                        name = "MailBox License"
                ),
                termsOfService = "Terms Of Service"
        )
)
public class OpenApiConfig {
}
