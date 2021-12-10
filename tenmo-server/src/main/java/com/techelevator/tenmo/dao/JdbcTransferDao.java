package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfers transferAmount() {
        return null;
    }

    @Override
    public Transfers[] viewTransfers() {
        return new Transfers[0];
    }

    @Override
    public Transfers pendingTransactions() {
        return null;
    }
}
