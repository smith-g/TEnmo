package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
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
    public Transfers findTransfer(int id) {

        String sql = "Select * from transfers where transfer_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if(rowSet.next()){
            return mapRowTOTransfers(rowSet);
        }
        throw new UsernameNotFoundException("Transfer " + id + " was not found.");
    }

    @Override
    public Transfers createTransfer(Transfers transfers) {
            String sql = "insert into transfers(TRANSFER_TYPE_ID," +
                    " TRANSFER_STATUS_ID," +
                    " ACCOUNT_FROM, " +
                    "ACCOUNT_TO," +
                    " AMOUNT) " +
                    "values(?,?,?,?,?)";
            jdbcTemplate.update(sql, transfers.getTransferType(), transfers.getTransferStatus(),
                    transfers.getAccountFrom(), transfers.getAccountTo(), transfers.getAmount());
            return transfers;
    }



    private Transfers mapRowTOTransfers(SqlRowSet rs){
        Transfers transfers = new Transfers();
        transfers.setTransferID(rs.getLong("transfer_id"));
        transfers.setTransferType(rs.getLong("transfer_type_id"));
        transfers.setTransferStatus(rs.getLong("transfer_status_id"));
        transfers.setAccountFrom(rs.getLong("account_from"));
        transfers.setAccountTo(rs.getLong("account_to"));
        transfers.setAmount(rs.getBigDecimal("amount"));
        return transfers;
    }


}
