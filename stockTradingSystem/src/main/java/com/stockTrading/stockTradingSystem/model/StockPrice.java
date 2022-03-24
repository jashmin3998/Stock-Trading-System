package com.stockTrading.stockTradingSystem.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Stock_price")
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sp_id")
    private long spId;

    private int stId;
    @Column(precision=5, scale = 5)
    private float price;
    private long updated_time;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Stocks stocks;


    public StockPrice() {

    }

    public StockPrice(int stockId, float price, long updated_time) {
        this.stId = stockId;
        this.price = price;
        this.updated_time = updated_time;
    }



    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long updated_time) {
        this.updated_time = updated_time;
    }

    public long getSp_id() {
        return spId;
    }
}
