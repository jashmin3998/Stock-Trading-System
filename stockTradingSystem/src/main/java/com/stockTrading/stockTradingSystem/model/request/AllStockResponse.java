package com.stockTrading.stockTradingSystem.model.request;

import javax.persistence.Column;
import java.sql.Date;

public class AllStockResponse {
    private String name;
    private String stockSymbol;
    private long total_quantity;
    private long purchased_quantity;
    private float price;

    public AllStockResponse(String name, String stockSymbol, long total_quantity, long purchased_quantity, float price) {
        this.name = name;
        this.stockSymbol = stockSymbol;
        this.total_quantity = total_quantity;
        this.purchased_quantity = purchased_quantity;
        this.price = price;
    }
}
