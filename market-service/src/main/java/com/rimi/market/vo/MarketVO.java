package com.rimi.market.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "院校信息")
@Data
public class MarketVO implements Serializable {

    @ApiModelProperty(value = "ID")
    private String mid;

    @Excel(name="计划培训时间",orderNum = "11")
    @ApiModelProperty(value = "计划培训时间")
    private String planTime;//计划培训时间

    @Excel(name = "是否愿意参加评测",orderNum = "12")
    @ApiModelProperty(value = "是否参加评测")
    private String review;//是否参加评测

    @Excel(name = "分组",orderNum = "13")
    @ApiModelProperty(value = "分组")
    private Byte group;//分组

    @Excel(name = "咨询老师",orderNum = "14")
    @ApiModelProperty(value = "咨询人员")
    private String consultant;//咨询人员

    @Excel(name = "回访记录",orderNum = "15")
    @ApiModelProperty(value = "回访记录")
    private String callbackRecord;//回访记录

    @Excel(name = "市场人员",orderNum = "16")
    @ApiModelProperty(value = "市场人员")
    private String marketer;//市场人员

    @Excel(name = "姓名",orderNum = "1")
    @ApiModelProperty(value = "学生姓名")
    private String name;//学生姓名

    @Excel(name = "性别",orderNum = "2")
    @ApiModelProperty(value = "性别")
    private String sex;//性别

    @ApiModelProperty(value = "年龄")
    private Byte age;//年龄

    @Excel(name = "学校",orderNum = "3")
    @ApiModelProperty(value = "学校")
    private String school;//学校

    @Excel(name = "专业",orderNum = "4")
    @ApiModelProperty(value = "专业")
    private String profession;//专业

    @Excel(name = "学历",orderNum = "5")
    @ApiModelProperty(value = "学历")
    private String education;//学历

    @Excel(name = "年级",orderNum = "10")
    @ApiModelProperty(value = "毕业年限")
    private String year;//毕业年限

    @Excel(name = "手机",orderNum = "6")
    @ApiModelProperty(value = "手机")
    private String telephone;//手机

    @Excel(name = "QQ/邮箱",orderNum = "7")
    @ApiModelProperty(value = "QQ")
    private String qq;//QQ

    @ApiModelProperty(value = "e-mail")
    private String email;//e-mail

    @ApiModelProperty(value = "微信")
    private String weChat;//微信

    @Excel(name = "其他联系方式",orderNum = "8")
    @ApiModelProperty(value = "其他联系方式")
    private String otherContact;//其他联系方式

    @ApiModelProperty(value = "现状")
    private String presentSituation;//现状

    @Excel(name = "意向专业",orderNum = "9")
    @ApiModelProperty(value = "发展方向")
    private String developmentDirection;//发展方向

    @ApiModelProperty(value = "院校名称")
    private String collegesName;//院校名称

}
