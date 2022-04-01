package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.LimitOrderTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitOrderTransactionRepository extends JpaRepository<LimitOrderTransaction, Long> {

    public List<LimitOrderTransaction> findByUserUsername(String username);
    public LimitOrderTransaction findByOrderId(long orderId);

    @Modifying
    @Query(
            value = "delete from limit_order_transaction where order_id = ?1",
            nativeQuery = true
    )
    public int removeLimitOrder(long orderId);

    public void deleteByOrderId(long orderId);

}
