package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import com.stockTrading.stockTradingSystem.model.request.PortfolioResponse;

import java.util.List;

public interface TransactionDtlService {

    public Response addStockTransaction(TransactionDtl transactionDtl);
    public List<TransactionDtl> getAllStockTransactionByUsername(String username);
    public List<Object> getPortfolioByUsername(String username);

}
