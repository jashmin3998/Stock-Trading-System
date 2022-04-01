package com.stockTrading.stockTradingSystem.service;


import com.stockTrading.stockTradingSystem.model.*;
import com.stockTrading.stockTradingSystem.repository.LimitOrderTransactionRepository;
import com.stockTrading.stockTradingSystem.repository.MarketScheduleRepository;
import com.stockTrading.stockTradingSystem.repository.StocksRepository;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LimitOrderTransactionServiceImpl implements LimitOrderTransactionService {

    @Autowired
    private LimitOrderTransactionRepository limitOrderTransactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private MarketScheduleRepository marketScheduleRepository;
    @Autowired
    private TransactionDtlService transactionDtlService;

    @Override
    @Transactional
    public Response addLimitOrder(LimitOrderTransaction limitOrderTransaction) {

        try{
            MarketSchedule ms = marketScheduleRepository.getMarketScheduleByDates(LocalDate.now());

            if(ms.getEndTime() < System.currentTimeMillis() || ms.getStartTime() > System.currentTimeMillis())
                return new Response(false,"Market is closed. PLease try again in market hours");

            UserDtl user = userRepository.findByUsername(limitOrderTransaction.getUser().getUsername());
            Stocks stock = stocksRepository.findByStockSymbol(limitOrderTransaction.getStock().getStockSymbol());

            if(user == null || stock == null)
                return new Response(false,"Limit Order not Placed");
            limitOrderTransaction.setTotalAmount(limitOrderTransaction.getQuantity() * limitOrderTransaction.getRate());

            if(limitOrderTransaction.getTransactionType() == 0 &&
                    user.getCashBalance() > limitOrderTransaction.getTotalAmount() &&
                    (stock.getPurchasedQuantity() + limitOrderTransaction.getQuantity()) < stock.getTotalQuantity()){

                user.setCashBalance(user.getCashBalance() - limitOrderTransaction.getTotalAmount());
                user.setUsedCash(user.getCashBalance() + limitOrderTransaction.getTotalAmount());
            }
            else if(limitOrderTransaction.getTransactionType() == 1 &&
                    transactionDtlService.getAvailableStocks(user.getUserId(), stock.getStockId()) > limitOrderTransaction.getQuantity()){

            }
            else {
                return new Response(false, "Order Unsuccessful");
            }
            limitOrderTransaction.setUser(user);
            limitOrderTransaction.setStock(stock);
            limitOrderTransactionRepository.save(limitOrderTransaction);
            return new Response(true,"Limit Order Placed Successfully");
        }
        catch (Exception e){
            return new Response(true,"Order Unsuccessful");
        }
    }

    @Override
    public List<LimitOrderTransaction> getAllLimitOrders(){
        return limitOrderTransactionRepository.findAll();
    }

    @Override
    public List<LimitOrderTransaction> getAllLimitOrderByUsername(String username) {
        return limitOrderTransactionRepository.findByUserUsername(username);
    }

    @Override
    @Transactional
    public void removeLimitOrder(long orderId) {
        LimitOrderTransaction order = limitOrderTransactionRepository.findByOrderId(orderId);
        UserDtl user = order.getUser();
        userRepository.updateCashBalance(user.getUserId(),user.getCashBalance() + order.getTotalAmount());
        userRepository.updateUsedCashBalance(user.getUserId(),user.getUsedCash() - order.getTotalAmount());
        limitOrderTransactionRepository.deleteByOrderId(orderId);
    }


}
