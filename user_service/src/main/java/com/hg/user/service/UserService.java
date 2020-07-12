package com.hg.user.service;

import com.alibaba.fastjson.JSONObject;
import com.hg.common.RestResp;
import com.hg.user.model.User;

/**
 * Created by 浩发 on 2020/7/12 10:11
 */
public interface UserService {

    void addUser(User user);

    RestResp login(JSONObject user);

}
