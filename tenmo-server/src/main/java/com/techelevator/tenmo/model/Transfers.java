package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transfers {

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

    public Transfers(long transferID, long accountFrom, long accountTo, BigDecimal amount, long transferType,
                     long transferStatus) {
        this.transferID = transferID;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfers() {

    }

    public long getTransferType() {
        return transferType;
    }

    public void setTransferType(long transferType) {
        if(TRANSFER_SENDS == transferType){
            this.transferType = TRANSFER_SENDS;
        }else if (TRANSFER_RECEIVES == transferType) {
            this.transferType = TRANSFER_RECEIVES;
        }
    }

    public long getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(long transferStatus) {
        if(TRANSFER_APPROVE == transferStatus){
            this.transferStatus= TRANSFER_APPROVE;
        }else if (TRANSFER_PENDING == transferStatus){
            this.transferStatus = TRANSFER_PENDING;;
        }else
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfers transfers = (Transfers) o;
        return transferID == transfers.transferID && transferType == transfers.transferType && transferStatus == transfers.transferStatus && accountFrom == transfers.accountFrom && accountTo == transfers.accountTo && Objects.equals(amount, transfers.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferID, transferType, transferStatus, accountFrom, accountTo, amount);
    }

    @Override
    public String toString() {
        return "Transfers{" +
                "transferID=" + transferID +
                ", transferType=" + transferType +
                ", transferStatus=" + transferStatus +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
