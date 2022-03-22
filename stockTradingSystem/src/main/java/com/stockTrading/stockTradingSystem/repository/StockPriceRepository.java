package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
