package com.example.cryptocurrencytracker.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stock_table")
public class StockModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String stockname;
    private String stockDescription;
    private String stockDuration;
    private String stockBuy;
    private String stockSell;

    public StockModel() {
    }

    //constructor
    public StockModel(String stockname, String stockDescription, String stockDuration, String stockBuy, String stockSell) {
        this.stockname = stockname;
        this.stockDescription = stockDescription;
        this.stockDuration = stockDuration;
        this.stockBuy = stockBuy;
        this.stockSell = stockSell;
    }
    //getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getStockDescription() {
        return stockDescription;
    }

    public void setStockDescription(String stockDescription) {
        this.stockDescription = stockDescription;
    }

    public String getStockDuration() {
        return stockDuration;
    }

    public void setStockDuration(String stockDuration) {
        this.stockDuration = stockDuration;
    }

    public String getStockBuy() {
        return stockBuy;
    }

    public void setStockBuy(String stockBuy) {
        this.stockBuy = stockBuy;
    }

    public String getStockSell() {
        return stockSell;
    }

    public void setStockSell(String stockSell) {
        this.stockSell = stockSell;
    }
}
