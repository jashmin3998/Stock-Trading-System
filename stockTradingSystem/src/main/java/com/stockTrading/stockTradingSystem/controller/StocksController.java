package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.service.StockPriceService;
import com.stockTrading.stockTradingSystem.service.StocksService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stocks")
public class StocksController {

    private StocksService stocksService;

    public StocksController(StocksService stocksService, StockPriceService stockPriceService){
        super();
        this.stocksService = stocksService;

    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveStocks(@RequestBody Stocks stock){
        System.out.println("Method: save stocks - StockController");
        stock.setCreation_time(System.currentTimeMillis());
        return stocksService.saveStocks(stock);
        //stocksService.saveStocks(stock);
    }

    @GetMapping(path = "allstocks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stocks> getStocks(){
        return stocksService.getAllStocks();
    }
}
