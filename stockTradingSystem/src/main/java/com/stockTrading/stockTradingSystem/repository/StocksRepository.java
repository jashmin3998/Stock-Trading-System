package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stocks, Integer>{

}
