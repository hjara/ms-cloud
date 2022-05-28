package pe.code.migracion.seguridad.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	private String title = "Spring Boot REST API";
	private String description = "Spring Boot REST API - Seguridad";
	private String version = "1.0";

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(
				new Info().title(title)
				.description(description)
				.version(version)
				.license(
					new License().name("Apache License Version 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0")
				)
			)
			.externalDocs(
				new ExternalDocumentation()
				.description("Proyecto Migración")
				.url("https://www.google.com.pe/")
			);
	}
}
