package com.rimi.user.controller;

import com.rimi.common.dto.RespDTO;
import com.rimi.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关接口
 *
 * @author szf
 */
@Api(value = "用户相关接口", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/register")
    public RespDTO register(String username, String password) {
        return userService.register(username, password);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public RespDTO login(String username, String password) {
        return userService.login(username, password);
    }

    /**
     * 根据用户名获得用户信息
     *
     * @param username 用户名
     * @return
     */
    @ApiOperation(value = "获得用户信息", notes = "根据url中用户名获得用户信息")
    @GetMapping(value = "/{username}", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getUserInfo(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    /**
     * 退出
     *
     * @param authorization
     * @return
     */
    @ApiOperation(value = "退出", notes = "退出")
    @GetMapping(value = "/logout", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO logout(@RequestHeader(value = "Authorization") String authorization) {
        return userService.logout(authorization);
    }
}
