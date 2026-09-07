package com.unitrs.controller;

import com.unitrs.service.UserService;
import com.unitrs.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    

}
