package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Employee {

    private Long id; //ID

    private String username;  //员工账号

    private String realname;  //真实姓名

    private String password;  //密码

    private String tel;  //电话

    private String email;  //邮箱

    private Department dept;  //部门

    private Date inputtime;  //录入时间

    private Boolean state; //就职状态

    private Boolean admin; //超级管理员身份


}