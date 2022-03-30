package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.CashTransaction;
import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;

import javax.transaction.Transaction;
import java.util.List;

public interface CashTransactionService {
    public Response addCashTransaction(CashTransaction transaction);
    public List<CashTransaction> getAllTransactionById(String username);

}
