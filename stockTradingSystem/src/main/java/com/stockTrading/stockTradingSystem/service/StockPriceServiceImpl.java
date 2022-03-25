package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
import com.stockTrading.stockTradingSystem.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Autowired
    private StockPriceRepository stockPriceRepository;


    @Override
    public Response saveStockPrice(StockPrice stockPrice) {
        try{
            stockPriceRepository.save(stockPrice);
            return new Response(true,"");
        }
        catch (Exception e){
            System.out.println("UserServiceImpl: Registration Failed");
            return new Response(false,"Registration Failed");
        }
    }

    @Override
    public List<StockPrice> getStocksPrice() {
        return stockPriceRepository.findAll();
    }


}
