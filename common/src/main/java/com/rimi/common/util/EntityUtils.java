package com.rimi.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;


/**
 * 实体类相关工具类
 * 解决问题： 1、快速对实体的常驻字段，如：crtUser、crtHost、updUser等值快速注入
 *
 * @author shangzf
 */
public class EntityUtils {
    /**
     * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     */
    public static <T> void setCreateAndUpdateInfo(T entity) {
        setCreateInfo(entity);
        setUpdatedInfo(entity);
    }

    /**
     * 快速将bean的crtUser、crtHost、crtTime附上相关值
     *
     * @param entity 实体bean
     */
    public static <T> void setCreateInfo(T entity) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SetUserInfo setUserInfo = new SetUserInfo(request).invoke();
        String hostIp = setUserInfo.getHostIp();
        String name = setUserInfo.getName();
        String id = setUserInfo.getId();
        // 默认属性
        String[] fields = {"crtName", "crtUser", "crtHost", "crtTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "crtTime");
        // 默认值
        Object[] value = null;
        if (field != null && field.getType().equals(Date.class)) {
            value = new Object[]{name, id, hostIp, new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 快速将bean的updUser、updHost、updTime附上相关值
     *
     * @param entity 实体bean
     */
    public static <T> void setUpdatedInfo(T entity) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SetUserInfo setUserInfo = new SetUserInfo(request).invoke();
        String hostIp = setUserInfo.getHostIp();
        String name = setUserInfo.getName();
        String id = setUserInfo.getId();
        // 默认属性
        String[] fields = {"updName", "updUser", "updHost", "updTime"};
        Field field = ReflectionUtils.getAccessibleField(entity, "updTime");
        Object[] value = null;
        if (field != null && field.getType().equals(Date.class)) {
            value = new Object[]{name, id, hostIp, new Date()};
        }
        // 填充默认属性值
        setDefaultValues(entity, fields, value);
    }

    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value  值数组
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (ReflectionUtils.hasField(entity, field)) {
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    /**
     * 根据主键属性，判断主键是否值为空
     *
     * @param entity 对象
     * @param field  属性
     * @return 主键为空，则返回false；主键有值，返回true
     */
    public static <T> boolean isPKNotNull(T entity, String field) {
        if (!ReflectionUtils.hasField(entity, field)) {
            return false;
        }
        Object value = ReflectionUtils.getFieldValue(entity, field);
        return value != null && !"".equals(value);
    }

    private static class SetUserInfo {
        private HttpServletRequest request;
        private String hostIp = "";
        private String name = "";
        private String id = "";

        SetUserInfo(HttpServletRequest request) {
            this.request = request;
        }

        String getHostIp() {
            return hostIp;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        SetUserInfo invoke() {
            try {
                if (request != null) {
                    String userHost = request.getHeader("userHost");
                    if (StringUtils.isNotBlank(userHost)) {
                        hostIp = userHost;
                    }

                    String userName = request.getHeader("userName");
                    if (StringUtils.isNotBlank(userName)) {
                        name = URLDecoder.decode(userName, "UTF-8");
                    }
                    String userId = request.getHeader("userId");
                    if (StringUtils.isNotBlank(userId)) {
                        id = userId;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return this;
        }
    }
}
