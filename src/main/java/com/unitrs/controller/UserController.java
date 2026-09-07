package com.unitrs.controller;

import com.unitrs.service.UserService;
import com.unitrs.service.impl.UserServiceImpl;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }
    
    // Controller logic will go here
}
