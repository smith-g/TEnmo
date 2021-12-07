package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Accounts{

    private long account_id;
    private long user_id;
    private BigDecimal balance;

    public Accounts(long account_id, long user_id, BigDecimal balance) {
        this.account_id = account_id;
        this.user_id = account_id;
        this.balance = balance;
    }

    public Accounts() {

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", balance=" + balance +
                '}';
    }
}
