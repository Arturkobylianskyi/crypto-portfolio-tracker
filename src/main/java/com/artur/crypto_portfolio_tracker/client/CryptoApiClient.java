package com.artur.crypto_portfolio_tracker.client;

import com.artur.crypto_portfolio_tracker.config.WebClientConfig;
import com.artur.crypto_portfolio_tracker.dto.CryptoApiResponse;
import com.artur.crypto_portfolio_tracker.dto.CryptoSymbolDTO;
import com.artur.crypto_portfolio_tracker.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CryptoApiClient {

    private final WebClient webClient;

    private static final String apiURL = "https://api.freecryptoapi.com/v1/getData";
    private static final Logger log = LoggerFactory.getLogger(CryptoApiClient.class);

    @Value("${crypto.api.key}")
    private String apiKey;

    @Autowired
    public CryptoApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Map<String, BigDecimal> getCryptoPrice(List<String> symbols){
        if(symbols==null || symbols.isEmpty()){
            return new HashMap<>();
        }

        // join currency for batch request
        String joinedSymbols = String.join("+", symbols);


        CryptoApiResponse response = webClient.get()
                .uri(apiURL+"?symbol="+joinedSymbols )
                .header("Authorization", "Bearer "+apiKey)
                .retrieve()
                .bodyToMono(CryptoApiResponse.class)
                .block();

        // convenient way to find all prices
        Map<String, BigDecimal> pricesMap = new HashMap<>();

        if (response != null && "success".equals(response.getStatus())) {
            for(CryptoSymbolDTO symbolDTO: response.getSymbols()){
                pricesMap.put(symbolDTO.getSymbol(), symbolDTO.getLast());
            }
        }
        else {
            return new HashMap<>(); // silent error
        }

        return pricesMap;
    }

    public BigDecimal getBitcoinPrice(){
        CryptoApiResponse response = webClient.get()
                .uri(apiURL+"?symbol=BTC")
                .header("Authorization", "Bearer "+apiKey)
                .retrieve()
                .bodyToMono(CryptoApiResponse.class)
                .block();

        BigDecimal price = response.getSymbols().get(0).getLast();

        return price;
    }
}
