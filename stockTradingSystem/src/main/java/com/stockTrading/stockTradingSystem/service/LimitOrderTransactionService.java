package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.LimitOrderTransaction;
import com.stockTrading.stockTradingSystem.model.Response;

import java.util.List;

public interface LimitOrderTransactionService {

    public Response addLimitOrder(LimitOrderTransaction limitOrderTransaction);
    public List<LimitOrderTransaction> getAllLimitOrders();
    public List<LimitOrderTransaction> getAllLimitOrderByUsername(String username);
    public void removeLimitOrder(long orderId);
}
