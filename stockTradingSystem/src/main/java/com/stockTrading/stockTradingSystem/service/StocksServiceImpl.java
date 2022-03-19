package com.stockTrading.stockTradingSystem.service;


import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StocksServiceImpl implements  StocksService{

    @Autowired
    private StocksRepository stockRepository;

    @Override
    public Stocks saveStocks(Stocks stock) {
        return stockRepository.save(stock);
    }
}
