package com.rimi.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rimi.common.constant.RedisConstant;
import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.user.client.AuthServiceClient;
import com.rimi.user.dao.UserDao;
import com.rimi.user.dto.LoginDTO;
import com.rimi.user.pojo.JWT;
import com.rimi.user.pojo.User;
import com.rimi.user.service.IUserService;
import com.rimi.user.uitls.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author shangzf
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthServiceClient authServiceClient;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public RespDTO register(String username, String password) {
        if (existUserByUserName(username)) {
            throw new CommonException(ErrorCode.USER_REGISTER);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtils.getPassword(password));
        userDao.save(user);
        return RespDTO.onSuc(user);
    }

    /**
     * 判断用户名是否存在，如果存在，则返回true，不存在返回false
     * @param username
     * @return
     */
    private boolean existUserByUserName(String username) {
        User byUsername = userDao.findByUsername(username);
        return null != byUsername;
    }

    @Override
    public RespDTO login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (null == user) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        if (!BPwdEncoderUtils.matches(password, user.getPassword())) {
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }
        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
        if (jwt == null) {
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        // 把token存入到redis中
        redisTemplate.opsForValue().set(RedisConstant.LOGIN + ":" + jwt.getAccess_token(), JSONObject.toJSONString(user), 30, TimeUnit.MINUTES);
        return RespDTO.onSuc(loginDTO);
    }

    @Override
    public RespDTO logout(String authorization) {
        String token = authorization.substring(7);
        // 删除token
        redisTemplate.delete(RedisConstant.LOGIN + ":" + token);
        return new RespDTO();
    }

    @Override
    public RespDTO findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return RespDTO.onSuc(user);
    }

}
