package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfers;

import java.util.List;

public interface TransferDao{

    public List<Transfers> viewTransfers();

    public Transfers pendingTransactions();

}
