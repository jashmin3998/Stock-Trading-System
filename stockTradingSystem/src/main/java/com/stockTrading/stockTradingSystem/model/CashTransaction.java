package com.stockTrading.stockTradingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cash_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ctId;

    private double amount;
    private long transaction_time;
    private int transaction_type;  //deposite 0 withdraw 1

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "userId"
    )
    private UserDtl user;



}
