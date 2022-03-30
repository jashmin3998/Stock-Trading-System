package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.CashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashTransactionRepository extends JpaRepository<CashTransaction, Long> {

    List<CashTransaction> findByUserUsername(String username);
}
