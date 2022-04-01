package com.stockTrading.stockTradingSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class LimitOrderTransaction {

    @Id
    @SequenceGenerator(
            name = "stocks_limitorder_sequence",
            sequenceName = "stocks_limitorder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stocks_limitorder_sequence"
    )
    private long orderId;
    private long quantity;
    private double rate;
    private double totalAmount;
    private long transactionTime;
    private int transactionType;

    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "userId"
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private UserDtl user;
    @ManyToOne(
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name="stock_id",
            referencedColumnName = "stockId"
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Stocks stock;

}
