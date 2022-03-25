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
        Response res;
        try{
            stockRepository.save(stock);
            res = new Response(true,"Successfully Inserted");
        } catch (Exception e){
            System.out.println("StocksServiceImpl: Insertion Failed");
            e.printStackTrace();
            res = new Response(false,"Insertion Failed");
            return res;
        }
        return res;
    }

    @Override
    public List<Stocks> getAllStocks() {
        List<Stocks> allStocks;
        try{
            allStocks = stockRepository.findAll();
        } catch (Exception e){
            System.out.println("StocksServiceImpl: Insertion Failed");
            return null;
        }
        return allStocks;
    }

}
