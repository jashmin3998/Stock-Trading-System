package com.stockTrading.stockTradingSystem.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(
        name="stocks",
        uniqueConstraints= @UniqueConstraint(columnNames={"name", "stockSymbol"})
)
public class Stocks {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private int stocksId;
    private String name;
    private String stockSymbol;
    private long total_quantity;
    private long purchased_quantity;
    private long creation_time;
    @Column(precision=2, scale = 6)
    private float price;

    /*@OneToOne(mappedBy = "stocks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StockPrice stockPrice;*/

    public Stocks(){

    }

    public Stocks(String name, String stockSymbol, long total_quantity, long purchased_quantity, long creation_time) {
        this.name = name;
        this.stockSymbol = stockSymbol;
        this.total_quantity = total_quantity;
        this.purchased_quantity = purchased_quantity;
        this.creation_time = creation_time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(long creation_time) {
        this.creation_time = creation_time;
    }



    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }



    public int getStocksId() {
        return stocksId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(long total_quantity) {
        this.total_quantity = total_quantity;
    }

    public long getPurchased_quantity() {
        return purchased_quantity;
    }

    public void setPurchased_quantity(long purchased_quantity) {
        this.purchased_quantity = purchased_quantity;
    }




}
