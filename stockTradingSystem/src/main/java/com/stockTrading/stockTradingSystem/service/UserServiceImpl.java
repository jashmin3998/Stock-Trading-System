package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDtl saveUser(UserDtl user) {
        return userRepository.save(user);
    }
}
