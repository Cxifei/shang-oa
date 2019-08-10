package com.rimi.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息
 */

@Data
@Document(indexName = "rimi_oa",type = "oa_customer")
public class Customer implements Serializable {

    @Id
    private String id;
    private String name;
    private String sex;//性别
    private Byte age;//年龄
    private String school;//学校
    private String profession;//专业
    private String education;//学历
    private String year;//毕业年限
    private String telephone;//手机
    private String qq;//QQ
    private String email;//e-mail
    private String weChat;//微信
    private String otherContact;//其他联系方式
    private String presentSituation;//现状
    private String developmentDirection;//发展方向
    private String consultant;//咨询人员名称
    private Byte sourceType;//来源类型（0-市场，1-网络咨询）
    private Date registrationTime;//报名时间
    private Boolean effectiveness;//有效性
    private Date visitTime;//到访时间


    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;

}
