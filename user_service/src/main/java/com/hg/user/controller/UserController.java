package com.hg.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.hg.common.RestResp;
import com.hg.user.model.User;
import com.hg.user.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 浩发 on 2020/7/12 10:08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public RestResp addUser(@RequestBody JSONObject user) {
        User user1 = new User();
        user1.setAge(user.getInteger("age"));
        user1.setUserName(user.getString("name"));
        user1.setMobile(user.getString("mobile"));
        user1.setPassword(user.getString("password"));
        userService.addUser(user1);
        return new RestResp();
    }

    @PostMapping("/auth/login")
    public RestResp login(@RequestBody JSONObject user) {
        return userService.login(user);
    }

}
