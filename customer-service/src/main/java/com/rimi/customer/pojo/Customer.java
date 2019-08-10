package com.rimi.customer.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "oa_customer")
public class Customer implements Serializable {

    @Id
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


    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;
}
