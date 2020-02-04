package com.application.dto;


public class PaymentsDto {

    private long userId;

    private long amount;

    private long amountPaid;

    private long balanceAmount;

    private long months;

    private GroupUserDto groupUserDto;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public GroupUserDto getGroupUserDto() {
        return groupUserDto;
    }

    public void setGroupUserDto(GroupUserDto groupUserDto) {
        this.groupUserDto = groupUserDto;
    }
}
