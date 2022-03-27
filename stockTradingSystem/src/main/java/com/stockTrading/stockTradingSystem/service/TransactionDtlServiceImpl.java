package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.Stocks;
import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.repository.StocksRepository;
import com.stockTrading.stockTradingSystem.repository.TransactionDtlRepository;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionDtlServiceImpl implements TransactionDtlService{

    @Autowired
    private TransactionDtlRepository transactionDtlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StocksRepository stocksRepository;

    @Override
    @Transactional
    public Response addStockTransaction(TransactionDtl transactionDtl) {
        try{
            UserDtl user = userRepository.findByUsername(transactionDtl.getUser().getUsername());
            Stocks stock = stocksRepository.findByStockSymbol(transactionDtl.getStock().getStockSymbol());

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
                    return new Response(true,"Stocks Purchase Failed");

                //updating user attribute
                user.setCashBalance(currentCashBalance - totalAmount);
                user.setUsedCash(usedCashBalance + totalAmount);


                //updating stock attribute
                stock.setPurchasedQuantity(purchasedStocks + stockQuantity);
            }
            else {
                //pre validations
                if(getAvailableStocks(user.getUserId(), stock.getStockId()) < stockQuantity)
                    return new Response(true,"Stocks sell Failed");

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
            return new Response(true,"Stocks Purchased/sell Successfully");

        }
        catch (Exception e){
            return new Response(true,"Stocks Purchase Failed");
        }
    }

    @Override
    public List<TransactionDtl> getAllStockTransactionByUserId(long userId) {
        return transactionDtlRepository.findByUserUserId(userId);
    }

    public long getAvailableStocks(long userId, long stockId){
        try {
            long y = transactionDtlRepository.getSoldStocks(userId, stockId);
            long x = transactionDtlRepository.getPurchasedStocks(userId, stockId);
            return ( x-y );
        }
        catch(Exception e){
            System.out.print("getAvailableStocks Exception");
            return 0;
        }
    }
}
