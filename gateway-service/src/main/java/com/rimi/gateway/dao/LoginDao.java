package com.rimi.gateway.dao;

import com.rimi.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author shangzf
 */
@Repository
public class LoginDao {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 判断 token 是否存在
     *
     * @param token
     * @return
     */
    public String getUser(String token) {
        return redisTemplate.boundValueOps(RedisConstant.LOGIN + ":" + token).get();
    }

    /**
     * 设置过期时间 30分钟
     *
     * @param token
     * @return
     */
    public void setTime(String token) {
        redisTemplate.expire(RedisConstant.LOGIN + ":" + token, 30, TimeUnit.MINUTES);
    }

}
