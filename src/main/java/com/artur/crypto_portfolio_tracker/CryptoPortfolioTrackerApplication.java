package com.artur.crypto_portfolio_tracker;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.config.WebClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@SpringBootApplication
public class CryptoPortfolioTrackerApplication {


	public static void main(String[] args) {
		SpringApplication.run(CryptoPortfolioTrackerApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(CryptoApiClient cryptoApiClient){
		return runner -> {
			cryptoApiClient.getCryptoPrice("SOL");
		};
	}


}
