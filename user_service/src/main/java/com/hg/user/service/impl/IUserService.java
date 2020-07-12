package com.hg.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hg.common.RestResp;
import com.hg.user.model.User;
import com.hg.user.repository.UserRepository;
import com.hg.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 浩发 on 2020/7/12 10:11
 */
@Service
public class IUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RestResp login(JSONObject user) {
        User user1 = userRepository.findByUserNameAndPassword(user.getString("name"),user.getString("password"));
        if(user1 == null) {
            return new RestResp(RestResp.ERROR_FOR_MSG,"用户密码错误或不存在");
        }
        return new RestResp(user1);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
