package com.artur.crypto_portfolio_tracker.entity;


import jakarta.persistence.*;

@Entity
@Table(name="asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="name")
    private String name;

    @Column(name="quantity")
    private int quantity;

    @Column(name="purchasePrice")
    private double purchasePrice;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Asset(){}

    public Asset(User user, double purchasePrice, int quantity, String name, String symbol) {
        this.user = user;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.name = name;
        this.symbol = symbol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", user=" + user +
                '}';
    }
}
