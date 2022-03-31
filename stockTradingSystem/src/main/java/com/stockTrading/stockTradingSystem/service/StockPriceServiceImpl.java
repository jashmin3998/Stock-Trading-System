package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
import com.stockTrading.stockTradingSystem.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Autowired
    private StockPriceRepository stockPriceRepository;


    @Override
    public Response saveStockPrice(StockPrice stockPrice) {
        stockPriceRepository.save(stockPrice);
        return new Response(true,"Stock saved Successfully");
    }

    @Override
    public List<StockPrice> getStocksPrice() {
        return stockPriceRepository.findAll();
    }

    @Override
    @Transactional
    public int updateAllStocks() {
        return stockPriceRepository.updateStockPrice();
    }

    @Transactional
    @Override
    public int updateTodayHigh() {
        return stockPriceRepository.updateTodayHighPrice();
    }

    @Transactional
    @Override
    public int updateTodayLow() {
        return stockPriceRepository.updateTodayLowPrice();
    }

    @Override
    public double getCurrentPrice(long stockId) {
        return stockPriceRepository.getCurrentPrice(stockId);
    }

    @Transactional
    @Override
    public int updateOpenPrice() {
        return stockPriceRepository.updateOpenPrice();
    }


}
