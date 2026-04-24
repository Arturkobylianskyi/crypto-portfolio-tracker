package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        List<PortfolioItemDTO> items = new ArrayList<>();

        for(Asset asset: assets){
            // get current price for each crypto coin
            String symbol = asset.getSymbol();
            BigDecimal currentPrice = cryptoApiClient.getCryptoPrice(symbol);

            // create new PortflolioItem and write there coin with current price
            PortfolioItemDTO portfolioItemDTO = new PortfolioItemDTO();
//
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
}
