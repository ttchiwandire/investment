package za.co.momentum.investment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Momentum Investment API", version = "1.0", description = "Investment Portifolios Serivce"))
public class InvestmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentApplication.class, args);
	}

}
