package com.rimi.user.client.hystrix;

import com.rimi.user.client.AuthServiceClient;
import com.rimi.user.pojo.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定义接口调用失败，记录日志
 *
 * @author szf
 */
@Component
@Slf4j
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        log.error("调用{}异常,参数{},{},{},{}", "getToken", authorization, type, username,password);
        return null;
    }
}
