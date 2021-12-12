package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfers;

import java.util.List;

public interface TransferDao{

    public Transfers transferAmount();

    public List<Transfers> viewTransfers();

    public Transfers pendingTransactions();

    public Transfers findTransfer(int id) throws Exception;


}
