package com.stockTrading.stockTradingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionDtl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long TId;
    private long UserId;
    private long StockId;
    private long quantity;
    private double purchasedRate;
    private double totalAmount;
    private long transactionTime;
    private int transactionType;

    public long getTId() {
        return TId;
    }

    public long getUserId() {
        return UserId;
    }

    public long getStockId() {
        return StockId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPurchasedRate() {
        return purchasedRate;
    }

    public void setPurchasedRate(double purchasedRate) {
        this.purchasedRate = purchasedRate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
}
