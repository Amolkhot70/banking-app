package com.ak;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Expense-tracker-app API Documentation",
				description = "Documentation of Expense-tracker-app",
				version = "V1.0",
				contact = @Contact(
						name = "Amol Annasaheb Khot",
						email = "amolkhot70@outlook.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https:www.amolkhot/liscens"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Expense tracker spring boot documentation for developers and testers",
				url = "https://www.amolkhot.com/external-docs.html"
		)
)
@SpringBootApplication
public class ExpenseTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerAppApplication.class, args);
	}

}
