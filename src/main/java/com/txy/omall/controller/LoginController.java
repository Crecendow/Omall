package com.txy.omall.controller;
/**
 * Project Name: zmall.
 * Package Name: com.txy.zmall.controller.
 * File Name: loginController
 * Copyright (c) 2019, 南京天芯云数据服务有限公司.
 */

import com.txy.omall.model.User;
import com.txy.omall.service.IUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final String SUCCESS_CODE = "200";
    private static final String FAIL_CODE = "500";
    private static final String ERROR_CODE = "400";
    @Autowired
    private IUser UserService;

    @RequestMapping("/page")
    public String  getLoginPage(){
        return  "login.html";
    }

    @PostMapping("/checkLogin")
    @ResponseBody
    public String checkLoginInfo(@Param("userName") String userName , @Param("password") String password){
        User user =UserService.getUserByName(userName);
        String userPassword = "";
        if(user != null){
            userPassword = user.getUserPwd();
        }
        if(user == null){
            System.out.println("用户不存在");
            return FAIL_CODE;
        }else if (userPassword.equals(password)){
            System.out.println("成功登陆");
            return SUCCESS_CODE;
        }else{
            System.out.println("密码错误");
            return ERROR_CODE;
        }
    }

}
