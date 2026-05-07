package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.dto.CryptoApiResponse;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.dto.PortfolioSummaryDTO;
import com.artur.crypto_portfolio_tracker.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PortfolioRestController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioRestController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{userId}/portfolio")
    public List<PortfolioItemDTO> giveMyPortfolio(@PathVariable int userId){
        return portfolioService.getPortfolio(userId);
    }


    @GetMapping("/{userId}/summary")
    public PortfolioSummaryDTO giveStatistic(@PathVariable int userId){
        return portfolioService.getPortfolioOverview(userId);
    }

}
