package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    @Modifying
    @Query(
            value = "update stock_price set price = ((random()*(1)-0.5)*price*0.0005)+price",
            nativeQuery = true
    )
    public int updateStockPrice();

    @Modifying
    @Query(
            value = "update stock_price set today_high = price where today_high < price",
            nativeQuery = true
    )
    public int updateTodayHighPrice();

    @Modifying
    @Query(
            value = "update stock_price set today_low = price where today_low > price;",
            nativeQuery = true
    )
    public int updateTodayLowPrice();

    @Query(
            value = "select price from stock_price where stock_id = ?1",
            nativeQuery = true
    )
    public double getCurrentPrice(long stockId);

    @Modifying
    @Query(
            value = "update stock_price set open_price = price",
            nativeQuery = true
    )
    public int updateOpenPrice();
}
