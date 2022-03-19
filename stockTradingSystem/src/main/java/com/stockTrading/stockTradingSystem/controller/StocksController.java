package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.service.StocksService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
public class StocksController {

    private StocksService stocksService;

    public StocksController(StocksService stocksService){
        super();
        this.stocksService = stocksService;
    }

    @PostMapping("/add")
    public String saveStocks(@RequestBody Stocks stock){
        stocksService.saveStocks(stock);
        return "stock added succesfully";
    }
}
