package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class jdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public jdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private Accounts mapRowToAccount(SqlRowSet rs){
        Accounts account = new Accounts();
        account.setAccount_id(rs.getLong("account_id"));
        account.setUser_id(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }

    @Override
    public Accounts getBalance(int id) {
        String sql = "SELECT balance FROM accounts" +
                " where user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            return mapRowToAccount(results);
        }
        return null;
    }
}
