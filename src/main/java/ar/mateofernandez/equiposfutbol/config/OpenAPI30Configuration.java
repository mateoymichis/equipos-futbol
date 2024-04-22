package ar.mateofernandez.equiposfutbol.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Equipos API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Mateo Fernandez", email = "mateo.rost@gmail.com", url = "https://mateofernandez.ar"
                ),
                description = "API para gestionar información de equipos de fútbol"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPI30Configuration {
}
