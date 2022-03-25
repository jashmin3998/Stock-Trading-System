package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDtlRepository extends JpaRepository<TransactionDtl, Long> {

    public List<TransactionDtl> findByUserUserId(long userId);
}
