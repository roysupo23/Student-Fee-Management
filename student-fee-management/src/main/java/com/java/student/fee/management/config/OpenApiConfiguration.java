package com.java.student.fee.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("Student fee collection")
				.description("This application is used to onboard student and collect their fees").version("1.0")
				.contact(apiContact()).license(apiLicence());
	}

	private License apiLicence() {
		return new License().name("MIT Licence").url("https://opensource.org/licenses/mit-license.php");
	}

	private Contact apiContact() {
		return new Contact().name("developer").email("developer@company.com");
	}
}
