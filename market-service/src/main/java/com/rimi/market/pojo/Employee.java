package com.rimi.market.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工
 */
@Data
@Document(collection = "oa_employee")
public class Employee implements Serializable {

    @Id
    private String id;
    private String employeeNumber;//编号
    private String employeeName;//名称
    private String employeeSex;//性别
    private String employeeBirth;//出生日期
    private String employeeDepartment;//部门
    private String employeePosition;//岗位
    private String employeeMaritalStatus;//婚姻状况
    private String employeeTelephone;//联系电话
    private String employeeEntryDate;//入职时间
    private String employeeEmail;//邮箱
    private String employeeAddress;//家庭住址
    private String employeeImage;//头像

    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;


}
