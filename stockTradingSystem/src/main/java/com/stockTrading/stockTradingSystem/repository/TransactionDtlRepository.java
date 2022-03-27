package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDtlRepository extends JpaRepository<TransactionDtl, Long> {

    public List<TransactionDtl> findByUserUserId(long userId);

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
}
