package com.artur.crypto_portfolio_tracker.dto;

import com.artur.crypto_portfolio_tracker.entity.User;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class AssetDTO {

    private int id;

    private String symbol;

    private BigDecimal quantity;

    private BigDecimal purchasePrice;

    public AssetDTO() {
    }

    public AssetDTO(int id, String symbol, BigDecimal quantity, BigDecimal purchasePrice) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AssetDTO{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
