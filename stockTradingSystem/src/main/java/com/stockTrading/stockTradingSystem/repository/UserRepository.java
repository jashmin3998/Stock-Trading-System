package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.UserDtl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDtl, Integer> {

    @Query(value = "select u from UserDtl u where u.username=?1 and u.pwd=?2")
    UserDtl findByUsernamepAndPwd(String username, String pwd);
}
