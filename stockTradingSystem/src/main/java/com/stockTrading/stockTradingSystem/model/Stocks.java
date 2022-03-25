package com.stockTrading.stockTradingSystem.model;


import javax.persistence.*;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints= @UniqueConstraint(columnNames={"name", "stockSymbol"})
)
public class Stocks {

    @Id
    @SequenceGenerator(
            name = "stocks_sequence",
            sequenceName = "stocks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stocks_sequence"
    )
    private long stockId;
    private String name;
    private String stockSymbol;
    private long totalQuantity;
    private long purchasedQuantity;
    private long creationTime;
    @Column(precision=2, scale = 6)
    private double price;
//    @OneToOne(
//            mappedBy = "stocks",
//            cascade = CascadeType.ALL
//    )
//    private StockPrice stockPrice;
//
//    public StockPrice getStockPrice() {
//        return stockPrice;
//    }
//
//    public void setStockPrice(StockPrice stockPrice) {
//        this.stockPrice = stockPrice;
//    }


    public Stocks(String name, String stockSymbol, long totalQuantity, long purchasedQuantity, long creationTime, double price) {
        this.name = name;
        this.stockSymbol = stockSymbol;
        this.totalQuantity = totalQuantity;
        this.purchasedQuantity = purchasedQuantity;
        this.creationTime = creationTime;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(long purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Stocks(){

    }




}
