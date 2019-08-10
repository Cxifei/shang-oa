package com.rimi.user.service;

import com.rimi.common.dto.RespDTO;
import com.rimi.user.pojo.User;

/**
 * 用户业务接口
 *
 * @author shangzf
 */
public interface IUserService {

    /**
     * 根据用户名和密码保存用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    RespDTO register(String username, String password);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    RespDTO login(String username, String password);

    /**
     * 退出
     *
     * @param authorization token
     * @return
     */
    RespDTO logout(String authorization);


    /**
     * 根据用户名获得用户信息
     *
     * @param username 用户名
     * @return
     */
    RespDTO findByUsername(String username);
}
