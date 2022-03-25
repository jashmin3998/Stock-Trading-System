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

        try{
            UserDtl user = userRepository.findByUsername(transaction.getUser().getUsername());
            if(user == null)
                return new Response(false,"Insertion Failed");
            double totalAmount = 0 ;
            if(transaction.getTransaction_type() == 0)
                totalAmount = (transaction.getAmount() + user.getCashBalance());

            else if(transaction.getTransaction_type() == 1)
                totalAmount =  user.getCashBalance() - transaction.getAmount();
            user.setCashBalance(totalAmount);
            transaction.setUser(user);
            cashTransactionRepository.save(transaction);
            return new Response(true,"Successfully Transaction");
        } catch (Exception e){
            System.out.println("CashTransactionServiceImpl: Insertion Failed");
            e.printStackTrace();
            return new Response(false,"Insertion Failed");
        }
    }

    @Override
    public List<CashTransaction> getAllTransactionById(long userId) {
        return cashTransactionRepository.findByUserUserId(userId);
    }
}
