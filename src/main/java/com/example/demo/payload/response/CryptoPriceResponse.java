package com.example.demo.payload.response;

public class CryptoPriceResponse {
    
    private String symbol;

    private Double price;

    public CryptoPriceResponse() {
    }

    public CryptoPriceResponse(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    

}
