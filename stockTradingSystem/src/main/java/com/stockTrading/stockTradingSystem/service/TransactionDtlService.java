package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.TransactionDtl;

import java.util.List;

public interface TransactionDtlService {

    public Response addStockTransaction(TransactionDtl transactionDtl);
    public List<TransactionDtl> getAllStockTransactionByUserId(long userId);

}
