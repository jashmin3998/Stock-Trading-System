package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;

public interface StockPriceService {
    public Response saveStockPrice(StockPrice stockPrice);
}
