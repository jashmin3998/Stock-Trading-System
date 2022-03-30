package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.Exception.InvalidUserException;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.model.UserRole;


public interface UserService {

    public Response saveUser(UserDtl user);
    public Response getUser(String username, String pwd) throws InvalidUserException;
    public double getCashBalance(String username);
    public UserRole getUserRole(String username);
}
