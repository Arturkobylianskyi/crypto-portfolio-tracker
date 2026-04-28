package com.artur.crypto_portfolio_tracker;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.config.WebClientConfig;
import com.artur.crypto_portfolio_tracker.dao.AssetRepository;
import com.artur.crypto_portfolio_tracker.dao.UserRepository;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@SpringBootApplication
public class CryptoPortfolioTrackerApplication {


	public static void main(String[] args) {
		SpringApplication.run(CryptoPortfolioTrackerApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(UserRepository userRepository,
									  AssetRepository assetRepository,
									  PasswordEncoder passwordEncoder) {
		return args -> {
			// check if user exist
			if (userRepository.findUserByUserName("user") == null) {

				// create user
				User testUser = new User();
				testUser.setUserName("user");
				testUser.setPassword(passwordEncoder.encode("test"));

				// save user
				userRepository.save(testUser);

				// 2. create conins

				Asset btc = new Asset();
				btc.setSymbol("BTC"); // Symbol for api
				btc.setQuantity(new BigDecimal("0.5"));
				btc.setPurchasePrice(new BigDecimal("50000.00"));
				btc.setUser(testUser);
				assetRepository.save(btc);


				Asset eth = new Asset();
				eth.setSymbol("ETH");
				eth.setQuantity(new BigDecimal("2.5"));
				eth.setPurchasePrice(new BigDecimal("2000.00"));
				eth.setUser(testUser);
				assetRepository.save(eth);


				Asset sol = new Asset();
				sol.setSymbol("SOL");
				sol.setQuantity(new BigDecimal("15.0"));
				sol.setPurchasePrice(new BigDecimal("20.00"));
				sol.setUser(testUser);
				assetRepository.save(sol);

				System.out.println("The database has been successfully populated! User 'artur' and 3 coins (BTC, ETH, SOL) have been created.");
			} else {
				System.out.println("The test data already exists, we skip filling it in.");
			}
		};
	}


}
