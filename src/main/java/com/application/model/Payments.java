package com.application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
public class Payments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "paymentsId")
    private long paymentsId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "amountPaid")
    private double amountPaid;

    @Column(name = "balanceAmount")
    private double balanceAmount;

    @Column(name = "months")
    private long months;

    @ManyToOne
    @JoinColumn(name = "id")
    private GroupUser groupUsers;

    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL)
    private List<TransactionHistory> transactionHistoryList = new ArrayList<>();

    public long getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(long paymentsId) {
        this.paymentsId = paymentsId;
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

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public GroupUser getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(GroupUser groupUsers) {
        this.groupUsers = groupUsers;
    }

    public List<TransactionHistory> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public void setTransactionHistoryList(List<TransactionHistory> transactionHistoryList) {
        this.transactionHistoryList = transactionHistoryList;
    }


}



