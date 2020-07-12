package com.hg.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.hg.common.RestResp;
import com.hg.test.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 浩发 on 2020/7/12 16:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/doSomething")
    public RestResp test(@RequestBody JSONObject param) {
        userServiceClient.addUser(param);
        return new RestResp();
    }

}
