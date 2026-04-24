package com.artur.crypto_portfolio_tracker.client;

import com.artur.crypto_portfolio_tracker.config.WebClientConfig;
import com.artur.crypto_portfolio_tracker.dto.CryptoApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class CryptoApiClient {

    private final WebClient webClient;

    private static final String apiURL = "https://api.freecryptoapi.com/v1/getData";

    @Value("${crypto.api.key}")
    private String apiKey;

    @Autowired
    public CryptoApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public BigDecimal getCryptoPrice(String symbol){
        CryptoApiResponse response = webClient.get()
                .uri(apiURL+"?symbol={symbol}", symbol)
                .header("Authorization", "Bearer "+apiKey)
                .retrieve()
                .bodyToMono(CryptoApiResponse.class)
                .block();

        if (response == null || !"success".equals(response.getStatus())) {
//            System.out.println("this case 1");
            return BigDecimal.ZERO;
        }

        if (response.getSymbols() == null || response.getSymbols().isEmpty()) {
//            System.out.println("this case 2");

            return BigDecimal.ZERO;
        }

        BigDecimal price = response.getSymbols().get(0).getLast();

        return price;
    }

    public void getBitcoinPrice(){
        CryptoApiResponse response = webClient.get()
                .uri(apiURL)
                .header("Authorization", "Bearer "+apiKey)
                .retrieve()
                .bodyToMono(CryptoApiResponse.class)
                .block();

        BigDecimal price = response.getSymbols().get(0).getLast();
        System.out.println(price);
    }
}
