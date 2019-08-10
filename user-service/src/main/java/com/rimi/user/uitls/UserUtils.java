package com.rimi.user.uitls;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @author shangzf
 */
public class UserUtils {

    private static final String AUTHORIZATION = "authorization";
    private static final String ROLE_STARTS = "ROLE_";

    /**
     * 获取当前请求的token
     *
     * @return
     */
    public static String getCurrentToken() {
        return HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get(AUTHORIZATION);
    }

    /**
     * 获取当前请求的用户Id
     *
     * @return
     */
    public static String getCurrentPrinciple() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 判读当前token用户是否为接口所需的参数username
     *
     * @param username
     * @return
     */
    public static boolean isMyself(String username) {
        return username.equals(getCurrentPrinciple());
    }

    /**
     * 获取当前请求Authentication
     *
     * @return
     */
    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前请求的权限信息
     *
     * @return
     */
    public static List<SimpleGrantedAuthority> getCurrentAuthorities() {
        return (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    /**
     * 是否有角色
     *
     * @param role 角色名称
     * @return
     */
    public static boolean hasRole(String role) {
        if (!role.startsWith(ROLE_STARTS)) {
            role = ROLE_STARTS + role;
        }
        boolean hasRole = false;
        List<SimpleGrantedAuthority> list = getCurrentAuthorities();
        for (SimpleGrantedAuthority s : list) {
            if (role.equals(s.getAuthority())) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

}
