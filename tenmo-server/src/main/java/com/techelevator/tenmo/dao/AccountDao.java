package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Accounts> findAll();

    Accounts getAccount(long id);

    boolean updateBalance(BigDecimal amount, long id);

    Accounts getBalance(long id);
}
