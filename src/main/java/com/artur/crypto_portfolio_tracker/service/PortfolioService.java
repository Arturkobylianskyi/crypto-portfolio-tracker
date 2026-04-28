package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.dto.PortfolioSummaryDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PortfolioService {

    private AssetService assetService;
    private CryptoApiClient cryptoApiClient;

    @Autowired
    public PortfolioService(AssetService assetService, CryptoApiClient cryptoApiClient) {
        this.assetService = assetService;
        this.cryptoApiClient = cryptoApiClient;
    }

    public List<PortfolioItemDTO> getPortfolio(int userId){
        List<Asset> assets = assetService.findAllByUserId(userId);
        if(assets.isEmpty()){
            return new ArrayList<>();
        }

        // get coins symbols name from user assets
        List<String> coinSymbols = new ArrayList<>();
        for(Asset asset: assets){
            coinSymbols.add(asset.getSymbol());
        }

        // Make only one request to api
        // TODO: debug request apiClient
        Map<String, BigDecimal> currentPrices = cryptoApiClient.getCryptoPrice(coinSymbols);
        System.out.println(currentPrices);

        List<PortfolioItemDTO> items = new ArrayList<>();

        for(Asset asset: assets){
            // get current price for each crypto coin from Dictionary
            String symbol = asset.getSymbol();
            BigDecimal currentPrice = currentPrices.getOrDefault(symbol, BigDecimal.ZERO);

            // create new PortflolioItem and write there coin with current price
            PortfolioItemDTO portfolioItemDTO = new PortfolioItemDTO();

            portfolioItemDTO.setSymbol(symbol);
            portfolioItemDTO.setQuantity(asset.getQuantity());
            portfolioItemDTO.setPurchasePrice(asset.getPurchasePrice());
            portfolioItemDTO.setCurrentPrice(currentPrice);

            BigDecimal totalValue = asset.getQuantity().multiply(currentPrice);
            portfolioItemDTO.setTotalValue(totalValue);

            BigDecimal totalPurchasePrice = asset.getPurchasePrice().multiply(asset.getQuantity());
            portfolioItemDTO.setProfitOrLoss(totalValue.subtract(totalPurchasePrice));

            items.add(portfolioItemDTO);
        }

        return items;

    }

    public PortfolioSummaryDTO getPortfolioOverview(int userId){
        List<Asset> assets = assetService.findAllByUserId(userId);
        List<PortfolioItemDTO> items = getPortfolio(userId);

        BigDecimal totalValue = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;

        for(PortfolioItemDTO item : items){
            totalValue = totalValue.add(item.getTotalValue());
            totalProfit = totalProfit.add(item.getProfitOrLoss());
        }

        return new PortfolioSummaryDTO(
                totalValue,
                totalProfit,
                items
        );
    }
}
