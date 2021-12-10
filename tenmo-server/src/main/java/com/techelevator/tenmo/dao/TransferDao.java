package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfers;

public interface TransferDao{

    public Transfers transferAmount();

    public Transfers[] viewTransfers();

    public Transfers pendingTransactions();


}
