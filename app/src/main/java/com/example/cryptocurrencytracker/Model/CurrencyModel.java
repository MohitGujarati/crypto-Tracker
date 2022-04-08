package com.example.cryptocurrencytracker.Model;

public class CurrencyModel {
    private String name;

    private String symbol;
    private String tags;
    private double price;

    // private double identity  ;

    public CurrencyModel(String name, String symbol, String tags,double price) {
        this.name = name;

        this.symbol = symbol;
        this.price = price;
        this.tags = tags;


    }



    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
