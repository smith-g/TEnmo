package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfers;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao{

     Transfers transferAmount();

     List<Transfers> viewTransfers();

     Transfers pendingTransactions();

     Transfers findTransfer(long id);

     Transfers createTransfer(Transfers transfers);

}
