package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Accounts;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/pay/{id}", method = RequestMethod.PUT)
    public void updateBalance(@RequestBody Accounts updatedAccount, @PathVariable long id) {
        accountDao.updateBalance(updatedAccount.getBalance(), id);
    }

    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Accounts> accounts() {
        return accountDao.findAll();
    }

    @RequestMapping(path = "/account/{id}", method = RequestMethod.GET)
    public Accounts getAccount(@PathVariable long id) {
        return accountDao.getAccount(id);
    }

}
