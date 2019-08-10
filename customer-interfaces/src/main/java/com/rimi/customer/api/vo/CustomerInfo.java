package com.rimi.customer.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息（学生）
 *
 * @author shangzf
 * @date 2018-07-05 18:50
 */
@Data
public class CustomerInfo implements Serializable {

    private String id;
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 学校
     */
    private String school;

    /**
     * 专业
     */
    private String profession;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业年限
     */
    private String year;

    /**
     * 手机
     */
    private String telephone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String weChat;

    /**
     * 其他联系方式
     */
    private String otherContact;

    /**
     * 现状
     */
    private String presentSituation;

    /**
     * 发展方向
     */
    private String developmentDirection;

    /**
     * 咨询人员名称
     */
    private String consultant;

    /**
     * 来源类型（0-市场，1-网络咨询）
     */
    private Byte sourceType;

    /**
     * 报名时间
     */
    private Date registrationTime;

    /**
     * 有效性
     */
    private Boolean effectiveness;

    /**
     * 到访时间
     */
    private Date visitTime;

    /**
     * 回访记录
     */
    private String[] callback;
}
