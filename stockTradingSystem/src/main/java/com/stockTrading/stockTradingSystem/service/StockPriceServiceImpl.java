package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
import com.stockTrading.stockTradingSystem.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Autowired
    private StockPriceRepository stockPriceRepository;


    @Override
    public Response saveStockPrice(StockPrice stockPrice) {
        Response res;
        try{
            stockPriceRepository.save(stockPrice);
            res = new Response(true,"");
        }
        catch (Exception e){
            System.out.println("UserServiceImpl: Registration Failed");
            res = new Response(false,"Registration Failed");
            return res;
        }

        return res;
    }
}
