package com.rimi.user.client;

import com.rimi.user.client.hystrix.AuthServiceHystrix;
import com.rimi.user.pojo.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 调用 uaa-service 相关接口
 *
 * @author szf
 */
@FeignClient(value = "uaa-service", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {

    /**
     * 获取 JWT 相关信息
     *
     * @param authorization token
     * @param type          类型
     * @param username      用户名
     * @param password      密码
     * @return
     */
    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);
}



