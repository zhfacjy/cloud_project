package com.hg.common;

import lombok.Data;

/**
 * Created by 浩发 on 2020/7/12 13:13
 */
@Data
public class RestResp {

    public static final Integer OK = 200;
    public static final Integer ERROR = 520;
    public static final Integer NOT_FOUND = 404;
    public static final Integer PARAM_ERROR= 400;
    public static final Integer NO_PERMISSION = 1001;
    public static final Integer NO_SESSION = 1002;
    public static final Integer INVISIBLE = 1003;
    public static final Integer ALREADYEXISTS = 1004;
    // 异常信息提示
    public static final Integer ERROR_FOR_MSG = 1005;

    // 默认成功
    private Integer code = OK;

    private String msg;

    private Object data;

    public RestResp(){}

    public RestResp(Integer code, String msg ,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResp(Object data) {
        this.data = data;
    }

    public RestResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
