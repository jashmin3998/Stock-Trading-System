package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.*;
import com.stockTrading.stockTradingSystem.model.request.PortfolioResponse;
import com.stockTrading.stockTradingSystem.repository.MarketScheduleRepository;
import com.stockTrading.stockTradingSystem.repository.StocksRepository;
import com.stockTrading.stockTradingSystem.repository.TransactionDtlRepository;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDtlServiceImpl implements TransactionDtlService{

    @Autowired
    private TransactionDtlRepository transactionDtlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private MarketScheduleRepository marketScheduleRepository;

    @Override
    @Transactional
    public Response addStockTransaction(TransactionDtl transactionDtl) {
        try{
            MarketSchedule ms = marketScheduleRepository.getMarketScheduleByDates(LocalDate.now());

            if(ms.getEndTime() < System.currentTimeMillis() || ms.getStartTime() > System.currentTimeMillis())
                return new Response(false,"Market is closed. Please try again in market hours");

            UserDtl user = userRepository.findByUsername(transactionDtl.getUser().getUsername());
            Stocks stock = stocksRepository.findByStockSymbol(transactionDtl.getStock().getStockSymbol());

            if(user == null || stock == null)
                return new Response(false,"Order Unsuccessful");
            double stockCurrentRate = stock.getStockPrice().getPrice();
            long stockQuantity = transactionDtl.getQuantity();
            double currentCashBalance = user.getCashBalance();
            double usedCashBalance = user.getUsedCash();
            long purchasedStocks = stock.getPurchasedQuantity();
            double totalAmount = stockQuantity * stockCurrentRate;

            if(transactionDtl.getTransactionType() == 0){
                //pre validations
                if(user.getCashBalance() < totalAmount ||
                        (purchasedStocks + stockQuantity) > stock.getTotalQuantity())
                    return new Response(false,"Order Unsuccessful");

                //updating user attribute
                user.setCashBalance(currentCashBalance - totalAmount);
                user.setUsedCash(usedCashBalance + totalAmount);


                //updating stock attribute
                stock.setPurchasedQuantity(purchasedStocks + stockQuantity);
            }
            else {
                //pre validations
                if(getAvailableStocks(user.getUserId(), stock.getStockId()) < stockQuantity)
                    return new Response(false,"Order Unsuccessful");

                //updating user attribute
                user.setCashBalance(currentCashBalance + totalAmount);
                user.setUsedCash(usedCashBalance - totalAmount);


                //updating stock attribute
                stock.setPurchasedQuantity(purchasedStocks - stockQuantity);
            }


            //updating transaction attribute
            transactionDtl.setTotalAmount(totalAmount);
            transactionDtl.setPurchasedRate(stockCurrentRate);
            transactionDtl.setUser(user);
            transactionDtl.setStock(stock);

            //saving the transaction
            transactionDtlRepository.save(transactionDtl);
            return new Response(true,"Order Successfully");

        }
        catch (Exception e){
            return new Response(false,"Order Unsuccessful");
        }
    }

    @Override
    public List<TransactionDtl> getAllStockTransactionByUsername(String username) {
        return transactionDtlRepository.findByUserUsername(username);
    }


    @Override
    public long getAvailableStocks(long userId, long stockId){
        try {
            long y = transactionDtlRepository.getSoldStocks(userId, stockId);
            long x = transactionDtlRepository.getPurchasedStocks(userId, stockId);
            return ( x-y );
        }
        catch(Exception e){
            return 0;
        }
    }

    @Override
    public List<Object> getPortfolioByUsername(String username){
        long userId = userRepository.findByUsername(username).getUserId();
        List<Object> obj = transactionDtlRepository.getPortfolioByUserId(userId);
        int i =0;
        return obj;
    }


    @Override
    @Transactional
    public Response executeLimitOrders(TransactionDtl transactionDtl){

        try {

            UserDtl user = userRepository.findByUsername(transactionDtl.getUser().getUsername());
            Stocks stock = stocksRepository.findByStockSymbol(transactionDtl.getStock().getStockSymbol());

            if (user == null || stock == null)
                return new Response(false, "Order Unsuccessful");

            double stockCurrentRate = transactionDtl.getPurchasedRate();
            long stockQuantity = transactionDtl.getQuantity();
            double currentCashBalance = user.getCashBalance();
            double usedCashBalance = user.getUsedCash();
            long purchasedStocks = stock.getPurchasedQuantity();
            double totalAmount = stockQuantity * stockCurrentRate;

            if (transactionDtl.getTransactionType() == 0) {
                //pre validations
//            if(user.getCashBalance() < totalAmount ||
//                    (purchasedStocks + stockQuantity) > stock.getTotalQuantity())
//                return new Response(true,"Stocks Purchase Failed");

                //updating user attribute
//            user.setCashBalance(currentCashBalance - totalAmount);
//            user.setUsedCash(usedCashBalance + totalAmount);


                //updating stock attribute
                stock.setPurchasedQuantity(purchasedStocks + stockQuantity);
            } else {
                //pre validations
                if (getAvailableStocks(user.getUserId(), stock.getStockId()) < stockQuantity)
                    return new Response(false, "Stocks Sell Order Failed");

                //updating user attribute
                user.setCashBalance(currentCashBalance + totalAmount);
                user.setUsedCash(usedCashBalance - totalAmount);


                //updating stock attribute
                stock.setPurchasedQuantity(purchasedStocks - stockQuantity);
            }


            //updating transaction attribute
            transactionDtl.setTotalAmount(totalAmount);
            transactionDtl.setPurchasedRate(stockCurrentRate);
            transactionDtl.setUser(user);
            transactionDtl.setStock(stock);

            //saving the transaction
            transactionDtlRepository.save(transactionDtl);
            return new Response(true, "Order Successful");
        }
        catch (Exception e){
            return new Response(false, "Order Unsuccessful");
        }
    }
}
