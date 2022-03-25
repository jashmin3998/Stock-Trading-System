package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
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
    StockPriceService stockPriceService;

    public StocksController(StocksService stocksService, StockPriceService stockPriceService){
        super();
        this.stocksService = stocksService;
        this.stockPriceService = stockPriceService;
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveStocks(@RequestBody Stocks stock){
        System.out.println("Method: save stocks - StockController");
        stock.setCreationTime((System.currentTimeMillis()));
        StockPrice sp = new StockPrice(stock.getPrice(),
                stock.getCreationTime(),
                stock.getPrice(),
                stock.getPrice(),
                0,
                stock);

        return stockPriceService.saveStockPrice(sp);
        //stocksService.saveStocks(stock);
    }

    @GetMapping(path = "allstocks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stocks> getStocks(){
        return stocksService.getAllStocks();
    }

    @GetMapping(path="get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockPrice> getStocksPrice(){
        return stockPriceService.getStocksPrice();
    }
}
