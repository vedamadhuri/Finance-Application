package com.application.dto;

import com.application.model.Payments;

import java.sql.Timestamp;

public class TransactionHistoryDto {

    private long id;
    private Timestamp transactionCreatedDate;
    private double amount;
    private double balanceAmount;
    private double amountPaid;
    private Payments payments;
    private long months;
    private String message;

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTransactionCreatedDate() {
        return transactionCreatedDate;
    }

    public void setTransactionCreatedDate(Timestamp transactionCreatedDate) {
        this.transactionCreatedDate = transactionCreatedDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }
}
