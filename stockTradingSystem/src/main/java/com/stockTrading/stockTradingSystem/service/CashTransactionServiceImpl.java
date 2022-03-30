package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.CashTransaction;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.repository.CashTransactionRepository;
import com.stockTrading.stockTradingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CashTransactionServiceImpl implements CashTransactionService{

    @Autowired
    private CashTransactionRepository cashTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Response addCashTransaction(CashTransaction transaction) {

        double totalAmount = 0 ;
        UserDtl user = userRepository.findByUsername(transaction.getUser().getUsername());

        if(transaction.getTransactionType() == 0)
            totalAmount = (transaction.getAmount() + user.getCashBalance());
        else if(transaction.getTransactionType() == 1 && user.getCashBalance() > transaction.getAmount())
            totalAmount =  user.getCashBalance() - transaction.getAmount();
        else
            return new Response(false,"Transaction Unsuccessful");

        user.setCashBalance(totalAmount);
        transaction.setUser(user);
        cashTransactionRepository.save(transaction);
        return new Response(true,"Transaction Successful");

    }

    @Override
    public List<CashTransaction> getAllTransactionById(String username) {
        return cashTransactionRepository.findByUserUsername(username);
    }


}
