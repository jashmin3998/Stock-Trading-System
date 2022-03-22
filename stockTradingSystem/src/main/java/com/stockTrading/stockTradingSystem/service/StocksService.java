package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.Stocks;

public interface StocksService {
    public Response saveStocks(Stocks stock);
}

