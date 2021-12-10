package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {

    public static String TRANSFER_APPROVE = "1";
    public static String TRANSFER_PENDING = "2";
    public static String TRANSFER_NOT_APPROVED = "3";



    public static String TRANSFER_SENDS = "1";
    public static String TRANSFER_RECEIVES = "2";


    private long transferID;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;

    public Transfers(long transferID, long accountFrom, long accountTo, BigDecimal amount) {
        this.transferID = transferID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public long getTransferID() {
        return transferID;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
