package com.rimi.consultant.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "网络咨询信息")
@Data
public class ConsultantVO implements Serializable {

    @ApiModelProperty(value = "ID")
    private String cid;

    @Excel(name = "咨询时间")
    @ApiModelProperty(value = "咨询时间", notes = "格式：yyyy-MM-dd HH:mm")
    private String consultationTime;

    @Excel(name = "到访时间", orderNum = "5")
    @ApiModelProperty(value = "到访时间", notes = "格式：yyyy-MM-dd HH:mm")
    private String visitTime;

    @Excel(name = "试听时间", orderNum = "6")
    @ApiModelProperty(value = "试听时间", notes = "格式：yyyy-MM-dd HH:mm")
    private String auditionTime;

    @Excel(name = "区域", orderNum = "15")
    @ApiModelProperty(value = "区域")
    private String district;

    @Excel(name = "咨询类别", orderNum = "16")
    @ApiModelProperty(value = "咨询类别")
    private String consultationCategory;

    @Excel(name = "平台来源", orderNum = "17")
    @ApiModelProperty(value = "平台来源")
    private String platformSource;

    @Excel(name = "咨询方式", orderNum = "18")
    @ApiModelProperty(value = "咨询方式")
    private String contact;

    @Excel(name = "关键字", orderNum = "19")
    @ApiModelProperty(value = "关键字")
    private String keywords;

    @Excel(name = "网址", orderNum = "20")
    @ApiModelProperty(value = "网址")
    private String referenceURL;

    @Excel(name = "关注点", orderNum = "22")
    @ApiModelProperty(value = "关注点")
    private String concern;

    @Excel(name = "备注", orderNum = "23")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Excel(name = "数据有效性", orderNum = "1")
    @ApiModelProperty(value = "数据有效性")
    private String effectiveness;

    @Excel(name = "有效性回访", orderNum = "24")
    @ApiModelProperty(value = "有效性回访")
    private String effectivenessVisit;

    @Excel(name = "报名时间", orderNum = "25")
    @ApiModelProperty(value = "报名时间")
    private String registrationTime;

    @Excel(name = "值班", orderNum = "26")
    @ApiModelProperty(value = "值班")
    private String watchKeeper;

    @Excel(name = "咨询师", orderNum = "2")
    @ApiModelProperty(value = "咨询师")
    private String consultant;

    @ApiModelProperty(value = "回访记录")
    private String callbackRecord;

    @Excel(name = "姓名", orderNum = "7")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Excel(name = "性别", orderNum = "11")
    @ApiModelProperty(value = "性别")
    private String sex;

    @Excel(name = "年龄", orderNum = "12")
    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "学校")
    private String school;

    @Excel(name = "专业", orderNum = "21")
    @ApiModelProperty(value = "专业")
    private String profession;

    @Excel(name = "学历", orderNum = "13")
    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "毕业年限")
    private String year;

    @Excel(name = "电话", orderNum = "8")
    @ApiModelProperty(value = "电话")
    private String telephone;

    @Excel(name = "QQ", orderNum = "9")
    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "e-mail")
    private String email;

    @Excel(name = "微信", orderNum = "10")
    @ApiModelProperty(value = "微信")
    private String weChat;

    @ApiModelProperty(value = "其他联系方式")
    private String otherContact;

    @Excel(name = "现状", orderNum = "14")
    @ApiModelProperty(value = "现状")
    private String presentSituation;

    @ApiModelProperty(value = "发展方向")
    private String developmentDirection;
}
