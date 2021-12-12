package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.TransferException;
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
    public Transfers transferAmount() {
        return null;
    }

    @Override
    public List<Transfers> viewTransfers() {
        List<Transfers> transferHistory = new ArrayList<>();

        String sql = "SELECT * from transfers";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Transfers transfers = mapRowTOTransfers(results);
            transferHistory.add(transfers);
        }
        return transferHistory ;
    }

    @Override
    public Transfers pendingTransactions() {
        return null;
    }

    @Override
    public Transfers findTransfer(int id) throws Exception {

        String sql = "Select * from transfers where transfer_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if(rowSet.next()){
            return mapRowTOTransfers(rowSet);
        }
        Exception TransferException = new Exception();
        throw TransferException;
    }

    private Transfers mapRowTOTransfers(SqlRowSet rs){
        Transfers transfers = new Transfers();
        transfers.setTransferID(rs.getLong("transfer_id"));
        transfers.setAccountFrom(rs.getLong("account_from"));
        transfers.setAccountTo(rs.getLong("account_to"));
        transfers.setAmount(rs.getBigDecimal("amount"));
        return transfers;
    }


}
