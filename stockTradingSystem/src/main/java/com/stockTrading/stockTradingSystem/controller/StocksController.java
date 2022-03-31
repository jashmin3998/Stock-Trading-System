package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.*;
import com.stockTrading.stockTradingSystem.model.request.PortfolioResponse;
import com.stockTrading.stockTradingSystem.service.LimitOrderTransactionService;
import com.stockTrading.stockTradingSystem.service.StockPriceService;
import com.stockTrading.stockTradingSystem.service.StocksService;
import com.stockTrading.stockTradingSystem.service.TransactionDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("stocks")
public class StocksController {

    @Autowired
    private StocksService stocksService;
    @Autowired
    private StockPriceService stockPriceService;
    @Autowired
    private TransactionDtlService transactionDtlService;
    @Autowired
    private LimitOrderTransactionService limitOrderTransactionService;




    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response saveStocks(@RequestBody Stocks stock){
        System.out.println("Method: save stocks - StockController");
        stock.setCreationTime((System.currentTimeMillis()));
        StockPrice sp = new StockPrice(stock.getPrice(),
                stock.getCreationTime(),
                stock.getPrice(),
                stock.getPrice(),
                0,
                stock.getPrice(),
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

    @GetMapping(path="transaction-history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDtl> getAllTransactionByUsername(@RequestParam String username){
        return transactionDtlService.getAllStockTransactionByUsername(username);
    }


    //display portfolio

    @GetMapping(path="portfolio", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> getPortfolioByUsername(@RequestParam String username){
        return transactionDtlService.getPortfolioByUsername(username);
    }


    //Limit Order Handling

    @PostMapping(path = "add-limitorder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getPortfolioByUsername(@RequestBody LimitOrderTransaction limitOrderTransaction){
        limitOrderTransaction.setTransactionTime(System.currentTimeMillis());
        return  limitOrderTransactionService.addLimitOrder(limitOrderTransaction);
    }

    @GetMapping(path = "pending-orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LimitOrderTransaction> getAllLimitOrderByUserUsername(@RequestParam String username){
        return limitOrderTransactionService.getAllLimitOrderByUsername(username);
    }

    @DeleteMapping(path="remove-limit-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public int removeLimitOrder(@RequestParam long orderId){
         limitOrderTransactionService.removeLimitOrder(orderId);
         return 1;
    }
}
