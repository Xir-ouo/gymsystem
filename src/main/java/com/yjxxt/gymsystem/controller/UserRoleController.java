package com.yjxxt.gymsystem.controller;


import com.yjxxt.gymsystem.base.BaseController;
import com.yjxxt.gymsystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user_role")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;





}
