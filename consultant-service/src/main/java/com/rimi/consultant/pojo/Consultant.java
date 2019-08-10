package com.rimi.consultant.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 网络咨询
 */
@Data
@Document(collection = "oa_network_consultation")
public class Consultant implements Serializable {

    @Id
    private String id;
//    private String number;//编号

    private String customer;//学生信息ID

    private String consultationTime;//咨询时间*
    private String visitTime;//到访时间
//    private String auditionTime;//试听时间
    private String district;//区域*
    private String consultationCategory;//咨询类别*
    private String platformSource;//平台来源*
    private String contact;//咨询方式*
    private String keywords;//关键字*
    private String referenceURL;//网址*
//    private String concern;//关注点
    private String remark;//备注
//    private Boolean effectiveness;//有效性
//    private String effectivenessVisit;//有效性回访
    private String watchKeeper;//值班人员名称

    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;

}
