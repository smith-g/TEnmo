package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    public static long TRANSFER_APPROVE = 2;
    public static long TRANSFER_PENDING = 1;
    public static long TRANSFER_NOT_APPROVED = 3;

    public static long TRANSFER_SENDS = 2;
    public static long TRANSFER_RECEIVES = 1;

    private long transferID;
    private long transferType;
    private long transferStatus;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;

    public long getTransferType() {
        return transferType;
    }

    public void setTransferType(long transferType) {
        if (TRANSFER_SENDS == transferType) {
            this.transferType = TRANSFER_SENDS;
        } else if (TRANSFER_RECEIVES == transferType) {
            this.transferType = TRANSFER_RECEIVES;
        }
    }

    public long getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(long transferStatus) {
        if (TRANSFER_APPROVE == transferStatus) {
            this.transferStatus = TRANSFER_APPROVE;
        } else if (TRANSFER_PENDING == transferStatus) {
            this.transferStatus = TRANSFER_PENDING;
            ;
        } else
            this.transferStatus = TRANSFER_NOT_APPROVED;
    }

    public long getTransferID() {
        return transferID;
    }

    public void setTransferID(long transferID) {
        this.transferID = transferID;
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
