package com.hg.test.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 浩发 on 2020/7/12 16:36
 */
@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/user/add")
    void addUser(@RequestBody JSONObject user);

}
