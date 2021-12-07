package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

public interface AccountDao {
    Accounts getBalance(int id);
}
