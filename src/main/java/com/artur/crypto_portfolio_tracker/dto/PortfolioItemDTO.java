package com.artur.crypto_portfolio_tracker.dto;

import java.math.BigDecimal;

public class PortfolioItemDTO {

    private String symbol;

    private BigDecimal quantity;

    private BigDecimal purchasePrice;

    private BigDecimal currentPrice;

    private BigDecimal totalValue;

    private BigDecimal profitOrLoss;

    public PortfolioItemDTO(){}

    public PortfolioItemDTO(String symbol, BigDecimal quantity, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal totalValue, BigDecimal profitOrLoss) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.totalValue = totalValue;
        this.profitOrLoss = profitOrLoss;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    @Override
    public String toString() {
        return "PortfolioItemDTO{" +
                "symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", currentPrice=" + currentPrice +
                ", totalValue=" + totalValue +
                ", profitOrLoss=" + profitOrLoss +
                '}';
    }
}
