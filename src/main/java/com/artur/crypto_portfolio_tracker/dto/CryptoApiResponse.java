package com.artur.crypto_portfolio_tracker.dto;

import java.util.List;

public class CryptoApiResponse {

    private String status;

    private List<CryptoSymbolDTO> symbols;

    public CryptoApiResponse(){}

    public CryptoApiResponse(String status, List<CryptoSymbolDTO> symbols) {
        this.status = status;
        this.symbols = symbols;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CryptoSymbolDTO> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<CryptoSymbolDTO> symbols) {
        this.symbols = symbols;
    }
}
