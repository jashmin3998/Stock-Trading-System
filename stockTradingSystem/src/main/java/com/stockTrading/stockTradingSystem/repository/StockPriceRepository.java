package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
