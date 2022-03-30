package com.stockTrading.stockTradingSystem.service;


import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class StocksServiceImpl implements  StocksService{

    @Autowired
    private StocksRepository stockRepository;



    @Override
    public Response saveStocks(Stocks stock) {
        stockRepository.save(stock);
        return new Response(true,"Successfully Inserted");
    }

    @Override
    public List<Stocks> getAllStocks() {
        return stockRepository.findAll();
    }

}
