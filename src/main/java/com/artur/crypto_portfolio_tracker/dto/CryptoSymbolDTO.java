package com.artur.crypto_portfolio_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CryptoSymbolDTO {

    private String symbol;

    private BigDecimal last;

    @JsonProperty("last_btc")
    private BigDecimal lastBtc;

    private BigDecimal lowest;

    private BigDecimal highest;

    private String date;

    @JsonProperty("daily_change_percentage")
    private BigDecimal dailyChangePercentage;

    @JsonProperty("source_exchange")
    private String sourceExchange;

    public CryptoSymbolDTO() {}

    public CryptoSymbolDTO(String symbol, BigDecimal last, BigDecimal lastBtc, BigDecimal lowest, BigDecimal highest, String date, BigDecimal dailyChangePercentage, String sourceExchange) {
        this.symbol = symbol;
        this.last = last;
        this.lastBtc = lastBtc;
        this.lowest = lowest;
        this.highest = highest;
        this.date = date;
        this.dailyChangePercentage = dailyChangePercentage;
        this.sourceExchange = sourceExchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getLastBtc() {
        return lastBtc;
    }

    public void setLastBtc(BigDecimal lastBtc) {
        this.lastBtc = lastBtc;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getDailyChangePercentage() {
        return dailyChangePercentage;
    }

    public void setDailyChangePercentage(BigDecimal dailyChangePercentage) {
        this.dailyChangePercentage = dailyChangePercentage;
    }

    public String getSourceExchange() {
        return sourceExchange;
    }

    public void setSourceExchange(String sourceExchange) {
        this.sourceExchange = sourceExchange;
    }

    @Override
    public String toString() {
        return "CryptoSymbolDTO{" +
                "symbol='" + symbol + '\'' +
                ", last=" + last +
                ", lastBtc=" + lastBtc +
                ", lowest=" + lowest +
                ", highest=" + highest +
                ", date='" + date + '\'' +
                ", dailyChangePercentage=" + dailyChangePercentage +
                ", sourceExchange='" + sourceExchange + '\'' +
                '}';
    }
}
