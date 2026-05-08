package com.artur.crypto_portfolio_tracker.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="symbol")
    private String symbol;


    @Column(name="quantity")
    private BigDecimal quantity;

    @Column(name="purchase_price")
    private BigDecimal purchasePrice;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Asset(){}

    public Asset(User user, BigDecimal purchasePrice, BigDecimal quantity, String symbol) {
        this.user = user;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.symbol = symbol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", user=" + user +
                '}';
    }
}
