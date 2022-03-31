package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;

import java.util.List;

public interface StockPriceService {
    public Response saveStockPrice(StockPrice stockPrice);
    public List<StockPrice> getStocksPrice();
    public int updateAllStocks();
    public int updateTodayHigh();
    public int updateTodayLow();
    public double getCurrentPrice(long stockId);
    public int updateOpenPrice();
}
