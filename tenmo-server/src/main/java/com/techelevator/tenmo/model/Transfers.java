package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transfers {

    public static String TRANSFER_APPROVE = "1";
    public static String TRANSFER_PENDING = "2";
    public static String TRANSFER_NOT_APPROVED = "3";
    public static String[] TRANSFER_APPROVAL = {TRANSFER_APPROVE, TRANSFER_PENDING, TRANSFER_NOT_APPROVED};


    public static String TRANSFER_SENDS = "1";
    public static String TRANSFER_RECEIVES = "2";
    public static String[] TRANSFERS_TYPE = {TRANSFER_SENDS, TRANSFER_RECEIVES};

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

    public Transfers() {

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
        return transferID == transfers.transferID && accountFrom == transfers.accountFrom && accountTo == transfers.accountTo && Objects.equals(amount, transfers.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferID, accountFrom, accountTo, amount);
    }

    @Override
    public String toString() {
        return "Transfers{" +
                "transferID=" + transferID +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
