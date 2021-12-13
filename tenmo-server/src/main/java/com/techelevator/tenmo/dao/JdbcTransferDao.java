package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfers> viewTransfers() {
        List<Transfers> transferHistory = new ArrayList<>();

        String sql = "Select * from transfers";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        return transferHistory;
    }

    @Override
    public Transfers pendingTransactions() {
        return null;
    }
}
