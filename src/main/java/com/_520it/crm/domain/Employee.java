package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern="yyyy-MM-dd",timezone ="GTM+8")  //后台发送都前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")   //前台发送到后台
    private Date inputtime;  //录入时间

    private Boolean state; //就职状态

    private Boolean admin; //超级管理员身份


}