package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;
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

        try{
            userRepository.save(user);
            return new Response(true,"");
        }
        catch (Exception e){
            System.out.println("UserServiceImpl: Registration Failed");
            return new Response(false,"Registration Failed");

        }

    }

    @Override
    public Response getUser(String username, String pwd) {
        Response res;
        try{
            UserDtl user = userRepository.findByUsernamepAndPwd(username, pwd);
            if(user != null){
                return new Response(true, "");
            }
            else
                return new Response(false,"Login Failed");

        }
        catch (Exception e){
            System.out.println("UserServiceImpl: Login Failed");
            return new Response(false,"Login Failed");
        }


    }



}
