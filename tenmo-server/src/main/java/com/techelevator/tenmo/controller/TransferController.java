package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao){
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/transferhistory", method = RequestMethod.GET)
    public List<Transfers> history(){
        return transferDao.viewTransfers();
    }

    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfers transfersById(@PathVariable long id) {
        return transferDao.findTransfer(id);
    }

    @RequestMapping(path = "/createtransfer", method = RequestMethod.POST)
    public Transfers createTransfers(@RequestBody Transfers transfers){
//        long fromAcc = transfers.getAccountFrom();
//        long toAcc = transfers.getAccountTo();
//        Accounts fromAccount = accountDao.getAccount(fromAcc);
//        fromAccount.setBalance(fromAccount.getBalance().subtract(transfers.getAmount()));
//        accountDao.updateBalance(fromAccount.getBalance().subtract(transfers.getAmount()), fromAcc);

        return transferDao.createTransfer(transfers);
    }
}
