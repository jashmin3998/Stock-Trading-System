package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import com.stockTrading.stockTradingSystem.model.request.PortfolioResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDtlRepository extends JpaRepository<TransactionDtl, Long> {

    public List<TransactionDtl> findByUserUsername(String username);

    @Query(
            value = "select COALESCE(SUM(quantity),0) from transaction_dtl where user_id = ?1 and stock_id = ?2 and transaction_type = 0",
            nativeQuery = true
    )
    public long getPurchasedStocks(long userId, long stockId);

    @Query(
            value = "select COALESCE(SUM(quantity),0) from transaction_dtl where user_id = ?1 and stock_id = ?2 and transaction_type = 1",
            nativeQuery = true
    )
    public long getSoldStocks(long userId, long stockId);


    @Query(
            value = "select x.stock_symbol, (COALESCE(x.bq,0) - COALESCE(y.sq,0)) as quantity, (COALESCE(x.bt,0) - COALESCE(y.st,0)) as amount,\n" +
                    "x.price\n" +
                    "from\n" +
                    "(\n" +
                    "    select s.stock_symbol, COALESCE(sum(d.quantity),0) as bq, COALESCE(sum(total_amount),0) as bt, sp.price\n" +
                    "\tfrom transaction_dtl d join stocks s \n" +
                    "\ton d.stock_id = s.stock_id join stock_price sp \n" +
                    "\ton s.stock_id = sp.stock_id where user_id = ?1 and d.transaction_type = 0\n" +
                    "\tgroup by s.stock_symbol, sp.price \n" +
                    ") as x\n" +
                    "left outer join \n" +
                    "(\n" +
                    "    select s.stock_symbol, COALESCE(sum(d.quantity), 0) as sq, COALESCE(sum(total_amount),0) as st, sp.price\n" +
                    "\tfrom transaction_dtl d join stocks s \n" +
                    "\ton d.stock_id = s.stock_id join stock_price sp \n" +
                    "\ton s.stock_id = sp.stock_id where user_id = ?1 and d.transaction_type = 1\n" +
                    "\tgroup by s.stock_symbol, sp.price\n" +
                    ") as y on x.stock_symbol = y.stock_symbol;",
            nativeQuery = true
    )
    public List<Object> getPortfolioByUserId(long userId);
}
