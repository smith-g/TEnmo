package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private UserDao userDao;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> users(){
        return userDao.findAll();
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User currentsUser(@RequestBody String username){
        return userDao.findByUsername(username);
    }
}
