package com.stockTrading.stockTradingSystem.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Stock_price")
public class StockPrice {

    @Id
    @SequenceGenerator(
            name = "stock_price_sequence",
            sequenceName = "stock_price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_price_sequence"
    )
    @Column(name = "sp_id")
    private long spId;
    //private long stockId;
    @Column(precision=5, scale = 5)
    private double price;
    private long updatedTime;
    private double todayHigh;
    private double todayLow;
    private double preClose;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "stock_id",
            referencedColumnName = "stockId"
    )
    private Stocks stocks;

    public StockPrice(double price, long updatedTime, double todayHigh, double todayLow, double preClose, Stocks stocks) {
        this.price = price;
        this.updatedTime = updatedTime;
        this.todayHigh = todayHigh;
        this.todayLow = todayLow;
        this.preClose = preClose;
        this.stocks =stocks;
    }

    public StockPrice() {

    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "stock_price_stocks",
            joinColumns = @JoinColumn(name = "stock_price_sp_id"),
            inverseJoinColumns = @JoinColumn(name = "stocks_stock_id"))


    public Stocks getStocks() {
        return stocks;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }


    public long getSpId() {
        return spId;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public double getTodayHigh() {
        return todayHigh;
    }

    public void setTodayHigh(double todayHigh) {
        this.todayHigh = todayHigh;
    }

    public double getTodayLow() {
        return todayLow;
    }

    public void setTodayLow(double todayLow) {
        this.todayLow = todayLow;
    }

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }
}
