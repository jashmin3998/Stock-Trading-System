package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.LimitOrderTransaction;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDtl, Integer> {

    @Query(value = "select u from UserDtl u where u.username=?1 and u.pwd=?2")
    UserDtl findByUsernamepAndPwd(String username, String pwd);

    UserDtl findByUsername(String username);

    UserDtl findByUserId(long userId);

    @Modifying
    @Query(
            value = "update user_dtl set cash_balance = ?2 where user_id = ?1",
            nativeQuery = true
    )
    int updateCashBalance(long userId, double balance);

    @Modifying
    @Query(
            value = "update user_dtl set used_cash = ?2 where user_id = ?1",
            nativeQuery = true
    )
    int updateUsedCashBalance(long userId, double balance);
}
