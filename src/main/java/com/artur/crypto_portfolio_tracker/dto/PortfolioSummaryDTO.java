package com.artur.crypto_portfolio_tracker.dto;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioSummaryDTO {
    private BigDecimal totalPortfolioValue;

    private BigDecimal totalProfitOrLoss;

    private List<PortfolioItemDTO> assets;

    public PortfolioSummaryDTO(){}

    public PortfolioSummaryDTO(BigDecimal totalPortfolioValue, BigDecimal totalProfitOrLoss, List<PortfolioItemDTO> assets) {
        this.totalPortfolioValue = totalPortfolioValue;
        this.totalProfitOrLoss = totalProfitOrLoss;
        this.assets = assets;
    }

    public BigDecimal getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(BigDecimal totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public BigDecimal getTotalProfitOrLoss() {
        return totalProfitOrLoss;
    }

    public void setTotalProfitOrLoss(BigDecimal totalProfitOrLoss) {
        this.totalProfitOrLoss = totalProfitOrLoss;
    }

    public List<PortfolioItemDTO> getAssets() {
        return assets;
    }

    public void setAssets(List<PortfolioItemDTO> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "PortfolioSummaryDTO{" +
                "totalPortfolioValue=" + totalPortfolioValue +
                ", totalProfitOrLoss=" + totalProfitOrLoss +
                ", assets=" + assets +
                '}';
    }
}
