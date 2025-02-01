package com.ak.banking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Rest API Documentation",
				description = "Documentation of Banking- App",
				version = "V1.0",
				contact = @Contact(
						name = "Amol Annasaheb Khot",
						email = "amolkhot70@outlook.com"
				)
		)
)


@SpringBootApplication(scanBasePackages = "com.ak.banking")
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
