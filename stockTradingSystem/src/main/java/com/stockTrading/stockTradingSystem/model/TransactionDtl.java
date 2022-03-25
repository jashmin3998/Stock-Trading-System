package com.stockTrading.stockTradingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDtl {
    @Id
    @SequenceGenerator(
            name = "stocks_transaction_sequence",
            sequenceName = "stocks_transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stocks_transaction_sequence"
    )
    private long TId;
    private long quantity;
    private double purchasedRate;
    private double totalAmount;
    private long transactionTime;
    private int transactionType;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "userId"
    )
    private UserDtl user;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="stock_id",
            referencedColumnName = "stockId"
    )
    private Stocks stock;


}
