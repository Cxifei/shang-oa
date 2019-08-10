package com.rimi.market.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 院校
 */
@Data
@Document(collection = "oa_colleges_info")
public class Market implements Serializable {

    @Id
    private String id;
    private String number;//编号

    private String customer;//学生信息ID

    private String planTime;//计划培训时间
    private String review;//是否参加评测
    private Byte group;//分组

    private String marketer;//市场人员名称

    private String collegesName;//院校名称

    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;

}
