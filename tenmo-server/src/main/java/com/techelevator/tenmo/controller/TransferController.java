package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.data.repository.support.PageableExecutionUtils;
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

    @RequestMapping(path = "/transferbyid", method = RequestMethod.GET)
    public Transfers transfersById(@PathVariable int id) throws Exception {
        return transferDao.findTransfer(id);
    }

    @RequestMapping(path = "/createtransfer", method = RequestMethod.POST)
    public void createTransfers(@RequestBody Transfers transfers){
         transferDao.createTransfer(transfers.getTransferType(), transfers.getTransferStatus(),
                 transfers.getAccountFrom(), transfers.getAccountTo(), transfers.getAmount());
    }
}
