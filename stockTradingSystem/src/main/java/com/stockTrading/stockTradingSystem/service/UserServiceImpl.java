package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.Exception.InvalidUserException;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.model.UserRole;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response saveUser(UserDtl user) {
        userRepository.save(user);
        return new Response(true,"");
    }

    @Override
    public Response getUser(String username, String pwd) throws InvalidUserException {
            UserDtl user = userRepository.findByUsernamepAndPwd(username, pwd);
            if(user != null){
                return new Response(true, "");
            }
            else
                throw new InvalidUserException("Username or Password is incorrect");

    }

    @Override
    public double getCashBalance(String username) {
        UserDtl user = userRepository.findByUsername(username);
        return user.getCashBalance();
    }

    @Override
    public UserRole getUserRole(String username) {
        UserDtl user = userRepository.findByUsername(username);
        return user.getUserRole();
    }
}
