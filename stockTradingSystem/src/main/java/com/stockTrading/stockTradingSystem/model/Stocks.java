package com.stockTrading.stockTradingSystem.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(
        name="Stocks",
        uniqueConstraints= @UniqueConstraint(columnNames={"name", "stockSymbol"})
)
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stocksId;
    private String name;
    private String stockSymbol;
    private long total_quantity;
    private long purchased_quantity;
    private Date creation_time;



    public Stocks(){

    }



    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
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
