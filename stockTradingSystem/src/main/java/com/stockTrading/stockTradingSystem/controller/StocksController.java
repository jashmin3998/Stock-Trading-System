package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.StockPrice;
import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import com.stockTrading.stockTradingSystem.service.StockPriceService;
import com.stockTrading.stockTradingSystem.service.StocksService;
import com.stockTrading.stockTradingSystem.service.TransactionDtlService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("stocks")
public class StocksController {

    private StocksService stocksService;
    private StockPriceService stockPriceService;
    private TransactionDtlService transactionDtlService;


    public StocksController(StocksService stocksService,
                            StockPriceService stockPriceService,
                            TransactionDtlService transactionDtlService){
        super();
        this.stocksService = stocksService;
        this.stockPriceService = stockPriceService;
        this.transactionDtlService = transactionDtlService;
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

    @GetMapping(path = "allstocks")
    public List<Stocks> getStocks(){
        List<Stocks> s =stocksService.getAllStocks();
        System.out.println("get all Stocks");
        return s;
    }

    @GetMapping(path="get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockPrice> getStocksPrice(){
        return stockPriceService.getStocksPrice();
    }

    //stocks buy sell

    @PostMapping(path="transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addStockTransaction(@RequestBody TransactionDtl transaction){
        transaction.setTransactionTime(System.currentTimeMillis());
        return transactionDtlService.addStockTransaction(transaction);
    }

    @GetMapping(path="transactionHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDtl> getAllTransactionByUsername(@RequestBody long userId){
        return transactionDtlService.getAllStockTransactionByUserId(userId);
    }
}
