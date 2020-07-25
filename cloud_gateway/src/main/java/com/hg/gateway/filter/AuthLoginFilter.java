package com.hg.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hg.common.gateway.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

@Component
public class AuthLoginFilter extends ZuulFilter {

    @Value("#{'${auth.addJwt.path}'.split(',')}")
    private List<String> addJwtPath;

    @Resource(name = "JwtUtil")
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        for (String add : addJwtPath) {
            if (url.indexOf(add) > 0) return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            JSONObject object = JSON.parseObject(body);
            if (object.getInteger("code") == 200) {
                HashMap<String, Object> jwtClaims = new HashMap<String, Object>() {{
                    put("userId", object.getJSONObject("data").getString("userId"));
                }};
                Date expDate = DateTime.now().plusDays(1).toDate(); //过期时间 1 天
                String token = jwtUtil.createJWT(expDate,jwtClaims);
                object.put("token",token);
                body = object.toJSONString();
            }
            ctx.setResponseBody(body);
            // 响应头设置token
            // ctx.addZuulResponseHeader("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
