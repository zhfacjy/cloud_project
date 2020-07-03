package com.hg.gateway.filter;

import com.hg.common.gateway.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 验证token，优先级为0
 */
@Component
public class AuthTokenFilter extends ZuulFilter {

    @Resource(name = "JwtUtil")
    private JwtUtil jwtUtil;

    @Value("${auth.ignore.path}")
    private List<String> ignorePath;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (ignorePath.indexOf(request.getPathInfo()) > 0) return false;
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("x-asscess-token");
        Claims claims;
        try {
            //解析没有异常则表示token验证通过，如有必要可根据自身需求增加验证逻辑
            claims = jwtUtil.parseJWT(token);
            //对请求进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            //请求头加入userId，传给业务服务
            ctx.addZuulRequestHeader("userId", claims.get("userId").toString());
        } catch (ExpiredJwtException expiredJwtEx) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(402);
            ctx.setResponseBody("{\"result\":\"token expired!\"}");
            ctx.set("isSuccess", false);
        } catch (Exception ex) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"invalid token!\"}");
            ctx.set("isSuccess", false);
        }
        return null;
    }
}
