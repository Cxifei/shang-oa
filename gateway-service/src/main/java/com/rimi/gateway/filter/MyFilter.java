package com.rimi.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.ErrorCode;
import com.rimi.gateway.dao.LoginDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author shangzf
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {

    @Autowired
    private LoginDao loginDao;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 设置相应状态码
        ctx.setResponseStatusCode(200);
        HttpServletRequest request = ctx.getRequest();
        String authorization = request.getHeader("Authorization");
        // 判断用户请求是否携带token
        if (StringUtils.isNotBlank(authorization)) {
            // 去掉开头的 "Bearer "
            String token = authorization.substring(7);
            // 判断 token 是否存在，如果不存在，则需要登录
            if (StringUtils.isBlank(loginDao.getUser(token))) {
                // 过滤该请求，不对其进行路由
                ctx.setSendZuulResponse(false);
                HttpServletResponse response = ctx.getResponse();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                RespDTO resp = new RespDTO();
                resp.code = ErrorCode.USER_IS_NOT_LOGIN.getCode();
                resp.error = ErrorCode.USER_IS_NOT_LOGIN.getMsg();
                try {
                    // 输出错误信息
                    response.getWriter().print(JSONObject.toJSONString(resp));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ctx.setResponse(response);
            } else {
                // 对该请求进行路由
                ctx.setSendZuulResponse(true);
            }
        }
        return null;
    }


}
