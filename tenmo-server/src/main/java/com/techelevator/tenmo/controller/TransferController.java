package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
