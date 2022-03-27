package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer>{

    public Stocks findByStockSymbol(String symbol);


}
