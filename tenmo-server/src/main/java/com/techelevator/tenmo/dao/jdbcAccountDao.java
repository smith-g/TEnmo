package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public List<Accounts> findAll() {
        List<Accounts> accounts = new ArrayList<>();
        String sql = "select * from accounts;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Accounts account = mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Accounts getAccount(long id) {
        String sql = "SELECT * FROM accounts" +
                " where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            return mapRowToAccount(results);
        }
        throw new UsernameNotFoundException("Invalid user ID");
    }

    @Override
    public Accounts getBalance(long id) {
        String sql = "select balance from accounts" +
                " where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToAccount(results);
        }
        throw new UsernameNotFoundException("Invalid user ID");
    }

    @Override
    public boolean updateBalance(BigDecimal amount, long id) {
        String sql = "update accounts set balance = balance + ? where user_id = ?;";
        return jdbcTemplate.update(sql, amount, id) == 1;
    }
}
