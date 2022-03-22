package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;


public interface UserService {

    public Response saveUser(UserDtl user);
    public Response getUser(String username, String pwd);

}
