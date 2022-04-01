package com.stockTrading.stockTradingSystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {

    private String stockSymbol;
    private long quantity;
    private double price;
    private double totalAmount;
}
