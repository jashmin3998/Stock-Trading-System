package com.stockTrading.stockTradingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Stock_price")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    //@Column(precision=5, scale = 5)
    private double price;
    private long updatedTime;
    private double todayHigh;
    private double todayLow;
    private double preClose;
    private double openPrice;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "stock_id",
            referencedColumnName = "stockId"
    )
    @JsonBackReference
    private Stocks stocks;

    public StockPrice(double price, long updatedTime, double todayHigh, double todayLow, double preClose, double openPrice, Stocks stocks) {
        this.price = price;
        this.updatedTime = updatedTime;
        this.todayHigh = todayHigh;
        this.todayLow = todayLow;
        this.preClose = preClose;
        this.openPrice = openPrice;
        this.stocks =stocks;
    }



//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "stock_price_stocks",
//            joinColumns = @JoinColumn(name = "stock_price_sp_id"),
//            inverseJoinColumns = @JoinColumn(name = "stocks_stock_id"))






}
