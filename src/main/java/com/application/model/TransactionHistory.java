package com.application.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(name = "TransactionDate")
    private Timestamp transactionDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "amountPaid")
    private double amountPaid;

    @Column(name = "balanceAmount")
    private double balanceAmount;

    @Column(name = "months")
    private long months;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payments payments;


    @Column(name = "message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getMonths() {
        return months;
    }

    public void setMonths(Long months) {
        this.months = months;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }
}
