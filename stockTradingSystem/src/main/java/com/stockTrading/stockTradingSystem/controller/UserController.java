package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String add(@RequestBody UserDtl user){
        System.out.println("post request");
        userService.saveUser(user);
        return  "added successfully";
    }

    @GetMapping("/get")
    public String get(){
        return "success";
    }
}
