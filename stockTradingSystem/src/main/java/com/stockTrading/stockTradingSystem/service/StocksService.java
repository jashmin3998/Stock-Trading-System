package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.Stocks;

import java.util.List;

public interface StocksService {
    public Response saveStocks(Stocks stock);
    List<Stocks> getAllStocks();
}

